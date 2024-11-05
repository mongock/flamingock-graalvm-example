plugins {
    id("java")
    id("io.flamingock.graalvmPlugin") version "1.0.2-SNAPSHOT"
    id("org.graalvm.buildtools.native") version "0.10.3"
}

group = "io.flamingock"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
    mavenLocal()
}

val mongodbVersion = "4.3.3"
val jacksonVersion = "2.15.2"
dependencies {
    implementation("io.flamingock:flamingock-core:1.0.0-SNAPSHOT")
    implementation("io.flamingock:mongodb-sync-v4-driver:1.0.0-SNAPSHOT")
    implementation("io.flamingock:utils:1.0.0-SNAPSHOT")

    implementation("org.mongodb:mongodb-driver-sync:$mongodbVersion")
    implementation("org.mongodb:mongodb-driver-core:$mongodbVersion")
    implementation("org.mongodb:bson:$mongodbVersion")

    implementation("org.slf4j", "slf4j-api", "2.0.6")
    implementation("org.slf4j:slf4j-simple:2.0.6")

    implementation("com.fasterxml.jackson.core:jackson-databind:$jacksonVersion")

    implementation("io.flamingock:graalvm-core:1.0.2-SNAPSHOT")
    annotationProcessor("io.flamingock:graalvm-core:1.0.2-SNAPSHOT")

    testImplementation(platform("org.junit:junit-bom:5.9.1"))
    testImplementation("org.junit.jupiter:junit-jupiter")
}

tasks.test {
    useJUnitPlatform()
}

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(17))
    }
}

graalvmNative {
    binaries {

        named("main") {
            buildArgs.addAll(
                "--features=io.flamingock.graalvm.RegistrationFeature",
                "-H:ResourceConfigurationFiles=resource-config.json",
                "--initialize-at-build-time=org.slf4j.simple.SimpleLogger,org.slf4j.LoggerFactory,org.slf4j.impl.StaticLoggerBinder",
                "--initialize-at-run-time=com.google.gson.internal.bind.ReflectiveTypeAdapterFactory,com.google.gson.internal.bind.ReflectiveTypeAdapterFactory\$FieldsData",
                "--no-fallback"
            )
        }

    }
}


tasks.withType<Jar> {
    manifest {
        attributes["Main-Class"] = "io.flamingock.Main"
    }
    duplicatesStrategy = DuplicatesStrategy.EXCLUDE

    from(sourceSets.main.get().output)

    from({
        configurations.runtimeClasspath.get().map { if (it.isDirectory) it else zipTree(it) }
    })
}
