plugins {
    id("gradle.kotlin-application-conventions")
    alias(libs.plugins.detekt)
    id("org.graalvm.buildtools.native") version "0.9.28"
    alias(libs.plugins.jmh)
}

group = "fr.lidonis.adventofcode"
version = "1.0-SNAPSHOT"

dependencies {
    implementation(project(":common"))

    for(year in 2019..2023) {
        implementation(project(":aoc$year"))
    }

    implementation(libs.jansi)
    implementation(libs.kotlinx.cli)

    jmhAnnotationProcessor(libs.jmh.generator)

    testImplementation(libs.kotlin.reflect)

    detektPlugins(libs.bundles.detekt)
}

application {
    mainClass.set("fr.lidonis.adventofcode.AdventOfCodeMainKt")
}

detekt {
    buildUponDefaultConfig = true
    allRules = false
    autoCorrect = true
    config.setFrom("${project.rootDir}/detekt.yml")
    source.setFrom(
        "src/main/kotlin",
        "src/test/kotlin",
    )
}

graalvmNative {
    toolchainDetection.set(true)
    binaries.all {
        resources.autodetect()
    }
}
