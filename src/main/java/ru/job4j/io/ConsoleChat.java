package ru.job4j.io;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ConsoleChat {
    private final String path;
    private final String botAnswers;
    private final List<String> botAnswersList = new ArrayList<>();
    private final List<String> dialogList = new ArrayList<>();
    private static final String OUT = "закончить";
    private static final String STOP = "стоп";
    private static final String CONTINUE = "продолжить";

    public ConsoleChat(String path, String botAnswers) {
        this.path = path;
        this.botAnswers = botAnswers;
    }

    public void run() {
        try (BufferedReader console = new BufferedReader(new InputStreamReader(System.in))
            ) {
            if (botAnswersList.isEmpty()) {
              fillAnswersList();
            }
            String user = console.readLine();
            boolean stop = false;
            while (!user.equals(OUT)) {
                dialogList.add(String.format("Пользователь: %s", user));
                if (user.equals(STOP)) {
                    stop = true;
                } else if (!stop || user.equals(CONTINUE)) {
                    stop = false;
                    dialogList.add(String.format("Робот: %s", answer()));
                }
                user = console.readLine();
            }
            dialogList.add("Пользователь: закончить");
            writeDialogToFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void fillAnswersList() {
        try (BufferedReader answ = new BufferedReader(new FileReader(botAnswers))) {
            while (answ.ready()) {
                botAnswersList.add(answ.readLine());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void writeDialogToFile() {
        try (PrintWriter dialog = new PrintWriter(new FileWriter(path))) {
            dialogList.forEach(dialog::println);
            dialogList.clear();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String answer() {
        int index = (int) (Math.random() * (botAnswersList.size()));
        return botAnswersList.get(index);
    }

    public static void main(String[] args) {
        ConsoleChat cc = new ConsoleChat("C:\\projects\\job4j_design\\data\\Dialog.txt",
                "C:\\projects\\job4j_design\\data\\Bot's_answers.txt");
        cc.run();
    }
}