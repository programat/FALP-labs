package test.kotlin

import Task6FileFuncs
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import java.io.File

class Task6FileFuncsTests {

    private val inputFilePath = "test_input.txt"
    private val outputFilePath = "test_output.txt"
    private val task6FileFuncs = Task6FileFuncs()

    @BeforeEach
    fun setUp() {
        // Удаляем файлы перед каждым тестом, если они существуют
        File(inputFilePath).delete()
        File(outputFilePath).delete()
    }

    @Test
    fun testProcessFileWithValidData() {
        val inputContent = """
            123 productOfDigits
            56789 countOddDigitsGreaterThanThree
            54 24 gcd
        """.trimIndent()
        File(inputFilePath).writeText(inputContent)

        task6FileFuncs.processFile(inputFilePath, outputFilePath)

        val expectedOutput = """
            123 productOfDigits 6
            56789 countOddDigitsGreaterThanThree 3
            54 24 gcd 6
        """.trimIndent()
        val actualOutput = File(outputFilePath).readText().trim()

        assertEquals(expectedOutput, actualOutput)
    }

    @Test
    fun testProcessFileWithInvalidNumberFormat() {
        val inputContent = """
            abc productOfDigits
            56789 countOddDigitsGreaterThanThree
        """.trimIndent()
        File(inputFilePath).writeText(inputContent)

        task6FileFuncs.processFile(inputFilePath, outputFilePath)

        val expectedOutput = """
            abc productOfDigits Error: Invalid number format
            56789 countOddDigitsGreaterThanThree 3
        """.trimIndent()
        val actualOutput = File(outputFilePath).readText().trim()

        assertEquals(expectedOutput, actualOutput)
    }

    @Test
    fun testProcessFileWithInvalidLineFormat() {
        val inputContent = """
            123 productOfDigits extra
            56789 countOddDigitsGreaterThanThree
        """.trimIndent()
        File(inputFilePath).writeText(inputContent)

        task6FileFuncs.processFile(inputFilePath, outputFilePath)

        val expectedOutput = """
            123 productOfDigits extra Error: Invalid number format
            56789 countOddDigitsGreaterThanThree 3
        """.trimIndent()
        val actualOutput = File(outputFilePath).readText().trim()

        assertEquals(expectedOutput, actualOutput)
    }

    @Test
    fun testProcessFileWithFunctionRequiringTwoArguments() {
        val inputContent = """
            54 gcd
        """.trimIndent()
        File(inputFilePath).writeText(inputContent)

        task6FileFuncs.processFile(inputFilePath, outputFilePath)

        val expectedOutput = """
            54 gcd Error: Function requires two arguments
        """.trimIndent()
        val actualOutput = File(outputFilePath).readText().trim()

        assertEquals(expectedOutput, actualOutput)
    }

    @Test
    fun testProcessFileWithValidTwoArgumentFunction() {
        val inputContent = """
            54 24 gcd
        """.trimIndent()
        File(inputFilePath).writeText(inputContent)

        task6FileFuncs.processFile(inputFilePath, outputFilePath)

        val expectedOutput = """
            54 24 gcd 6
        """.trimIndent()
        val actualOutput = File(outputFilePath).readText().trim()

        assertEquals(expectedOutput, actualOutput)
    }
}