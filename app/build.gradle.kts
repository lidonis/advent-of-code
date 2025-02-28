plugins {
    id("gradle.kotlin-application-jvm-conventions")
    id("org.graalvm.buildtools.native") version "0.10.5"
}

group = "fr.lidonis.adventofcode"
version = "1.0-SNAPSHOT"

dependencies {
    implementation(project(":common"))

    for (year in 2019..2024) {
        implementation(project(":aoc$year"))
    }

    implementation(libs.jansi)
    implementation(libs.kotlinx.cli)
}

application {
    mainClass.set("fr.lidonis.adventofcode.AdventOfCodeMainKt")
}

graalvmNative {
    toolchainDetection.set(true)
    binaries.all {
        resources.autodetect()
    }
}
