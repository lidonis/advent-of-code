plugins {
    id("gradle.kotlin-library-jvm-conventions")
}

dependencies {
    implementation(project(":common"))
    implementation(testFixtures(project(":common")))
}
