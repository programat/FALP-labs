import java.util.*

class RecursiveFunctionsProject {

    // task 5 ---

    // Function to calculate product of digits in an upward manner
    fun productOfDigits_up(n: Int): Int = if (n < 10) n else productOfDigits_up(n / 10) * (n % 10)

    // Tail recursive function to calculate product of digits
    tailrec fun productOfDigits_tail(n: Int, acc: Int = 1): Int =
        if (n < 10) n * acc else productOfDigits_tail(n / 10, n % 10 * acc)

    // Function to count the number of odd digits greater than three in an upward manner
    fun countOddDigitsGreaterThanThree_up(number: Int): Int =
        if (number < 10) if (number > 3 && number % 2 != 0) 1 else 0
        else countOddDigitsGreaterThanThree_up(number / 10) + if (number % 10 > 3 && number % 10 % 2 != 0) 1 else 0

    // Tail recursive function to count the number of odd digits greater than three
    fun countOddDigitsGreaterThanThree_tail(number: Int, acc: Int = 0): Int =
        if (number < 10) acc + if (number > 3 && number % 2 != 0) 1 else 0
        else countOddDigitsGreaterThanThree_tail(number / 10, acc + if (number % 10 > 3 && number % 10 % 2 != 0) 1 else 0)

    // Function to calculate the greatest common divisor in an upward manner
    fun gcd_up(a: Int, b: Int): Int = if (b == 0) a else gcd_up(b, a % b)

    // Tail recursive function to calculate the greatest common divisor
    tailrec fun gcd_tail(a: Int, b: Int): Int = if (b == 0) a else gcd_tail(b, a % b)


    // task 7 ---

    // Tail recursive function to check if a number is prime
    tailrec fun isPrimeTail(n: Int, divisor: Int = n - 1): Boolean =
        if (n < 2) false else if (divisor == 1) true else if (n % divisor == 0) false else isPrimeTail(n, divisor - 1)
    fun isPrime(n: Int): Boolean = isPrimeTail(n)

    // Higher-order function to process divisors of a number
    fun processDivisors(n: Int, initial: Int, operation: (Int, Int) -> Int): Int =
        if (n < 2) operation(n, initial) else processDivisors(n-1, operation(n, initial), operation)

    // Function to calculate the sum of prime divisors of a number
    fun sumPrimeDivisorsOfNumb(n: Int): Int =
        processDivisors(n, 0) { divisor, acc -> if (isPrime(divisor) && (n % divisor == 0)) acc + divisor else acc }

    // Function to calculate the product of divisors with a greater sum of digits than the number itself
    fun productOfDivisorsWithGreaterDigitSum(n: Int): Int =
        processDivisors(n, 1) { divisor, acc -> if (sum_digits(divisor) > sum_digits(n) && (n % divisor == 0)) acc * divisor else acc }

    // Functions to calculate the sum of digits of a number
    tailrec fun digits(n: Int, acc: Int = 0, f: (Int, Int) -> Int): Int =
        if (n == 0) acc else digits(n / 10, f(acc, n % 10), f)
    fun sum_digits(n: Int): Int = digits(n, 0, Int::plus)

    // Main function to interact with the user
    fun main(args: Array<String>) {
        val sc = Scanner(System.`in`)
        print("Enter a number: ")
        val n = sc.nextInt()
        println("Product of digits (tail): ${productOfDigits_tail(n)}")
        println("Number of odd digits greater than 3: ${countOddDigitsGreaterThanThree_tail(n)}")
        println("Sum of prime divisors: ${sumPrimeDivisorsOfNumb(n)}")
        println("Product of divisors with greater digit sum: ${productOfDivisorsWithGreaterDigitSum(n)}")
        print("Enter two numbers: ")
        val a = sc.nextInt()
        val b = sc.nextInt()
        println("GCD of $a and $b (tail): ${gcd_tail(a, b)}")
    }
}

// Entry point of the program
fun main() = RecursiveFunctionsProject().main(emptyArray())