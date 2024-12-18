plugins {
    id("java")
}

group = "io.flamingock"
version = "0.0.1-SNAPSHOT"

repositories {
    mavenCentral()
    mavenLocal()
}

val mongodbVersion = "4.3.3"
val jacksonVersion = "2.15.2"
val flamingockVersion = "0.0.30-beta"
dependencies {
    implementation("io.flamingock:mongodb-sync-v4-driver:$flamingockVersion")
    implementation("io.flamingock:flamingock-core:$flamingockVersion")
    implementation("io.flamingock:flamingock-graalvm:$flamingockVersion")

    annotationProcessor("io.flamingock:flamingock-core:$flamingockVersion")


    implementation("org.mongodb:mongodb-driver-sync:$mongodbVersion")

    implementation("org.slf4j", "slf4j-api", "2.0.6")
    implementation("org.slf4j:slf4j-simple:2.0.6")

}

tasks.test {
    useJUnitPlatform()
}

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(17))
    }
}

tasks.withType<Jar> {
    manifest {
        attributes["Main-Class"] = "io.flamingock.App"
    }
    duplicatesStrategy = DuplicatesStrategy.EXCLUDE

    from(sourceSets.main.get().output)

    from({
        configurations.runtimeClasspath.get().map { if (it.isDirectory) it else zipTree(it) }
    })
}
