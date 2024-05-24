import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class RecursiveFunctionsProjectTests {

    private val functions = RecursiveFunctionsProject()

    @Test
    fun testMax() {
        assertEquals(3, functions.max(1, 2, 3))
        assertEquals(3, functions.max(3, 2, 1))
        assertEquals(3, functions.max(2, 3, 1))
    }

    @Test
    fun testFactUp() {
        assertEquals(1, functions.fact_up(0))
        assertEquals(1, functions.fact_up(1))
        assertEquals(2, functions.fact_up(2))
        assertEquals(6, functions.fact_up(3))
    }

    @Test
    fun testFactDown() {
        assertEquals(0, functions.fact_down(0))
        assertEquals(1, functions.fact_down(1))
        assertEquals(2, functions.fact_down(2))
        assertEquals(6, functions.fact_down(3))
    }

    @Test
    fun testSumDigitsUp() {
        assertEquals(6, functions.sum_digits_up(123))
        assertEquals(15, functions.sum_digits_up(12345))
    }

    @Test
    fun testSumDigitsDown() {
        assertEquals(6, functions.sum_digits_down(123))
        assertEquals(15, functions.sum_digits_down(12345))
    }

    @Test
    fun testProductOfDigits() {
        assertEquals(6, functions.productOfDigits(123))
        assertEquals(120, functions.productOfDigits(12345))
    }

    @Test
    fun testCountOddDigitsGreaterThanThree() {
        assertEquals(1, functions.countOddDigitsGreaterThanThree(12345))
        assertEquals(3, functions.countOddDigitsGreaterThanThree(56789))
    }

    @Test
    fun testGcd() {
        assertEquals(1, functions.gcd(17, 13))
        assertEquals(6, functions.gcd(54, 24))
    }

    @Test
    fun testMaxDigit() {
        assertEquals(3, functions.max_digit(123))
        assertEquals(9, functions.max_digit(56789))
    }

    @Test
    fun testMinDigit() {
        assertEquals(1, functions.min_digit(123))
        assertEquals(5, functions.min_digit(56789))
    }

    @Test
    fun testSumDigits() {
        assertEquals(6, functions.sum_digits(123))
        assertEquals(15, functions.sum_digits(12345))
    }

    @Test
    fun testProdDigits() {
        assertEquals(6, functions.prod_digits(123))
        assertEquals(120, functions.prod_digits(12345))
    }
}