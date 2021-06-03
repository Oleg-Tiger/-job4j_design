package ru.job4j.io;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Zip {

    public void packFiles(List<Path> sources, Path target) {
        try (ZipOutputStream zip = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(target.toFile())))) {
            for (Path source : sources) {
                zip.putNextEntry(new ZipEntry(source.toString()));
                try (BufferedInputStream out = new BufferedInputStream(new FileInputStream(source.toFile()))) {
                    zip.write(out.readAllBytes());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void packSingleFile(Path source, Path target) {
        try (ZipOutputStream zip = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(target.toFile())))) {
            zip.putNextEntry(new ZipEntry(source.toString()));
            try (BufferedInputStream out = new BufferedInputStream(new FileInputStream(source.toFile()))) {
                zip.write(out.readAllBytes());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {
        ArgsName aName = ArgsName.of(args);
        String dir = aName.get("d");
        String output = aName.get("o");
        String exclude = aName.get("e");
        if (dir == null || exclude == null || output == null || exclude.startsWith(".") || !output.endsWith(".zip")) {
            throw new IllegalArgumentException(
                    "The input arguments must match the template -d=rootDirectory -e=excludeFileExtension(without \".\") -o=output.zip"
            );
        }
        if (!Files.exists(Paths.get(dir))) {
            throw new FileNotFoundException("The source directory does not exist");
        }
        List<Path> sources = Search.search(
                Paths.get(dir), x -> !x.toFile().getName().endsWith(String.format(".%s", exclude))
        );
        Path target = Paths.get(output);
        new Zip().packFiles(sources, target);
    }
}