import java.util.*

class ArrayOperations {
    tailrec fun arrayOp(array: MutableList<Int>, operation: (Int, Int) -> Int, acc: Int = 0): Int =
        if (array.isEmpty()) acc else arrayOp(array.drop(1).toMutableList(), operation, operation(acc, array[0]))

    fun sum(array: MutableList<Int>) = arrayOp(array, { acc, elem -> acc + elem}, 0)
    fun prod(array: MutableList<Int>) = arrayOp(array, { acc, elem -> acc * elem}, 1)
    fun min(array: MutableList<Int>) = arrayOp(array, { acc, elem -> if (acc < elem) acc else elem}, array[0])
    fun max(array: MutableList<Int>) = arrayOp(array, { acc, elem -> if (acc > elem) acc else elem}, array[0])

    fun findMostFrequent(array: List<Int>): Int {
        fun frequencyMap(array: List<Int>, map: Map<Int, Int> = emptyMap()): Map<Int, Int> =
            if (array.isEmpty()) map
            else frequencyMap(array.drop(1), map + (array.first() to (map[array.first()] ?: 0) + 1))

        return frequencyMap(array).maxByOrNull { it.value }?.key ?: throw NoSuchElementException("Array is empty")
    }

    fun evenElementsRepeatedEvenTimes(array: List<Int>): List<Int> {
        fun frequencyMap(array: List<Int>, map: Map<Int, Int> = emptyMap()): Map<Int, Int> =
            if (array.isEmpty()) map
            else frequencyMap(array.drop(1), map + (array.first() to (map[array.first()] ?: 0) + 1))

        val freqMap = frequencyMap(array)
        return array.filter { it % 2 == 0 && (freqMap[it] ?: 0) % 2 == 0 }
    }

    fun readIntList(): List<Int> {
        println("Enter a list of integers separated by spaces:")
        return readLine()
            ?.split(" ")
            ?.mapNotNull { it.toIntOrNull() }
            ?: emptyList()
    }

    tailrec fun sumOfDigits(n: Int, acc: Int=0): Int = if (n == 0) acc else sumOfDigits(n / 10, acc + n % 10)

    fun filterNegativeNumbers(array: List<Int>) = array.filter { it >= 0 || sumOfDigits(-it) >= 10}

    fun main(args: Array<String>) {
        val array = readIntList()
        println("Given array: $array")
//        val array = List(1, 2, 3, 4, 5)
        println("Sum: ${sum(array.toMutableList())}")
        println("Product: ${prod(array.toMutableList())}")
        println("Min: ${min(array.toMutableList())}")
        println("Max: ${max(array.toMutableList())}")
        println("Most Frequent: ${findMostFrequent(array)}")
        println("Even elements repeated even times: ${evenElementsRepeatedEvenTimes(array)}")
        println("Filtered list: ${filterNegativeNumbers(array)}")
    }
}

fun main() = ArrayOperations().main(emptyArray())

