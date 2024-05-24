import com.sun.tools.javac.Main
import java.lang.System.`in`
import java.util.*

class RecursiveFunctions {
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
    fun max_digit(n: Int): Int = digits(n / 10, n % 10) {a, b -> if (a > b) a else b }
    // Function to find the minimum digit in a number
    fun min_digit(n: Int): Int = digits(n / 10, n % 10) {a, b -> if (a < b) a else b }

    // Main function to interact with the user
    fun main(args: Array<String>) {
        val sc = Scanner(`in`)
        print("Enter a number: ")
        val n = sc.nextInt()
        println("Max digit: ${max_digit(n)}")
        println("Min digit: ${min_digit(n)}")
        println("Sum of digits: ${sum_digits(n)}")
        println("Product of digits: ${prod_digits(n)}")
    }
}

// Entry point of the program
fun main() = RecursiveFunctions().main(emptyArray())