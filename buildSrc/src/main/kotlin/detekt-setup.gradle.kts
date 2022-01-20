plugins {
    id("io.gitlab.arturbosch.detekt")
}

detekt {
    // Version of Detekt that will be used. When unspecified the latest detekt
    // version found will be used. Override to stay on the same version.
    toolVersion = "1.19.0"

    // The directories where detekt looks for source files.
    // Defaults to `files("src/main/java", "src/test/java", "src/main/kotlin", "src/test/kotlin")`.
    source = files("src/main/java", "src/main/kotlin")

    // Define the detekt configuration(s) you want to use.
    // Defaults to the default detekt configuration.
    config = files("${rootDir.path}/checks/detekt/config.yml")

    // If set to `true` the build does not fail when the
    // maxIssues count was reached. Defaults to `false`.
    ignoreFailures = true

    // Android: Don't create tasks for the specified build types (e.g. "release")
    ignoredBuildTypes = listOf("release")
}

val Project.reportOutputDir get() = "${buildDir.absolutePath}/reports/detekt"

tasks.withType<io.gitlab.arturbosch.detekt.Detekt>().configureEach {
    reports {
        // Enable/Disable XML report (default: true)
        xml.required.set(true)
        xml.outputLocation.set(file("$reportOutputDir/output.xml"))

        // Enable/Disable TXT report (default: true)
        txt.required.set(true)
        txt.outputLocation.set(file("$reportOutputDir/output.txt"))

        html.required.set(false)
        sarif.required.set(false)
    }
}