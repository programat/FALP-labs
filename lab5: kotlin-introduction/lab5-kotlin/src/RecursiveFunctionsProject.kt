import java.util.*

class RecursiveFunctionsProject {

    // Function to find the maximum of three numbers
    fun max(x: Int, y: Int, z: Int): Int = if (x > y) if (x > z) x else z else if (y > z) y else z

    // Function to calculate factorial in an upward manner
    fun fact_up(n: Int): Int = if (n < 2) 1 else fact_up(n - 1) * n

    // Tail recursive function to calculate factorial
    tailrec fun fact_tail(n: Int, acc: Int = 1): Int = if (n < 2) n * acc else fact_tail(n - 1, n * acc)
    // Function to calculate factorial in a downward manner
    fun fact_down(n: Int): Int = fact_tail(n, 1)

    // Function to calculate sum of digits in an upward manner
    fun sum_digits_up(n: Int): Int = if (n < 10) n else sum_digits_up(n / 10) + n % 10

    // Tail recursive function to calculate sum of digits
    tailrec fun sum_digits_tail(n: Int, acc: Int): Int = if (n < 10) n + acc else sum_digits_tail(n / 10, n % 10 + acc)
    // Function to calculate sum of digits in a downward manner
    fun sum_digits_down(n: Int): Int = sum_digits_tail(n, 0)

    // Function to call either sum_digits_down or fact_down based on the boolean value
    fun call_fun(f: Boolean): (Int) -> Int = if (f) ::sum_digits_down else ::fact_down

    // Tail recursive function to perform a certain operation on the digits of a number
    tailrec fun digits(n: Int, acc: Int = 0, f: (Int, Int) -> Int): Int =
        if (n == 0) acc else digits(n / 10, f(acc, n % 10), f)

    // Function to calculate sum of digits
    fun sum_digits(n: Int): Int = digits(n, 0, Int::plus)
    // Function to calculate product of digits
    fun prod_digits(n: Int): Int = digits(n, 1, Int::times)
    // Function to find the maximum digit in a number
    fun max_digit(n: Int): Int = digits(n / 10, n % 10) { a, b -> if (a > b) a else b }
    // Function to find the minimum digit in a number
    fun min_digit(n: Int): Int = digits(n / 10, n % 10) { a, b -> if (a < b) a else b }

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

    // Higher-order function for processing digits of a number
    fun processDigits(n: Int, initial: Int, operation: (Int, Int) -> Int): Int {
        return if (n < 10) operation(n, initial) else processDigits(n / 10, operation(n % 10, initial), operation)
    }

    // Higher-order function for processing two numbers
    fun processTwoNumbers(a: Int, b: Int, operation: (Int, Int) -> Int): Int {
        return operation(a, b)
    }

    // Function to calculate the product of digits of a number
    fun productOfDigits(n: Int): Int {
        return processDigits(n, 1) { digit, acc -> digit * acc }
    }

    // Function to count the number of odd digits greater than 3
    fun countOddDigitsGreaterThanThree(n: Int): Int {
        return processDigits(n, 0) { digit, acc -> if (digit > 3 && digit % 2 != 0) acc + 1 else acc }
    }

    // Function to calculate the greatest common divisor of two numbers
    fun gcd(a: Int, b: Int): Int {
        return processTwoNumbers(a, b) { x, y -> if (y == 0) x else gcd(y, x % y) }
    }

    // Main function to interact with the user
    fun main(args: Array<String>) {
        val sc = Scanner(System.`in`)
        print("Enter a number: ")
        val n = sc.nextInt()
        println("Max digit: ${max_digit(n)}")
        println("Min digit: ${min_digit(n)}")
        println("Sum of digits: ${sum_digits(n)}")
        println("Product of digits: ${prod_digits(n)}")
        println("Product of digits (down): ${productOfDigits_down(n)}")
        println("Number of odd digits greater than 3: ${countOddDigitsGreaterThanThree(n)}")
        print("Enter two numbers: ")
        val a = sc.nextInt()
        val b = sc.nextInt()
        println("GCD of $a and $b: ${gcd(a, b)}")
    }
}

// Entry point of the program
fun main() = RecursiveFunctionsProject().main(emptyArray())