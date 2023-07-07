plugins {
    id("java")
    id("application")
    id("org.springframework.boot") version "2.6.7"
    id("io.spring.dependency-management") version "1.0.11.RELEASE"
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

    implementation("org.springframework.boot:spring-boot-starter-web") {
        exclude(module = "spring-boot-starter-tomcat")
    }

    implementation("org.springframework.boot:spring-boot-starter-undertow")

    testImplementation("org.springframework.boot:spring-boot-starter-test")
}

tasks.test {
    useJUnitPlatform()
}