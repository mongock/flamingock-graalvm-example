package io.flamingock;

import io.flamingock.core.api.FlamingockMetadata;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) {

        FlamingockMetadata metada = FlamingockMetadata.getInstance();
        ClassLoader classLoader = Main.class.getClassLoader();
        try (InputStream inputStream = classLoader.getResourceAsStream("META-INF/flamingock-metadata.txt")) {
            if (inputStream != null) {
                try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
                    String line;
                    while ((line = reader.readLine()) != null) {
                        System.out.println("Annotated Class: " + line);
                    }
                }
            } else {
                throw new RuntimeException("annotated-classes.txt not found in META-INF");
            }
        } catch (Exception e) {
            throw new RuntimeException(e);

        }
    }
}