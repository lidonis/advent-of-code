dependencyResolutionManagement {
    versionCatalogs {
        create("libs", { from(files("../gradle/libs.versions.toml")) })
    }
}

rootProject.name = "advent-of-code-build-logic"
