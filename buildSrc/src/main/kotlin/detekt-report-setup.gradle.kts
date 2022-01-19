import groovy.xml.MarkupBuilder
import groovy.xml.XmlSlurper
import groovy.xml.slurpersupport.NodeChild

val Project.reportOutputDir get() = "${buildDir.absolutePath}/reports/detekt"
val Project.outputReportFilePath get() = "${reportOutputDir}/output.xml"

rootProject.tasks.register("mergeDetektReports") {
    group = "report"
    description = "merge detekt reports output each modules."

    subprojects
        .asSequence()
        .filter { p -> p.hasProperty("detekt") }
        .onEach { p ->
            val targetXml = file(p.outputReportFilePath)
            if (targetXml.exists()) {
                targetXml.delete()
            }
        }
        .mapNotNull { p ->
            val taskName = if (p.plugins.hasPlugin("kotlin-multiplatform")) {
                "detektAndroidDebug"
            } else {
                "detekt"
            }
            p.getTasksByName(taskName, false).firstOrNull()?.let { t ->
                ":${p.displayName.drop(10).dropLast(1)}:${t.name}"
            }
        }
        .toList()
        .also { setDependsOn(it) }

    val mergedOutputReportDirPath = reportOutputDir
    val mergedOutputReportDir = file(mergedOutputReportDirPath)
    doFirst {
        if (mergedOutputReportDir.exists()) {
            mergedOutputReportDir.deleteRecursively()
        }
        mergedOutputReportDir.mkdirs()

        subprojects.forEach { p ->
            copy {
                from(file(p.outputReportFilePath))
                into(file("$mergedOutputReportDirPath/${p.name}"))
            }
        }
    }

    doLast {
        file("$mergedOutputReportDirPath/merged.xml").writer().use { w ->
            MarkupBuilder(w).apply {
                doubleQuotes = true

                mkp.xmlDeclaration(mapOf("version" to "1.0", "encoding" to "utf-8"))
                withGroovyBuilder {
                    "checkstyle"("version" to "4.3") {
                        file(mergedOutputReportDirPath).listFiles()
                            ?.asSequence()
                            ?.filter(File::isDirectory)
                            ?.flatMap { reportDir ->
                                XmlSlurper()
                                    .parse(file("${reportDir.absolutePath}/output.xml"))
                                    .children()
                                    .list()
                            }
                            ?.filterIsInstance<NodeChild>()
                            ?.map { fileProperty ->
                                fileProperty.children()
                            }
                            ?.forEach { errorProperties ->
                                val errorOutput = {
                                    errorProperties
                                        .filterIsInstance<NodeChild>()
                                        .forEach { e ->
                                            "error"(
                                                "line" to e.getProperty("@line"),
                                                "column" to e.getProperty("@column"),
                                                "severity" to e.getProperty("@severity"),
                                                "message" to e.getProperty("@message"),
                                                "source" to e.getProperty("@source"),
                                            )
                                        }
                                }
                                "file"("name" to errorProperties.parent().getProperty("@name")) {
                                    errorOutput()
                                }
                            }
                    }
                }
            }
        }
    }
}