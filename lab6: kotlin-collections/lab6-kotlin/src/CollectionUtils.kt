class CollectionUtils {
    // task 2 ---
    fun countSquares(list: List<Int>): Int =
        list.count { element ->
            list.any { it * it == element }
        }

    fun sumOfDigits(n: Int): Int =
        if (n == 0) 0 else n % 10 + sumOfDigits(n / 10)

    fun countDivisors(n: Int): Int =
        (1..n).count { n % it == 0 }

    fun zipLists(list1: List<Int>, list2: List<Int>, list3: List<Int>): List<Triple<Int, Int, Int>> {
        val sortedList1 = list1.sortedDescending()
        val sortedList2 = list2.sortedWith(compareBy({ sumOfDigits(it) }, { -kotlin.math.abs(it) }))
        val sortedList3 = list3.sortedWith(compareBy({ -countDivisors(it) }, { -kotlin.math.abs(it) }))

        return sortedList1.zip(sortedList2).zip(sortedList3).map { Triple(it.first.first, it.first.second, it.second) }
    }

    fun main() {

        val list = mutableListOf(1, 2, 3, 4, 9, 16, 25)
        println("he number of elements that can be the square of some element in the list: ${countSquares(list)}")

        val listA = mutableListOf(5, 3, 8, 1)
        val listB = mutableListOf(12, 23, 34, 45)
        val listC = mutableListOf(6, 28, 15, 10)

        val result = zipLists(listA, listB, listC)
        println("List of tuples: $result")
    }

}

fun main() = CollectionUtils().main()
