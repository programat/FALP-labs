package test.kotlin

import RecursiveFunctionsInsteadCycle
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class RecursiveFunctionsInsteadCycleTests {

    private val functions = RecursiveFunctionsInsteadCycle()

    @Test
    fun testProductOfDigits_up() {
        assertEquals(1, functions.productOfDigits_up(1))
        assertEquals(2, functions.productOfDigits_up(12))
        assertEquals(6, functions.productOfDigits_up(123))
        assertEquals(24, functions.productOfDigits_up(1234))
        assertEquals(0, functions.productOfDigits_up(1024)) // Test case with zero
    }

    @Test
    fun testProductOfDigits_tail() {
        assertEquals(1, functions.productOfDigits_tail(1))
        assertEquals(2, functions.productOfDigits_tail(12))
        assertEquals(6, functions.productOfDigits_tail(123))
        assertEquals(24, functions.productOfDigits_tail(1234))
        assertEquals(0, functions.productOfDigits_tail(1024)) // Test case with zero
    }

    @Test
    fun testProductOfDigits_down() {
        assertEquals(1, functions.productOfDigits_down(1))
        assertEquals(2, functions.productOfDigits_down(12))
        assertEquals(6, functions.productOfDigits_down(123))
        assertEquals(24, functions.productOfDigits_down(1234))
        assertEquals(0, functions.productOfDigits_down(1024)) // Test case with zero
    }

    @Test
    fun testCountOddDigitsGreaterThanThree_up() {
        assertEquals(0, functions.countOddDigitsGreaterThanThree_up(1))
        assertEquals(0, functions.countOddDigitsGreaterThanThree_up(12))
        assertEquals(1, functions.countOddDigitsGreaterThanThree_up(135))
        assertEquals(2, functions.countOddDigitsGreaterThanThree_up(1357))
        assertEquals(3, functions.countOddDigitsGreaterThanThree_up(13579))
    }

    @Test
    fun testCountOddDigitsGreaterThanThree_tail() {
        assertEquals(0, functions.countOddDigitsGreaterThanThree_tail(1))
        assertEquals(0, functions.countOddDigitsGreaterThanThree_tail(12))
        assertEquals(1, functions.countOddDigitsGreaterThanThree_tail(135))
        assertEquals(2, functions.countOddDigitsGreaterThanThree_tail(1357))
        assertEquals(3, functions.countOddDigitsGreaterThanThree_tail(13579))
    }

    @Test
    fun testCountOddDigitsGreaterThanThree_down() {
        assertEquals(0, functions.countOddDigitsGreaterThanThree_down(1))
        assertEquals(0, functions.countOddDigitsGreaterThanThree_down(12))
        assertEquals(1, functions.countOddDigitsGreaterThanThree_down(135))
        assertEquals(2, functions.countOddDigitsGreaterThanThree_down(1357))
        assertEquals(3, functions.countOddDigitsGreaterThanThree_down(13579))
    }

    @Test
    fun testGcd_up() {
        assertEquals(1, functions.gcd_up(1, 1))
        assertEquals(1, functions.gcd_up(17, 13))
        assertEquals(6, functions.gcd_up(54, 24))
        assertEquals(12, functions.gcd_up(48, 180))
        assertEquals(5, functions.gcd_up(0, 5)) // Test case with zero
        assertEquals(5, functions.gcd_up(5, 0)) // Test case with zero
    }

    @Test
    fun testGcd_tail() {
        assertEquals(1, functions.gcd_tail(1, 1))
        assertEquals(1, functions.gcd_tail(17, 13))
        assertEquals(6, functions.gcd_tail(54, 24))
        assertEquals(12, functions.gcd_tail(48, 180))
        assertEquals(5, functions.gcd_tail(0, 5)) // Test case with zero
        assertEquals(5, functions.gcd_tail(5, 0)) // Test case with zero
    }
}