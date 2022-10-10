package lectures.part1basics

import scala.annotation.tailrec

object R3cursion extends App{

  def factorial(n: Int): Int =
    if(n <= 1) 1
    else {
      println("Computing factorial of " + n + " - I first need factorial of " + (n-1))
      val result = n * factorial(n-1)
      println("Computed factorial of " + n)
      result
    }
  println(factorial(10))

  def anotherFactorial(n:Int): BigInt = {
    @tailrec
    def factHelper(x:Int, accumulator: BigInt): BigInt =
      if(x <= 1) accumulator
      else factHelper(x-1, x * accumulator) // TAIL RECURSION = use recursive call as the LAST expression

    factHelper(n, 1)
  }

  println(anotherFactorial(5000))
  /**
   * anotherFactorial(10) = factHelper(10, 1)
   * = factHelper(9, 10 * 1)
   * = factHelper(8, 9 * 10 * 1)
   * = factHelper(7, 8 * 9 * 10 * 1)
   * = factHelper(2, 3 * .... * 9 * 10 * 1)
   * = factHelper(1, 1 * 2 * 3 * .... * 9 * 10)
   * = 1 * 2 * 3 * .... * 9 * 10
   */

  //WHEN YOU NEED LOOPS, USER TAIL RECURSION

  /**
   * 1. Concatenate a string n times
   * 2. IsPrime function tail recursive
   * 3. Fibonacci function, tail recursive
   */

  def concateString(s:String, n:Int): String = {
    @tailrec
    def concateHelper(x: Int, accumulator: String): String =
      if(x <= 1) accumulator
      else concateHelper(x-1, s + accumulator)

    concateHelper(n, s)
  }

  @tailrec
  def concatenateTailrec(aString: String, n: Int, accumulator: String): String =
    if(n <= 0) accumulator
    else concatenateTailrec(aString, n-1, aString + accumulator)

  println(concateString("AB", 5))
  println(concatenateTailrec("AB", 5, ""))

  def isPrime(n: Int): Boolean = {
    @tailrec
    def isPrimeHelper(t: Int, isStillPrime: Boolean): Boolean =
      if(!isStillPrime) false
      else if(t <= 1) true
      else isPrimeHelper(t-1, (n % t != 0) && isStillPrime)

    isPrimeHelper(n/2, true)
  }

  println(isPrime(3))
  println(isPrime(4))
  println(isPrime(8))
  println(isPrime(2003))
  println(isPrime(37 * 2003))

  def fibonacci(n: Int): Int =
    @tailrec
    def fiboTailrec(i: Int, last: Int, nextToLast: Int): Int =
      println("i: " + i + " and last : "+ last + " and nextToLast: " + nextToLast)
      if(i >= n) last
      else fiboTailrec(i + 1, last + nextToLast, last)

    if(n <= 2) 1
    else fiboTailrec(2, 1, 1)

  println(fibonacci(8))
}
