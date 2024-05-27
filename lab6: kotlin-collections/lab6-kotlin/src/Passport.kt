import java.time.LocalDate

data class Passport(
    val series: String,
    val number: String,
    val lastName: String,
    val firstName: String,
    val middleName: String,
    val gender: String,
    val birthDate: LocalDate,
    val issueDate: LocalDate,
    val issuedBy: String,
    val divisionCode: String,
    val expirationDate: LocalDate
) : Comparable<Passport> {

    init {
        require(series.matches(Regex("\\d{4}"))) { "Серия паспорта должна состоять из 4 цифр" }
        require(number.matches(Regex("\\d{6}"))) { "Номер паспорта должен состоять из 6 цифр" }
        require(gender.matches(Regex("[MF]"))) { "Пол должен быть указан как 'M' или 'F'" }
        require(divisionCode.matches(Regex("\\d{3}-\\d{3}"))) { "Код подразделения должен быть в формате 'XXX-XXX'" }
    }

    override fun toString(): String {
        return "Паспорт РФ:\n" +
                "Серия: $series\n" +
                "Номер: $number\n" +
                "Фамилия: $lastName\n" +
                "Имя: $firstName\n" +
                "Отчество: $middleName\n" +
                "Пол: ${if (gender == "M") "Мужской" else "Женский"}\n" +
                "Дата рождения: $birthDate\n" +
                "Дата выдачи: $issueDate\n" +
                "Кем выдан: $issuedBy\n" +
                "Код подразделения: $divisionCode\n" +
                "Действителен до: $expirationDate"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Passport

        if (series != other.series) return false
        if (number != other.number) return false

        return true
    }

    override fun hashCode(): Int {
        var result = series.hashCode()
        result = 31 * result + number.hashCode()
        return result
    }

    override fun compareTo(other: Passport): Int {
        val issueDateComparison = issueDate.compareTo(other.issueDate)
        if (issueDateComparison != 0) return issueDateComparison

        val seriesComparison = series.compareTo(other.series)
        if (seriesComparison != 0) return seriesComparison

        return number.compareTo(other.number)
    }
}

fun main() {
    val passport1 = Passport(
        series = "1234",
        number = "567890",
        lastName = "Иванов",
        firstName = "Иван",
        middleName = "Иванович",
        gender = "M",
        birthDate = LocalDate.of(1990, 1, 1),
        issueDate = LocalDate.of(2010, 1, 1),
        issuedBy = "УФМС России по г. Москве",
        divisionCode = "123-456",
        expirationDate = LocalDate.of(2020, 1, 1)
    )

    val passport2 = Passport(
        series = "1234",
        number = "567890",
        lastName = "Петров",
        firstName = "Петр",
        middleName = "Петрович",
        gender = "M",
        birthDate = LocalDate.of(1985, 5, 10),
        issueDate = LocalDate.of(2015, 5, 10),
        issuedBy = "УФМС России по г. Санкт-Петербургу",
        divisionCode = "789-012",
        expirationDate = LocalDate.of(2025, 5, 10)
    )

    val passport3 = Passport(
        series = "5678",
        number = "901234",
        lastName = "Сидорова",
        firstName = "Анна",
        middleName = "Ивановна",
        gender = "F",
        birthDate = LocalDate.of(1995, 10, 20),
        issueDate = LocalDate.of(2015, 10, 20),
        issuedBy = "УФМС России по Московской области",
        divisionCode = "345-678",
        expirationDate = LocalDate.of(2025, 10, 20)
    )

    println(passport1)
    println()

    println("passport1 == passport2: ${passport1 == passport2}")
    println("passport1 == passport3: ${passport1 == passport3}")
    println()

    val passports = listOf(passport1, passport2, passport3)
    println("Сортировка по дате выдачи:")
    passports.sorted().forEach { println(it) }
    println()

    println("Сортировка по серии и номеру:")
    passports.sortedWith(compareBy<Passport> { it.series }.thenBy { it.number }).forEach { println(it) }
    println()

    val passportSet = setOf(passport1, passport2, passport3)
    println("passport1 in passportSet: ${passport1 in passportSet}")
    println("passport2 in passportSet: ${passport2 in passportSet}")
    println()

    val passportTreeSet = sortedSetOf(passport1, passport2, passport3)
    println("passport1 in passportTreeSet: ${passport1 in passportTreeSet}")
    println("passport3 in passportTreeSet: ${passport3 in passportTreeSet}")
}