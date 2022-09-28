import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.7.10"
    id("org.springframework.boot") version "2.7.2"
    id("io.spring.dependency-management") version "1.0.12.RELEASE"
    kotlin("plugin.spring") version "1.6.21"
}

group = "ltd.matrixstudios"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
    mavenLocal()

    maven(url = "https://repo.spring.io/release")
}

dependencies {
    //spring
    implementation("org.springframework.boot:spring-boot-starter-data-mongodb-reactive")
    implementation("org.springframework.boot:spring-boot-starter-web")

    //google
    implementation("com.google.code.gson:gson:2.8.6")

    //kt
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-reactor")

    //commands
    implementation("com.github.Revxrsal.Lamp:cli:3.0.0")
    implementation("com.github.Revxrsal.Lamp:common:3.0.0")
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}