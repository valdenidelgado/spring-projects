plugins {
    id("java")
}

group = "com.clean.adm.catalog.application"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation(project(":domain"))

    implementation("io.vavr:vavr:0.10.4")

    testImplementation(platform("org.junit:junit-bom:5.9.1"))
    testImplementation("org.mockito:mockito-junit-jupiter:4.5.1")
    testImplementation("org.junit.jupiter:junit-jupiter")
}

tasks.test {
    useJUnitPlatform()
}