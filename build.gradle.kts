plugins {
    id("java")
    id("io.flamingock.graalvmPlugin") version "1.0.2-SNAPSHOT"
}

group = "io.flamingock"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
    mavenLocal()
}

val mongodbVersion = "4.3.3"
dependencies {
    implementation("io.mongock:mongock-standalone:5.3.5")
    implementation("io.mongock:mongodb-sync-v4-driver:5.3.5")

    implementation("org.mongodb:mongodb-driver-sync:$mongodbVersion")
    implementation("org.mongodb:mongodb-driver-core:$mongodbVersion")
    implementation("org.mongodb:bson:$mongodbVersion")

    implementation("org.slf4j", "slf4j-api", "2.0.6")
    implementation("org.slf4j:slf4j-simple:2.0.6")

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
