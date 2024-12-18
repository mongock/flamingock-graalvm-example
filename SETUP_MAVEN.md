# Steeps for GraalVM support

1. Add graalVM dependency and annotation processor
```xml
<dependencies>
    <dependency>
        <groupId>io.flamingock</groupId>
        <artifactId>mongodb-sync-v4-driver</artifactId>
        <version>${flamingock.version}</version>
    </dependency>

    <dependency>
        <groupId>io.flamingock</groupId>
        <artifactId>flamingock-core</artifactId>
        <version>${flamingock.version}</version>
    </dependency>

    <dependency>
        <groupId>io.flamingock</groupId>
        <artifactId>flamingock-graalvm</artifactId>
        <version>${flamingock.version}</version>
    </dependency>
</dependencies>

```

2. Add annotation processor
```xml
<build>
    <plugins>
        <plugin>
            <groupId>org.apache.maven.plugins</groupId>
            <artifactId>maven-compiler-plugin</artifactId>
            <version>3.11.0</version>
            <configuration>
                <annotationProcessorPaths>
                    <path>
                        <groupId>io.flamingock</groupId>
                        <artifactId>flamingock-core</artifactId>
                        <version>${flamingock.version}</version>
                    </path>
                </annotationProcessorPaths>
            </configuration>
        </plugin>
    </plugins>
</build>
```

3. Add the flamingock configuration file to `resource-config.json`
```json
{
  "resources": {
    "includes": [
      {
        "pattern": "META-INF/flamingock-metadata.json"
      }
    ]
  }
}
```

4. Build application
```shell
./mvnw clean package
```

5. Create native image
```shell
native-image --no-fallback --features=io.flamingock.graalvm.RegistrationFeature -H:ResourceConfigurationFiles=resource-config.json -H:+ReportExceptionStackTraces --initialize-at-build-time=org.slf4j.simple.SimpleLogger,org.slf4j.LoggerFactory,org.slf4j.impl.StaticLoggerBinder -jar target/flamingock-graalvm-example-0.0.1-SNAPSHOT.jar
```
6. Run native image
```shell
./flamingock-graalvm-example-1.0-SNAPSHOT
```

