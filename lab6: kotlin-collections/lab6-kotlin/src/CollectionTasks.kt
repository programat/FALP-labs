class CollectionTasks {
    // task 3 ---

    // 1.6. Cyclic shift of array elements to the left by three positions
    fun shiftLeft(array: List<Int>, positions: Int): List<Int> =
        array.drop( positions % array.size) + array.take( positions % array.size)

    // 1.7. Cyclic shift of array elements to the right by two positions
    fun shiftRight(array: List<Int>, positions: Int): List<Int> =
        array.takeLast(positions % array.size) + array.dropLast(positions % array.size)


    // 1.18. Finding elements located before the first minimum
    fun elementsBeforeFirstMin(array: List<Int>): List<Int> {
        val minIndex = array.indexOf(array.minOrNull() ?: return emptyList())
        return array.take(minIndex)
    }

    // 1.24. Finding the two largest elements
    fun twoLargestElements(array: List<Int>): List<Int> =
        array.sortedDescending().take(2)

    // 1.27. Cyclic shift of array elements to the left by one position
    fun shiftLeftByOne(array: List<Int>): List<Int> =
        array.drop(1) + array.take(1)


    // 1.36. Finding the maximum odd element
    fun maxOddElement(array: List<Int>): Int? =
        array.filter { it % 2 != 0 }.maxOrNull()


    fun main() {
        val array = listOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)

        println("Initial array: $array")

        // 1.6
        val shiftedLeft = shiftLeft(array, 3)
        println("Array after shifting left by 3 positions: $shiftedLeft")

        // 1.7
        val shiftedRight = shiftRight(array, 2)
        println("Array after shifting right by 2 positions: $shiftedRight")

        // 1.18
        val arrayWithMin = listOf(5, 2, 8, 1, 9, 3)
        val elementsBeforeMin = elementsBeforeFirstMin(arrayWithMin)
        println("Elements before the first minimum: $elementsBeforeMin")

        // 1.24
        val largestElements = twoLargestElements(arrayWithMin)
        println("Two largest elements: $largestElements")

        // 1.27
        val shiftedLeftByOne = shiftLeftByOne(array)
        println("Array after shifting left by 1 position: $shiftedLeftByOne")

        // 1.36
        val maxOdd = maxOddElement(arrayWithMin)
        println("Maximum odd element: $maxOdd")
    }
}

fun main() = CollectionTasks().main()