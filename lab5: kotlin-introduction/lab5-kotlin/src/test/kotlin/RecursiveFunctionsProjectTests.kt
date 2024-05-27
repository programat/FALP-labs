import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class RecursiveFunctionsProjectTest {

    private val functions = RecursiveFunctionsProject()

    @Test
    fun testProductOfDigitsTail() {
        assertEquals(6, functions.productOfDigits_tail(123))
        assertEquals(120, functions.productOfDigits_tail(12345))
    }

    @Test
    fun testCountOddDigitsGreaterThanThreeTail() {
        assertEquals(1, functions.countOddDigitsGreaterThanThree_tail(12345))
        assertEquals(3, functions.countOddDigitsGreaterThanThree_tail(56789))
    }

    @Test
    fun testGcdTail() {
        assertEquals(1, functions.gcd_tail(17, 13))
        assertEquals(6, functions.gcd_tail(54, 24))
    }

    @Test
    fun testSumPrimeDivisorsOfNumb() {
        assertEquals(7, functions.sumPrimeDivisorsOfNumb(10)) // 2 + 5
        assertEquals(19, functions.sumPrimeDivisorsOfNumb(34)) // 2 + 17
    }

    @Test
    fun testProductOfDivisorsWithGreaterDigitSum() {
        assertEquals(10, functions.productOfDivisorsWithGreaterDigitSum(10)) // 5 * 2
        assertEquals(24, functions.productOfDivisorsWithGreaterDigitSum(12)) // 6 * 4
    }

    @Test
    fun testSumDigits() {
        assertEquals(6, functions.sum_digits(123))
        assertEquals(15, functions.sum_digits(12345))
    }
}