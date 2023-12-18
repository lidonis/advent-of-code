plugins {
    id("gradle.kotlin-library-jvm-conventions")
    //alias(libs.plugins.jmh)
}

dependencies {
    implementation(project(":common"))
    implementation(testFixtures(project(":common")))

    implementation(libs.jackson)
    implementation(libs.kmath)

    //jmhAnnotationProcessor(libs.jmh.generator)
}