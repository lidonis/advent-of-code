import org.gradle.accessors.dm.LibrariesForLibs

val libs = the<LibrariesForLibs>()

plugins {
    id("gradle.kotlin-common-conventions")
    id("org.jetbrains.kotlin.jvm")
}

kotlin {
    jvmToolchain(libs.versions.jdk.get().toInt())
}

dependencies {
    implementation(libs.kotlinx.coroutines)

    testImplementation(libs.bundles.junit)
    testImplementation(libs.assertj)
    testImplementation(libs.mockk)

    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

tasks.named<Test>("test") {
    useJUnitPlatform()

    systemProperty("junit.jupiter.execution.parallel.enabled", "true")
    systemProperty("junit.jupiter.execution.parallel.config.dynamic.factor", "1.0")
    systemProperty("junit.jupiter.execution.parallel.mode.default", "concurrent")
}
