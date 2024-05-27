class FunctionalTasks6 {

    // 1.37 Given an integer array. Print the indices of elements that are less than their left neighbor, and the count of such numbers.
    fun indicesOfElementsLessThanLeftNeighbor(array: List<Int>): Pair<List<Int>, Int> {
        val indices = array.indices.drop(1).filter { array[it] < array[it - 1] }
        return indices to indices.size
    }

    // 1.46 Given an integer array. It is necessary to first output its positive elements, and then - negative.
    fun separatePositiveAndNegative(array: List<Int>): List<Int> {
        val positives = array.filter { it > 0 }
        val negatives = array.filter { it < 0 }
        return positives + negatives
    }

    // 1.49 For the entered list of positive numbers, build a list of all positive prime divisors of the list elements without repetitions.
    fun positivePrimeDivisors(array: List<Int>): List<Int> {
        fun isPrime(n: Int): Boolean =
            n > 1 && (2 until n).none { n % it == 0 }

        fun primeDivisors(n: Int): List<Int> =
            (2..n).filter { n % it == 0 && isPrime(it) }
        return array.flatMap { primeDivisors(it) }.distinct()
    }

    // 1.57 For the entered list, find the number of such elements that are greater than the sum of all previous ones
    fun countElementsGreaterThanSumOfPrevious(array: List<Int>): Int {
        fun helper(list: List<Int>, acc: Int = 0, count: Int = 0): Int {
            if (list.isEmpty()) return count
            val head = list.first()
            val newCount = if (head > acc) count + 1 else count
            return helper(list.drop(1), acc + head, newCount)
        }
        return helper(array)
    }
}

fun main() {
    val tasks = FunctionalTasks6()

    val array1 = listOf(3, 1, 4, 1, 5, 9, 2, 6, 5)
    val (indices, count) = tasks.indicesOfElementsLessThanLeftNeighbor(array1)
    println("Indices of elements that are less than their left neighbor: $indices, count: $count")

    val array2 = listOf(-1, 2, -3, 4, -5, 6)
    val separated = tasks.separatePositiveAndNegative(array2)
    println("Positive elements, then negative: $separated")

    val array3 = listOf(12, 15, 18, 20)
    val primeDivisors = tasks.positivePrimeDivisors(array3)
    println("Positive prime divisors without repetitions: $primeDivisors")

    val array4 = listOf(1, 2, 3, 4, 5, 6)
    val countGreaterThanSum = tasks.countElementsGreaterThanSumOfPrevious(array4)
    println("Number of elements that are greater than the sum of all previous ones: $countGreaterThanSum")
}