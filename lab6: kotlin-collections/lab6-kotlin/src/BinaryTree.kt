data class TreeNode(val value: String, val left: TreeNode? = null, val right: TreeNode? = null)

fun insertRec(node: TreeNode?, value: String, comparator: (String, String) -> Int): TreeNode =
    node?.let {
        if (comparator(value, it.value) < 0) {
            it.copy(left = insertRec(it.left, value, comparator))
        } else {
            it.copy(right = insertRec(it.right, value, comparator))
        }
    } ?: TreeNode(value)

fun toSortedList(node: TreeNode?): List<String> =
    node?.let {
        toSortedList(it.left) + it.value + toSortedList(it.right)
    } ?: emptyList()

fun printTreeRec(node: TreeNode?, prefix: String = "", isLeft: Boolean = true): String =
    node?.let {
        val right = printTreeRec(it.right, "$prefix${if (isLeft) "│   " else "    "}", false)
        val value = "$prefix${if (isLeft) "└── " else "┌── "}${it.value}\n"
        val left = printTreeRec(it.left, "$prefix${if (isLeft) "    " else "│   "}", true)
        right + value + left
    } ?: ""

fun listToBinaryTree(list: List<String>, comparator: (String, String) -> Int): TreeNode? =
    list.fold(null as TreeNode?) { acc, value -> insertRec(acc, value, comparator) }

fun binaryTreeToList(tree: TreeNode?): List<String> = toSortedList(tree)

fun main() {
    val strings = listOf("apple", "elderberry", "cherry", "banana", "date", "fig", "grape")

    // Example of sorting alphabetically
    val tree = listToBinaryTree(strings) { a, b -> a.compareTo(b) }
    val sortedList = binaryTreeToList(tree)
    println("Sorted list alphabetically: $sortedList")
    println("Tree alphabetically:")
    println(printTreeRec(tree))

    // Example of sorting by word count
    val stringsWithWords = listOf("apple pie", "banana split", "cherry tart", "date cake", "elderberry jam", "fig pudding", "grape jelly")
    val treeByWordCount = listToBinaryTree(stringsWithWords) { a, b -> a.split(" ").size.compareTo(b.split(" ").size) }
    val sortedListByWordCount = binaryTreeToList(treeByWordCount)
    println("Sorted list by word count: $sortedListByWordCount")
    println("Tree by word count:")
    println(printTreeRec(treeByWordCount))
}