# Steeps for GraalVM support

1. Add flamingock plugin to build script
```kotlin
plugins {
    id("java")
    id("io.flamingock.graalvmPlugin") version "1.0.2-SNAPSHOT"
}
```

2. Add graalVM dependency and annotation processor
```kotlin
    implementation("io.flamingock:graalvm-core:1.0.2-SNAPSHOT")
    annotationProcessor("io.flamingock:graalvm-core:1.0.2-SNAPSHOT")
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
        "pattern": "META-INF/flamingock-metadata.txt"
      }
    ]
  }
}
```

5. `.setLockGuardEnabled(false)` in the builder.

6. Build application
```shell
./gradlew clean build
```

7. Create native image
```shell
 native-image --no-fallback \
--features=io.flamingock.graalvm.RegistrationFeature \
-H:ResourceConfigurationFiles=resource-config.json \
--initialize-at-build-time=org.slf4j.simple.SimpleLogger,org.slf4j.LoggerFactory,org.slf4j.impl.StaticLoggerBinder \
--initialize-at-run-time=com.google.gson.internal.bind.ReflectiveTypeAdapterFactory,com.google.gson.internal.bind.ReflectiveTypeAdapterFactory$FieldsData \
--trace-class-initialization=com.google.gson.internal.bind.ReflectiveTypeAdapterFactory$FieldsData \
-H:+ReportExceptionStackTraces \
-jar build/libs/graalvm-example-2-1.0-SNAPSHOT.jar

```

