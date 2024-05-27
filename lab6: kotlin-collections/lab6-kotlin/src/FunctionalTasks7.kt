class FunctionalTasks7 {

    // 1. Given two sequences, find the longest common subsequence.
    fun longestCommonSubsequence(seq1: List<Int>, seq2: List<Int>): List<Int> {
        fun lcsHelper(seq1: List<Int>, seq2: List<Int>, acc: List<Int> = emptyList()): List<Int> =
            if (seq1.isEmpty() || seq2.isEmpty()) {
                acc
            } else if (seq1.last() == seq2.last()) {
                lcsHelper(seq1.dropLast(1), seq2.dropLast(1), listOf(seq1.last()) + acc)
            } else {
                val dropSeq1 = lcsHelper(seq1.dropLast(1), seq2, acc)
                val dropSeq2 = lcsHelper(seq1, seq2.dropLast(1), acc)
                if (dropSeq1.size > dropSeq2.size) dropSeq1 else dropSeq2
            }
        return lcsHelper(seq1, seq2)
    }

    // 6. Sort the entered list of tuples of length 5 in ascending lexicographic order,
    // and in the new list there can only be tuples of digits, in the final list write the numeric representation of the resulting tuple
    fun sortAndConvertTuples(tuples: List<List<Int>>): List<Int> =
        tuples
            .filter { it.all { digit -> digit in 0..9 } }
            .sortedWith(compareBy({ it[0] }, { it[1] }, { it[2] }, { it[3] }, { it[4] }))
            .map { it.joinToString("").toInt() }

    // 9. For the entered list, build a new list, which will include only those elements that
    // - are greater than the sum of all previous ones in the original list,
    // - are a perfect square of one of the elements of the original list,
    // - are divisible by all previous elements of the original list.
    // Include in the final list a tuple (number, sum of previous, number of elements in the list greater than the given one)
    fun buildNewList(array: List<Int>): List<Triple<Int, Int, Int>> {
        fun isPerfectSquare(n: Int): Boolean {
            val sqrt = kotlin.math.sqrt(n.toDouble()).toInt()
            return sqrt * sqrt == n
        }

        fun helper(list: List<Int>, prev: List<Int> = emptyList(), acc: Int = 0, result: List<Triple<Int, Int, Int>> = emptyList()): List<Triple<Int, Int, Int>> =
            if (list.isEmpty()) {
                result
            } else {
                val head = list.first()
                val newAcc = acc + head
                val countGreaterThanHead = list.count { it > head }
                val newResult = if (head > acc && isPerfectSquare(head) && prev.all { head % it == 0 }) {
                    result + Triple(head, acc, countGreaterThanHead)
                } else {
                    result
                }
                helper(list.drop(1), prev + head, newAcc, newResult)
            }
        return helper(array)
    }
}

fun main() {
    val tasks = FunctionalTasks7()

    // 1. Given two sequences, find the longest common subsequence.
    val seq1 = listOf(1, 2, 3, 4, 1)
    val seq2 = listOf(3, 4, 1, 2, 1, 3)
    val lcs = tasks.longestCommonSubsequence(seq1, seq2)
    println("Longest common subsequence: $lcs")

    // 6. Sort the entered list of tuples of length 5 in ascending lexicographic order
    val tuples = listOf(
        listOf(7, 3, 4, 5, 6),
        listOf(2, 3, 4, 6, 7),
        listOf(2, 3, 4, 5, 6),
        listOf(4, 3, 10, 4, 5)
    )
    val sortedTuples = tasks.sortAndConvertTuples(tuples)
    println("Sorted tuples: $sortedTuples")

    // 9. For the entered list, build a new list
    val testArray = listOf(1, 3, 9, 81, 25, 36, 49, 64, 81, 100)
    val newList = tasks.buildNewList(testArray)
    println("New list: $newList")
}