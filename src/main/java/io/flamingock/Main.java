package io.flamingock;

import com.google.gson.Gson;
import io.flamingock.core.api.FlamingockMetadata;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) {
        new Runner().load();
//
////        FlamingockMetadata metada = FlamingockMetadata.getInstance();
//        ClassLoader classLoader = Main.class.getClassLoader();
//        try (InputStream inputStream = classLoader.getResourceAsStream("META-INF/flamingock-metadata.txt")) {
//            if (inputStream != null) {
//                StringBuilder sb = new StringBuilder();
//                try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
//                    String line;
//
//                    while ((line = reader.readLine()) != null) {
//                        System.out.println("Annotated Class: " + line);
//                        sb.append(line);
//                    }
//                }
//
//                FlamingockMetadata metadata = new Gson().fromJson(sb.toString(), FlamingockMetadata.class);
//                System.out.println(metadata);
//            } else {
//                throw new RuntimeException("annotated-classes.txt not found in META-INF");
//            }
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//
//        }
    }
}