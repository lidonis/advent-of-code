plugins {
    id("gradle.kotlin-library-conventions")
}

dependencies {
    implementation(project(":common"))
    implementation(testFixtures(project(":common")))

    implementation(libs.jackson)
    implementation(libs.kmath)
}