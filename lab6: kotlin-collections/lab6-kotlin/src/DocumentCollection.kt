import java.io.File
import java.util.*
import kotlin.system.measureNanoTime

data class Doc(val series: String, val number: String) : Comparable<Doc> {
    init {
        require(series.matches(Regex("\\d{4}"))) { "Passport series must consist of 4 digits" }
        require(number.matches(Regex("\\d{6}"))) { "Passport number must consist of 6 digits" }
    }

    override fun compareTo(other: Doc): Int {
        val seriesComparison = series.compareTo(other.series)
        return if (seriesComparison != 0) seriesComparison else number.compareTo(other.number)
    }
}

abstract class DocumentCollection {
    abstract fun searchDoc(doc: Doc): Boolean

    fun measureSearchTime(doc: Doc): Pair<Boolean, Long> {
        var result: Boolean
        val time = measureNanoTime {
            result = searchDoc(doc)
        }
        return result to time
    }
}

class ArrayDocumentCollection(private val docs: Array<Doc>) : DocumentCollection() {
    override fun searchDoc(doc: Doc): Boolean {
        return docs.contains(doc)
    }
}

class ListDocumentCollection(private val docs: List<Doc>) : DocumentCollection() {
    override fun searchDoc(doc: Doc): Boolean {
        return docs.contains(doc)
    }
}

class BinaryListDocumentCollection(docs: List<Doc>) : DocumentCollection() {
    private val sortedDocs = docs.sortedWith(compareBy({ it.series }, { it.number }))

    override fun searchDoc(doc: Doc): Boolean {
        return Collections.binarySearch(sortedDocs, doc, compareBy({ it.series }, { it.number })) >= 0
    }
}

class HashSetDocumentCollection(private val docs: HashSet<Doc>) : DocumentCollection() {
    override fun searchDoc(doc: Doc): Boolean {
        return docs.contains(doc)
    }
}

class TreeSetDocumentCollection(private val docs: TreeSet<Doc>) : DocumentCollection() {
    override fun searchDoc(doc: Doc): Boolean {
        return docs.contains(doc)
    }
}

fun main() {
    val filePath = "lab6: kotlin-collections/lab6-kotlin/documents.txt"
    val file = File(filePath)
    if (!file.exists()) {
        println("File $filePath not found.")
        return
    }

    val lines = file.readLines()
    val docs = mutableListOf<Doc>()

    lines.forEach { line ->
        val parts = line.split(" ")
        if (parts.size == 2) {
            try {
                val doc = Doc(parts[0], parts[1])
                docs.add(doc)
            } catch (e: IllegalArgumentException) {
                println("Incorrect series or document number: $line")
            }
        } else {
            println("Incorrect line: $line")
        }
    }

    val arrayCollection = ArrayDocumentCollection(docs.toTypedArray())
    val listCollection = ListDocumentCollection(docs)
    val binaryListCollection = BinaryListDocumentCollection(docs)
    val hashSetCollection = HashSetDocumentCollection(HashSet(docs))
    val treeSetCollection = TreeSetDocumentCollection(TreeSet(docs))

    val collections = listOf(arrayCollection, listCollection, binaryListCollection, hashSetCollection, treeSetCollection)

    docs.forEach { doc ->
        val line = "${doc.series} ${doc.number}"
        println("Document $line is correct")

        collections.forEach { collection ->
            val (exists, time) = collection.measureSearchTime(doc)
            println("Document $line ${if (exists) "exists" else "does not exist"} in the collection ${collection::class.simpleName}, search time: $time ns")
        }

        println("--------------------------------------------------")
    }

    // calculate the average time of a positive and negative response for each collection
    collections.forEach { collection ->
        val positiveTimes = mutableListOf<Long>()
        val negativeTimes = mutableListOf<Long>()

        docs.forEach { doc ->
            val (exists, time) = collection.measureSearchTime(doc)
            if (exists) {
                positiveTimes.add(time)
            } else {
                negativeTimes.add(time)
            }
        }

        val averagePositiveTime = if (positiveTimes.isNotEmpty()) positiveTimes.average() else 0.0
        val averageNegativeTime = if (negativeTimes.isNotEmpty()) negativeTimes.average() else 0.0

        println("Average positive response time for ${collection::class.simpleName}: $averagePositiveTime ns")
        println("Average negative response time for ${collection::class.simpleName}: $averageNegativeTime ns")
    }
}