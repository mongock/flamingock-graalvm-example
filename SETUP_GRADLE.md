# Steeps for GraalVM support with Gradle

1. Add flamingock dependencies 
```kotlin
implementation("io.flamingock:mongodb-sync-v4-driver:$flamingockVersion")
implementation("io.flamingock:flamingock-core:$flamingockVersion")
implementation("io.flamingock:flamingock-graalvm:$flamingockVersion")
```

2. Add flamingock annotation processor
```kotlin
annotationProcessor("io.flamingock:flamingock-core:$flamingockVersion")
```

3. Add plugin manager to `settings.gradle.kts`
```kotlin
pluginManagement {
    repositories {
        mavenLocal()
        gradlePluginPortal()
        mavenCentral()
    }
}
```

4. Add the flamingock configuration file to `resource-config.json`
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

5. Build application
```shell
./gradlew clean build
```

6.Create native image
```shell
native-image --no-fallback --features=io.flamingock.graalvm.RegistrationFeature -H:ResourceConfigurationFiles=resource-config.json -H:+ReportExceptionStackTraces --initialize-at-build-time=org.slf4j.simple.SimpleLogger,org.slf4j.LoggerFactory,org.slf4j.impl.StaticLoggerBinder -jar build/libs/flamingock-graalvm-example-0.0.1-SNAPSHOT.jar
```

7. Run native image
```shell
./flamingock-graalvm-example-1.0-SNAPSHOT
```

