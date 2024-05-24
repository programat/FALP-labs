import java.io.File
import java.io.IOException

class Task6FileFuncs {

    private val functionsProject = RecursiveFunctionsProject()

    // Function to get a function by its name
    fun getFunctionByName(name: String): (Int) -> Int? {
        val functions: Map<String, (Int) -> Int?> = mapOf(
            "productOfDigits" to { n -> functionsProject.productOfDigits(n) },
            "countOddDigitsGreaterThanThree" to { n -> functionsProject.countOddDigitsGreaterThanThree(n) },
            "gcd" to { n -> null } // gcd requires two arguments, so we return null
        )
        return functions[name] ?: { null }
    }

    // Function to get a function by its name for two arguments
    fun getFunctionByNameForTwoArgs(name: String): (Int, Int) -> Int? {
        val functions: Map<String, (Int, Int) -> Int?> = mapOf(
            "gcd" to { a, b -> functionsProject.gcd(a, b) }
        )
        return functions[name] ?: { _, _ -> null }
    }

    // Function to process a file
    fun processFile(inputFilePath: String, outputFilePath: String) {
        val inputFile = File(inputFilePath)
        val outputFile = File(outputFilePath)

        if (!inputFile.exists()) {
            println("Input file does not exist.")
            return
        }

        try {
            val lines = inputFile.readLines()
            val results = mutableListOf<String>()

            for (line in lines) {
                val parts = line.split(" ")
                if (parts.size == 2) {
                    val number = parts[0].toIntOrNull()
                    val functionName = parts[1]

                    if (number != null) {
                        val function = getFunctionByName(functionName)
                        val result = function(number)
                        if (result != null) {
                            results.add("$number $functionName $result")
                        } else {
                            results.add("$number $functionName Error: Function requires two arguments")
                        }
                    } else {
                        results.add("$line Error: Invalid number format")
                    }
                } else if (parts.size == 3) {
                    val number1 = parts[0].toIntOrNull()
                    val number2 = parts[1].toIntOrNull()
                    val functionName = parts[2]

                    if (number1 != null && number2 != null) {
                        val function = getFunctionByNameForTwoArgs(functionName)
                        val result = function(number1, number2)
                        if (result != null) {
                            results.add("$number1 $number2 $functionName $result")
                        } else {
                            results.add("$number1 $number2 $functionName Error: Function not found")
                        }
                    } else {
                        results.add("$line Error: Invalid number format")
                    }
                } else {
                    results.add("$line Error: Invalid line format")
                }
            }

            outputFile.writeText(results.joinToString("\n"))
            println("Results written to $outputFilePath")
        } catch (e: IOException) {
            println("Error reading or writing file: ${e.message}")
        }
    }
}

// Entry point of the program
fun main() {
    val inputFilePath = "lab5: kotlin-introduction/lab5-kotlin/data/input.txt"
    val outputFilePath = "lab5: kotlin-introduction/lab5-kotlin/data/output.txt"

    // Example of input file content
    val exampleInput = """
        123 productOfDigits
        56789 countOddDigitsGreaterThanThree
        54 24 gcd
    """.trimIndent()

    // Writing the example to the input.txt file
    File(inputFilePath).writeText(exampleInput)

    val task6FileFuncs = Task6FileFuncs()
    task6FileFuncs.processFile(inputFilePath, outputFilePath)
}