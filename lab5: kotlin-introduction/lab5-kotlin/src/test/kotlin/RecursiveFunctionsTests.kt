package test.kotlin

import RecursiveFunctions
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*

class RecursiveFunctionsTests {
    private val recursiveFunctions = RecursiveFunctions()

    @Test
    fun testMax() {
        assertEquals(3, recursiveFunctions.max(1, 2, 3))
        assertEquals(3, recursiveFunctions.max(3, 2, 1))
        assertEquals(3, recursiveFunctions.max(1, 3, 2))
        assertEquals(3, recursiveFunctions.max(3, 3, 3))
    }

    @Test
    fun testFactUp() {
        assertEquals(1, recursiveFunctions.fact_up(0))
        assertEquals(1, recursiveFunctions.fact_up(1))
        assertEquals(2, recursiveFunctions.fact_up(2))
        assertEquals(6, recursiveFunctions.fact_up(3))
        assertEquals(24, recursiveFunctions.fact_up(4))
    }

    @Test
    fun testFactDown() {
        assertEquals(0, recursiveFunctions.fact_down(0))
        assertEquals(1, recursiveFunctions.fact_down(1))
        assertEquals(2, recursiveFunctions.fact_down(2))
        assertEquals(6, recursiveFunctions.fact_down(3))
        assertEquals(24, recursiveFunctions.fact_down(4))
    }

    @Test
    fun testSumDigitsUp() {
        assertEquals(1, recursiveFunctions.sum_digits_up(1))
        assertEquals(3, recursiveFunctions.sum_digits_up(12))
        assertEquals(6, recursiveFunctions.sum_digits_up(123))
        assertEquals(10, recursiveFunctions.sum_digits_up(1234))
    }

    @Test
    fun testSumDigitsDown() {
        assertEquals(1, recursiveFunctions.sum_digits_down(1))
        assertEquals(3, recursiveFunctions.sum_digits_down(12))
        assertEquals(6, recursiveFunctions.sum_digits_down(123))
        assertEquals(10, recursiveFunctions.sum_digits_down(1234))
    }

    @Test
    fun testCallFun() {
        val sumDigits = recursiveFunctions.call_fun(true)
        val factDown = recursiveFunctions.call_fun(false)
        assertEquals(6, sumDigits(123))
        assertEquals(6, factDown(3))
    }

    @Test
    fun testSumDigits() {
        assertEquals(1, recursiveFunctions.sum_digits(1))
        assertEquals(3, recursiveFunctions.sum_digits(12))
        assertEquals(6, recursiveFunctions.sum_digits(123))
        assertEquals(10, recursiveFunctions.sum_digits(1234))
    }

    @Test
    fun testProdDigits() {
        assertEquals(1, recursiveFunctions.prod_digits(1))
        assertEquals(2, recursiveFunctions.prod_digits(12))
        assertEquals(6, recursiveFunctions.prod_digits(123))
        assertEquals(24, recursiveFunctions.prod_digits(1234))
    }

    @Test
    fun testMaxDigit() {
        assertEquals(1, recursiveFunctions.max_digit(1))
        assertEquals(2, recursiveFunctions.max_digit(12))
        assertEquals(3, recursiveFunctions.max_digit(123))
        assertEquals(4, recursiveFunctions.max_digit(1234))
    }

    @Test
    fun testMinDigit() {
        assertEquals(1, recursiveFunctions.min_digit(1))
        assertEquals(1, recursiveFunctions.min_digit(12))
        assertEquals(1, recursiveFunctions.min_digit(123))
        assertEquals(1, recursiveFunctions.min_digit(1234))
    }
}