package io.flamingock;

import io.flamingock.core.api.FlamingockMetadata;

public class Main {
    public static void main(String[] args) {
        FlamingockMetadata metadata = FlamingockMetadata.getInstance();
        System.out.println(metadata);
    }
}