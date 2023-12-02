import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    application
    alias(libs.plugins.kotlin)
    alias(libs.plugins.detekt)
    alias(libs.plugins.graalvm)
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

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(libs.versions.jdk.get())
    }
}

kotlin {
    jvmToolchain(libs.versions.jdk.get().toInt())
}

dependencies {
    implementation(libs.kotlinx.coroutines)

    implementation(libs.picocli)
    implementation(libs.jansi)

    implementation(libs.jackson)
    implementation(libs.kmath)

    annotationProcessor(libs.picocli.codegen)

    jmhAnnotationProcessor(libs.jmh.generator)

    testImplementation(libs.bundles.junit)
    testImplementation(libs.assertj)
    testImplementation(libs.mockk)
    testImplementation(libs.kotlin.reflect)

    detektPlugins(libs.bundles.detekt)
}

application {
    mainClass.set("fr.lidonis.adventofcode.AdventOfCodeMain")
}

tasks.withType<KotlinCompile>().configureEach {
    kotlinOptions {
        freeCompilerArgs += "-Xcontext-receivers"
    }
}

tasks.withType<Test>().configureEach {
    useJUnitPlatform()
}

graal {
    mainClass(application.mainClass.get())
    outputName(rootProject.name)
    javaVersion(libs.versions.jdk.get())
    option("--static")
    graalVersion(libs.versions.graalvm.get())
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
