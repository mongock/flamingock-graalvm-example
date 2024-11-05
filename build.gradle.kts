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
dependencies {
    implementation("io.flamingock:flamingock-core:1.0.0-SNAPSHOT")
    implementation("io.flamingock:mongodb-sync-v4-driver:1.0.0-SNAPSHOT")

    implementation("org.mongodb:mongodb-driver-sync:$mongodbVersion")
    implementation("org.mongodb:mongodb-driver-core:$mongodbVersion")
    implementation("org.mongodb:bson:$mongodbVersion")

    implementation("org.slf4j", "slf4j-api", "2.0.6")
    implementation("org.slf4j:slf4j-simple:2.0.6")

    implementation("com.google.code.gson:gson:2.11.0")

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
//    metadataRepository {
//        enabled.set(true)
//        uri(file("/Users/dieppa/workspace/wiremock/graalvm-example-2/src/main/resources/META-INF/reachability/"))
//    }

//    -H:ResourceConfigurationFiles=resource-config.json \
    binaries {

        named("main") {
            buildArgs.addAll(
                "--features=io.flamingock.graalvm.RegistrationFeature",
                "--initialize-at-build-time=org.slf4j.simple.SimpleLogger,org.slf4j.LoggerFactory,org.slf4j.impl.StaticLoggerBinder",
                "--no-fallback"
            )
        }
    }
}

//graalvmNative {
//    binaries {
//        named("main") {
//            javaLauncher.set(javaToolchains.launcherFor {
//                languageVersion.set(JavaLanguageVersion.of(20))
//                vendor.set(JvmVendorSpec.matching("Oracle Corporation"))
//            })
//        }
//
//        all {
//            options.add("--initialize-at-build-time=com.fasterxml.jackson.databind")
//            options.add("--initialize-at-run-time=org.w3c.dom,javax.xml")
//            option("--no-fallback")
//        }
//    }
//}

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
