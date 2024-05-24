import java.lang.System.`in`
import java.util.*

class CycleFunctions {
    fun productOfDigits(n: Int): Int {
        var product = 1
        var num = n
        while (num != 0) {
            product *= num % 10
            num /= 10
        }
        return product
    }

    fun countOddDigitsGreaterThanThree(number: Int): Int {
        var count = 0
        var num = number
        while (num != 0) {
            val digit = num % 10
            if (digit > 3 && digit % 2 != 0) {
                count++
            }
            num /= 10
        }
        return count
    }

    fun gcd(a: Int, b: Int): Int {
        var num1 = a
        var num2 = b
        while (num2 != 0) {
            val temp = num2
            num2 = num1 % num2
            num1 = temp
        }
        return num1
    }

    fun main() {
        val sc = Scanner(`in`)
        print("Enter a number: ")
        val n = sc.nextInt()
        println("Product of digits: ${productOfDigits(n)}")
        print("Enter a number: ")
        val number = sc.nextInt()
        println("Number of odd digits greater than 3: ${countOddDigitsGreaterThanThree(number)}")
        print("Enter two numbers: ")
        val a = sc.nextInt()
        val b = sc.nextInt()
        println("GCD of $a and $b: ${gcd(a, b)}")
    }
}

fun main() = CycleFunctions().main()
