buildscript {
    repositories {
        mavenCentral()
    }
    dependencies {
        classpath("org.flywaydb:flyway-mysql:8.5.10")
    }
}


plugins {
    id("java")
    id("application")
    id("org.springframework.boot") version "2.6.7"
    id("io.spring.dependency-management") version "1.0.11.RELEASE"
    id("org.flywaydb.flyway") version "8.5.10"
}

group = "com.clean.adm.catalog.infrastructure"
version = "1.0-SNAPSHOT"

tasks {
    val bootJar by getting(org.springframework.boot.gradle.tasks.bundling.BootJar::class) {
        archiveFileName.set("application.jar")
        destinationDirectory.set(file("${rootProject.buildDir}/libs"))
    }
}


repositories {
    mavenCentral()
}

dependencies {
    implementation(project(":domain"))
    implementation(project(":application"))

    implementation("mysql:mysql-connector-java")

    implementation("com.google.cloud:google-cloud-storage:2.17.1")
    implementation("com.google.guava:guava:31.1-jre")

    implementation("org.springframework.boot:spring-boot-starter-web") {
        exclude(module = "spring-boot-starter-tomcat")
    }

    implementation("org.springframework.boot:spring-boot-starter-undertow")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("com.fasterxml.jackson.module:jackson-module-afterburner")

    testImplementation("org.flywaydb:flyway-core")
    testImplementation("org.springframework.boot:spring-boot-starter-test")

    runtimeOnly("com.h2database:h2")
}

flyway {
    url = System.getenv("FLYWAY_DB") ?: "jdbc:mysql://localhost:3306/adm_videos"
    user = System.getenv("FLYWAY_USER") ?: "root"
    password = System.getenv("FLYWAY_PASSWORD") ?: "123456"
}

tasks.test {
    useJUnitPlatform()
}