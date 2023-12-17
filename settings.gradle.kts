rootProject.name =  "advent-of-code"

plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version ("0.7.0")
}

include("app", "common")

include("aoc2019")
include("aoc2020")
include("aoc2021")
include("aoc2022")
include("aoc2023")
