plugins {
    id("gradle.kotlin-common-multiplatform-conventions")
}

kotlin {
    jvm()

    sourceSets {
        commonMain.dependencies {
            implementation(libs.kotlinx.serialization)
            implementation(libs.kmath)
        }

        jvmMain.dependencies {
            implementation(project(":common"))
        }
    }
}
