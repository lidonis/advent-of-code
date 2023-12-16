import org.gradle.accessors.dm.LibrariesForLibs
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

val libs = the<LibrariesForLibs>()

plugins {
    id("org.jetbrains.kotlin.jvm")
    id("io.gitlab.arturbosch.detekt")
}

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

    testImplementation(libs.bundles.junit)
    testImplementation(libs.assertj)
    testImplementation(libs.mockk)

    testRuntimeOnly("org.junit.platform:junit-platform-launcher")

    detektPlugins(libs.bundles.detekt)
}

tasks.withType<KotlinCompile>().configureEach {
    kotlinOptions {
        freeCompilerArgs += "-Xcontext-receivers"
    }
}

tasks.named<Test>("test") {
    useJUnitPlatform()

    systemProperty("junit.jupiter.execution.parallel.enabled", "true")
    systemProperty("junit.jupiter.execution.parallel.config.dynamic.factor", "1.0")
    systemProperty("junit.jupiter.execution.parallel.mode.default", "concurrent")
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
