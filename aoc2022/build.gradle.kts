plugins {
    id("gradle.kotlin-library-conventions")
}

repositories {
    maven {
        url = uri("https://repo.kotlin.link")
    }
}

dependencies {
    implementation(project(":common"))

    implementation(libs.jackson)
    implementation(libs.kmath)
}