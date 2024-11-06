package io.flamingock;

import io.flamingock.core.api.metadata.FlamingockMetadata;

public class Main {
    public static void main(String[] args) {
        FlamingockMetadata metadata = FlamingockMetadata.getInstance().orElseThrow(() -> new RuntimeException("No flamingock metadata"));
        System.out.println(metadata);
    }
}