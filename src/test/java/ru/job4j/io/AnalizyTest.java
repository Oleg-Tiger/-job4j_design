package ru.job4j.io;

import static org.junit.Assert.assertThat;
import static org.hamcrest.Matchers.is;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.*;

public class AnalizyTest {

    @Rule
    public TemporaryFolder folder = new TemporaryFolder();

    @Test
    public void unavailable() throws IOException {
        File source = folder.newFile("source.txt");
        File target = folder.newFile("target.txt");
        try (PrintWriter out = new PrintWriter(new BufferedOutputStream(new FileOutputStream(source)))) {
            out.println("200 10:56:01");
            out.println("500 10:57:01");
            out.println("400 10:58:01");
            out.println("200 10:59:01");
            out.println("500 11:01:02");
            out.println("200 11:02:02");
        }
        new Analizy().unavailable(source.getAbsolutePath(), target.getAbsolutePath());
        StringBuilder rsl = new StringBuilder();
        try (BufferedReader in = new BufferedReader(new FileReader(target))) {
            in.lines().forEach(rsl::append);
        }
        String expected = String.format("%s%s", "10:57:01;10:59:01;", "11:01:02;11:02:02;");
        assertThat(rsl.toString(), is(expected));
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenTemplateBroken() throws IOException {
        File source = folder.newFile("source.txt");
        File target = folder.newFile("target.txt");
        try (PrintWriter out = new PrintWriter(new BufferedOutputStream(new FileOutputStream(source)))) {
            out.println("200-10:56:01");
        }
        new Analizy().unavailable(source.getAbsolutePath(), target.getAbsolutePath());
    }
}