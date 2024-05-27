import java.time.LocalDate

data class User(
    val lastName: String,
    val firstName: String,
    val middleName: String,
    val birthDate: LocalDate,
    val telegram: String
) : Comparable<User> {
    override fun compareTo(other: User): Int = this.birthDate.compareTo(other.birthDate)
}

fun main() {
    val user1 = User("Ivanov", "Ivan", "Ivanovich", LocalDate.of(1990, 1, 1), "@ivanov")
    val user2 = User("Petrov", "Petr", "Petrovich", LocalDate.of(1985, 5, 15), "@petrov")
    val user3 = User("Sidorov", "Sidr", "Sidorovich", LocalDate.of(2000, 10, 10), "@sidorov")

    val hashSet = hashSetOf(user1, user2, user3)
    val treeSet = sortedSetOf(user1, user2, user3)

    println("HashSet: $hashSet")
    println("TreeSet: $treeSet")
}