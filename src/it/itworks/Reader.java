package it.itworks;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Reader {
    public static List<String> readFile(String filepath) {
        /*java.nio.file.r
        try (InputStreamReader isr = new InputStreamReader(new FileInputStream(path)); BufferedReader br = new BufferedReader(isr)) {
            while (br.readLine())
        } catch (Exception e) {

        }*/

        List<String> results = new ArrayList<>();

        Path path = Paths.get(filepath);
        try {
            List<String> contents = Files.readAllLines(path);
            results.addAll(contents);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return results;
    }
}
