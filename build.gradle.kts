plugins {
    application
    kotlin("jvm") version "1.3.61"
    id("com.github.johnrengelman.shadow") version "5.0.0"
    kotlin("plugin.serialization") version "1.3.61"
}

group = "com.justai.jaicf"
version = "1.0.0"

val jaicf = "0.7.0"
val slf4j = "1.7.30"
val ktor = "1.3.1"

// Main class to run application on heroku. Either PollingConnectionKt, or WebhookConnectionKt
application {
    mainClassName = "com.justai.jaicf.template.connections.AimyboxAndWebhookConnectionKt"

}

repositories {
    mavenLocal()
    mavenCentral()
    jcenter()
    maven("https://jitpack.io")
}

dependencies {
    implementation(kotlin("stdlib-jdk8"))

    implementation("org.slf4j:slf4j-simple:$slf4j")
    implementation("org.slf4j:slf4j-log4j12:$slf4j")

    implementation("com.justai.jaicf:core:$jaicf")
    implementation("com.justai.jaicf:mongo:$jaicf")
    implementation("com.justai.jaicf:jaicp:$jaicf")
    implementation("com.justai.jaicf:caila:$jaicf")

    implementation("com.justai.jaicf:telegram:$jaicf")

    implementation("io.ktor:ktor-server-netty:$ktor")

    implementation("com.justai.jaicf:aimybox:$jaicf")

    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.0.0")

}

tasks {
    compileKotlin {
        kotlinOptions.jvmTarget = "1.8"
    }
    compileTestKotlin {
        kotlinOptions.jvmTarget = "1.8"
    }
}

tasks.withType<Jar> {
    manifest {
        attributes(
            mapOf(
                "Main-Class" to application.mainClassName
            )
        )
    }
}

tasks.create("stage") {
    dependsOn("shadowJar")
}
