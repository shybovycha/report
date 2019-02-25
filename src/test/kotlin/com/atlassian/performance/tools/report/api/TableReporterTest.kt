package com.atlassian.performance.tools.report.api

import com.atlassian.performance.tools.report.api.result.LocalScalingResult
import org.assertj.core.api.Assertions
import org.junit.Test
import java.io.File
import java.nio.file.Paths

class TableReporterTest {

    @Test
    fun shouldCreateTableReport() {
        val actualOutput = Paths.get("build/actual-aui-table.html").toFile()

        val results = LocalScalingResult(Paths.get("DSR")).loadAll()
        TableReporter(
                output = actualOutput,
                labels = results[0].actionLabels.toList()
        ).report(results.map { it.actionStats })

        val expectedOutput = File(javaClass.getResource("expected-aui-table.html").toURI())
        Assertions.assertThat(actualOutput).hasSameContentAs(expectedOutput)
    }
}