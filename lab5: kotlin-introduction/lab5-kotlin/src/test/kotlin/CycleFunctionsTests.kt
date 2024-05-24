package test.kotlin

import CycleFunctions
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class CycleFunctionsTests {

    private val operations = CycleFunctions()

    @Test
    fun testProdDigits() {
        assertEquals(1, operations.productOfDigits(1))
        assertEquals(2, operations.productOfDigits(12))
        assertEquals(6, operations.productOfDigits(123))
        assertEquals(24, operations.productOfDigits(1234))
        assertEquals(0, operations.productOfDigits(1024)) // Test case with zero
    }

    @Test
    fun testCountOddDigitsGreaterThanThree() {
        assertEquals(0, operations.countOddDigitsGreaterThanThree(2))
        assertEquals(0, operations.countOddDigitsGreaterThanThree(12))
        assertEquals(0, operations.countOddDigitsGreaterThanThree(123))
        assertEquals(1, operations.countOddDigitsGreaterThanThree(1235))
        assertEquals(3, operations.countOddDigitsGreaterThanThree(13579)) // All odd digits
    }

    @Test
    fun testGcd() {
        assertEquals(1, operations.gcd(1, 1))
        assertEquals(1, operations.gcd(17, 13))
        assertEquals(6, operations.gcd(54, 24))
        assertEquals(12, operations.gcd(48, 180))
        assertEquals(5, operations.gcd(0, 5)) // Test case with zero
        assertEquals(5, operations.gcd(5, 0)) // Test case with zero
    }
}