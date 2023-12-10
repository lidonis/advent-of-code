import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    application
    alias(libs.plugins.kotlin)
    alias(libs.plugins.detekt)
    id("org.graalvm.buildtools.native") version "0.9.28"
    alias(libs.plugins.jmh)
}

group = "fr.lidonis.adventofcode"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
    maven {
        url = uri("https://repo.kotlin.link")
    }
}

kotlin {
    jvmToolchain(libs.versions.jdk.get().toInt())
}

dependencies {
    implementation(libs.kotlinx.coroutines)

    implementation(libs.jansi)
    implementation(libs.kotlinx.cli)

    implementation(libs.jackson)
    implementation(libs.kmath)

    jmhAnnotationProcessor(libs.jmh.generator)

    testImplementation(libs.bundles.junit)
    testImplementation(libs.assertj)
    testImplementation(libs.mockk)
    testImplementation(libs.kotlin.reflect)

    detektPlugins(libs.bundles.detekt)
}

application {
    mainClass.set("fr.lidonis.adventofcode.AdventOfCodeMainKt")
}

tasks.withType<KotlinCompile>().configureEach {
    kotlinOptions {
        freeCompilerArgs += "-Xcontext-receivers"
    }
}

tasks.withType<Test>().configureEach {
    useJUnitPlatform()
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
