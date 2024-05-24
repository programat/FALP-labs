import java.lang.System.`in`
import java.util.*

class RecursiveFunctionsInsteadCycle {
    // Function to calculate product of digits in an upward manner
    fun productOfDigits_up(n: Int): Int = if (n < 10) n else productOfDigits_up(n / 10) * (n % 10)

    // Tail recursive function to calculate product of digits
    tailrec fun productOfDigits_tail(n: Int, acc: Int = 1): Int =
        if (n < 10) n * acc else productOfDigits_tail(n / 10, n % 10 * acc)
    // Helper function to calculate product of digits in a downward manner
    fun productOfDigits_down(n: Int): Int = productOfDigits_tail(n, 1)

    // Function to count the number of odd digits greater than three in an upward manner
    fun countOddDigitsGreaterThanThree_up(number: Int): Int =
        if (number < 10) if (number > 3 && number % 2 != 0) 1 else 0
        else countOddDigitsGreaterThanThree_up(number / 10) + if (number % 10 > 3 && number % 10 % 2 != 0) 1 else 0

    // Tail recursive function to count the number of odd digits greater than three
    fun countOddDigitsGreaterThanThree_tail(number: Int, acc: Int = 0): Int =
        if (number < 10) acc + if (number > 3 && number % 2 != 0) 1 else 0
        else countOddDigitsGreaterThanThree_tail(number / 10, acc + if (number % 10 > 3 && number % 10 % 2 != 0) 1 else 0)
    // Helper function to count the number of odd digits greater than three in a downward manner
    fun countOddDigitsGreaterThanThree_down(number: Int): Int = countOddDigitsGreaterThanThree_tail(number, 0)

    // Function to calculate the greatest common divisor in an upward manner
    fun gcd_up(a: Int, b: Int): Int = if (b == 0) a else gcd_up(b, a % b)

    // Tail recursive function to calculate the greatest common divisor
    tailrec fun gcd_tail(a: Int, b: Int): Int = if (b == 0) a else gcd_tail(b, a % b)
    // Helper function to calculate the greatest common divisor in a downward manner
    fun gcd_down(a: Int, b: Int): Int = gcd_tail(a, b)

    fun main(args: Array<String>) {
        val sc = Scanner(`in`)
        print("Enter a number: ")
        val n = sc.nextInt()
        println("Product of digits: ${productOfDigits_down(n)}")
        print("Enter a number: ")
        val number = sc.nextInt()
        println("Number of odd digits greater than 3: ${countOddDigitsGreaterThanThree_down(number)}")
        print("Enter two numbers: ")
        val a = sc.nextInt()
        val b = sc.nextInt()
        println("GCD of $a and $b: ${gcd_down(a, b)}")
    }
}

fun main() = RecursiveFunctionsInsteadCycle().main(emptyArray())
