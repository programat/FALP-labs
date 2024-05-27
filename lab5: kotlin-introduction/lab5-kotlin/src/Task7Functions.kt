import java.lang.System.`in`
import java.util.*

class Task7Functions {

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

    fun main() {
        val sc = Scanner(`in`)
        print("Enter a number: ")
        val n = sc.nextInt()
        println("Sum of prime divisors: ${sumPrimeDivisorsOfNumb(n)}")
        println("Product of divisors with greater digit sum: ${productOfDivisorsWithGreaterDigitSum(n)}")
    }
}

fun main() = Task7Functions().main()