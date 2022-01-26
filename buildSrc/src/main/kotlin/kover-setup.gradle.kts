plugins {
    id("org.jetbrains.kotlinx.kover")
}

kover {
    // change instrumentation agent and reporter
    coverageEngine.set(kotlinx.kover.api.CoverageEngine.INTELLIJ)
    // change version of JaCoCo agent and reporter
    jacocoEngineVersion.set("0.8.7")
    // false to do not execute `koverMergedReport` task before `check` task
    generateReportOnCheck = true
    // true to instrument packages `android.*` and `com.android.*`
    instrumentAndroidPackage = false
    // true to run all tests in all projects if `koverHtmlReport`, `koverXmlReport`, `koverReport`, `koverVerify` or `check` tasks executed on some project
    runAllTestsForProjectTask = false
    // setOf("project-name") to disable coverage for project with name `project-name`
//    disabledProjects = setOf()
}

tasks.koverMergedHtmlReport {
    // false to disable report generation
    isEnabled = true
    htmlReportDir.set(layout.buildDirectory.dir("merged-kover-report/html-result"))
}

tasks.koverMergedXmlReport {
    // false to disable report generation
    isEnabled = true
    xmlReportFile.set(layout.buildDirectory.file("merged-kover-report/report.xml"))
}

tasks.named<Test>("test") {
    extensions.configure(kotlinx.kover.api.KoverTaskExtension::class) {
        val excludeFiles = listOf(
            "**/R.class",
            "**/R$*.class",
            "**/com/android/**/*.*",
            "**/BuildConfig.class",
            "**/*Activity*.class",
            "**/*Fragment*.class",
            "**/*Receiver.class",
            "**/*Manifest*.class",
            "**/*Application*.class"
        )

        isDisabled = false
        excludes = excludeFiles
    }
}

// TODO
/*tasks.koverMergedVerify {
    // inclusion rules for classes
    includes = listOf("com.example.*")
    // exclusion rules for classes
    excludes = listOf("*.BuildConfig")

    rule {
        name = "Minimum number of lines covered"
        bound {
            minValue = 100000
            valueType = kotlinx.kover.api.VerificationValueType.COVERED_LINES_COUNT
        }
    }
    rule {
        // rule without a custom name
        bound {
            minValue = 1
            maxValue = 1000
            valueType = kotlinx.kover.api.VerificationValueType.MISSED_LINES_COUNT
        }
    }
    rule {
        name = "Minimal line coverage rate in percent"
        bound {
            minValue = 50
            // valueType is kotlinx.kover.api.VerificationValueType.COVERED_LINES_PERCENTAGE by default
        }
    }
}*/

// TODO
/*tasks.koverVerify {
    // inclusion rules for classes
    includes = listOf("com.example.*")
    // exclusion rules for classes
    excludes = listOf("*.BuildConfig")

    rule {
        name = "Minimal line coverage rate in percent"
        bound {
            minValue = 75
        }
    }
}*/
