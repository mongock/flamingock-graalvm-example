package io.flamingock;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {
        ClassLoader classLoader = Main.class.getClassLoader();
        try (InputStream inputStream = classLoader.getResourceAsStream("META-INF/annotated-classes.txt")) {
            if (inputStream != null) {
                try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
                    String line;
                    while ((line = reader.readLine()) != null) {
                        System.out.println("Annotated Class: " + line);
                    }
                }
            } else {
                System.err.println("annotated-classes.txt not found in META-INF");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}