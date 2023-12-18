plugins {
    id("gradle.kotlin-library-jvm-conventions")
    `java-test-fixtures`
}

dependencies {
    testFixturesApi(libs.junit)
    testFixturesImplementation(libs.assertj)
    testFixturesImplementation(libs.kotlin.reflect)
}