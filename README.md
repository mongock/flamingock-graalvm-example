# Steeps for GraalVM support

1. Add flamingock plugin to build script
```kotlin
plugins {
    //...
    id("io.flamingock.MetadataBundler") version "1.0.0-SNAPSHOT"
}
```

2. Add graalVM dependency and annotation processor
```kotlin
implementation("io.flamingock:flamingock-core:1.0.0-SNAPSHOT")
annotationProcessor("io.flamingock:metadata-generator:1.0.0-SNAPSHOT")
implementation("io.flamingock:graalvm-core:1.0.3-SNAPSHOT")
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

6. Build application
```shell
./gradlew clean build
```

7. Create native image
```shell
native-image --no-fallback --features=io.flamingock.graalvm.RegistrationFeature -H:ResourceConfigurationFiles=resource-config.json -H:+ReportExceptionStackTraces --initialize-at-build-time=org.slf4j.simple.SimpleLogger,org.slf4j.LoggerFactory,org.slf4j.impl.StaticLoggerBinder -jar build/libs/flamingock-graalvm-example-1.0-SNAPSHOT.jar
```

8. Run native image
```shell
./flamingock-graalvm-example-1.0-SNAPSHOT
```

