import org.gradle.accessors.dm.LibrariesForLibs
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

val libs = the<LibrariesForLibs>()

plugins {
    id("gradle.kotlin-common-conventions")
    id("org.jetbrains.kotlin.multiplatform")
}

kotlin {

    jvm()

    sourceSets {
        commonMain.dependencies {
            implementation(libs.kotlinx.coroutines)
        }
        commonTest.dependencies {
            implementation(libs.mockk)
        }
        jvmTest.dependencies {
            implementation(kotlin("test-junit5"))
            implementation(libs.bundles.junit)
            implementation(libs.assertj)
            runtimeOnly("org.junit.platform:junit-platform-launcher")
            runtimeOnly(libs.junit.engine)
        }
    }
}

tasks.withType<KotlinCompile>().configureEach {
    compilerOptions {
        freeCompilerArgs.add("-Xcontext-receivers")
        freeCompilerArgs.add("-Xwhen-guards")
    }
}

tasks.named<Test>("jvmTest") {
    useJUnitPlatform()

    systemProperty("junit.jupiter.execution.parallel.enabled", "true")
    systemProperty("junit.jupiter.execution.parallel.config.dynamic.factor", "1.0")
    systemProperty("junit.jupiter.execution.parallel.mode.default", "concurrent")
}
