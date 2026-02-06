import org.gradle.accessors.dm.LibrariesForLibs

val libs = the<LibrariesForLibs>()

plugins {
    id("io.gitlab.arturbosch.detekt")
}

repositories {
    mavenCentral()
    maven {
        url = uri("https://repo.kotlin.link")
    }
}

dependencies {
    detektPlugins(libs.bundles.detekt)
}

detekt {
    buildUponDefaultConfig = true
    allRules = false
    autoCorrect = true
    config.setFrom("${project.rootDir}/detekt.yml")
    source.setFrom(
        "src/commonMain/kotlin",
        "src/commonTest/kotlin",
        "src/jvmMain/kotlin",
        "src/jvmTest/kotlin",
        "src/main/kotlin",
        "src/test/kotlin",
    )
}
