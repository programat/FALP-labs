import java.math.BigDecimal
import kotlin.math.pow

class ProjecteulerTask {
    // Tail recursive function to generate all distinct powers
    tailrec fun generatePowers(a: Int, b: Int, n: Int, m: Int, acc: Set<BigDecimal> = emptySet()): Set<BigDecimal> =
        if (a > n) acc else if (b > m) generatePowers(a + 1, 2, n, m, acc) else
            generatePowers(a, b + 1, n, m, acc + BigDecimal(a.toDouble().pow(b)))

    // Function to count distinct powers
    fun countDistinctPowers(n: Int, m: Int): Int = generatePowers(2, 2, n, m).size

    // Main function to interact with the user
    fun main(args: Array<String>) {
        val n = 100
        val m = 100
        println("Number of distinct terms: ${countDistinctPowers(n, m)}")
    }
}

// Entry point of the program
fun main() = ProjecteulerTask().main(emptyArray())
