data class TreeNode(val value: String, var left: TreeNode? = null, var right: TreeNode? = null)

class BinaryTree {
    var root: TreeNode? = null

    fun insert(value: String, comparator: (String, String) -> Int) {
        root = insertRec(root, value, comparator)
    }

    private fun insertRec(node: TreeNode?, value: String, comparator: (String, String) -> Int): TreeNode {
        if (node == null) {
            return TreeNode(value)
        }
        if (comparator(value, node.value) < 0) {
            node.left = insertRec(node.left, value, comparator)
        } else {
            node.right = insertRec(node.right, value, comparator)
        }
        return node
    }

    fun toSortedList(): List<String> {
        val result = mutableListOf<String>()
        inOrderTraversal(root, result)
        return result
    }

    private fun inOrderTraversal(node: TreeNode?, result: MutableList<String>) {
        if (node != null) {
            inOrderTraversal(node.left, result)
            result.add(node.value)
            inOrderTraversal(node.right, result)
        }
    }

    fun printTree() {
        printTreeRec(root, "", true)
    }

    private fun printTreeRec(node: TreeNode?, prefix: String, isLeft: Boolean) {
        if (node != null) {
            printTreeRec(node.right, "$prefix${if (isLeft) "│   " else "    "}", false)
            println("$prefix${if (isLeft) "└── " else "┌── "}${node.value}")
            printTreeRec(node.left, "$prefix${if (isLeft) "    " else "│   "}", true)
        }
    }
}

fun listToBinaryTree(list: List<String>, comparator: (String, String) -> Int): BinaryTree {
    val tree = BinaryTree()
    list.forEach { tree.insert(it, comparator) }
    return tree
}

fun binaryTreeToList(tree: BinaryTree): List<String> {
    return tree.toSortedList()
}

fun main() {
    val strings = listOf("apple", "elderberry", "cherry",  "banana", "date", "fig", "grape")

    // Example of sorting alphabetically
    val tree = listToBinaryTree(strings) { a, b -> a.compareTo(b) }
    val sortedList = binaryTreeToList(tree)
    println("Sorted list alphabetically: $sortedList")
    println("Tree alphabetically:")
    tree.printTree()

    // Example of sorting by word count
    val stringsWithWords = listOf("apple pie", "banana split", "cherry tart", "date cake", "elderberry jam", "fig pudding", "grape jelly")
    val treeByWordCount = listToBinaryTree(stringsWithWords) { a, b -> a.split(" ").size.compareTo(b.split(" ").size) }
    val sortedListByWordCount = binaryTreeToList(treeByWordCount)
    println("Sorted list by word count: $sortedListByWordCount")
    println("Tree by word count:")
    treeByWordCount.printTree()
}