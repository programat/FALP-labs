package test.kotlin

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*

import RecursiveFunctionsProject

class RecursiveFunctionsProjectTests {

    private val functions = RecursiveFunctionsProject()

    @Test
    fun testProductOfDigits_tail() {
        assertEquals(6, functions.productOfDigits_tail(123))
        assertEquals(120, functions.productOfDigits_tail(12345))
    }

    @Test
    fun testProductOfDigits_up() {
        assertEquals(6, functions.productOfDigits_tail(123))
        assertEquals(120, functions.productOfDigits_tail(12345))
    }

    @Test
    fun testCountOddDigitsGreaterThanThree_tail() {
        assertEquals(1, functions.countOddDigitsGreaterThanThree_tail(12345))
        assertEquals(3, functions.countOddDigitsGreaterThanThree_tail(56789))
    }

    @Test
    fun testCountOddDigitsGreaterThanThree_up() {
        assertEquals(1, functions.countOddDigitsGreaterThanThree_up(12345))
        assertEquals(3, functions.countOddDigitsGreaterThanThree_up(56789))
    }

    @Test
    fun testGcd_tail() {
        assertEquals(1, functions.gcd_tail(17, 13))
        assertEquals(6, functions.gcd_tail(54, 24))
    }

    @Test
    fun testGcd_up() {
        assertEquals(1, functions.gcd_up(17, 13))
        assertEquals(6, functions.gcd_up(54, 24))
    }
}