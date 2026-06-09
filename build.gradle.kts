plugins {
    kotlin("jvm") version "2.0.21"
    application
}

group = "pharmaplus"
version = "0.1.0"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(kotlin("test"))
}

application {
    // Point d'entrée du harnais (sous-domaine application).
    mainClass.set("pharmaplus.harnais.application.MainKt")
}

tasks.test {
    useJUnitPlatform()
}
