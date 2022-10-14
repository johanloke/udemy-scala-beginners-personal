package lectures.part2oop

object Exception extends App {

  val x: String = null
//  println(x.length)
//  this ^^ will crash with NPE
  // throwing and catching exceptions

//  val aWeirdValue: String = throw new NullPointerException

  // throwable classes extend the Throwable class
  // Exception and Error are the major Throwable subtypes

  // 2. how to catch exceptions
  def getInt(withExceptions: Boolean): Int =
    if(withExceptions) throw new RuntimeException("No int for you!")
    else 42

  // if catch is returning unit, then potentialFail is AnyVal
  // if catch is returning Int, then potentialFail is Int
  val potentialFail = try {
    // code that might throw
    getInt(true)
  } catch {
    case e: RuntimeException => println("caught a Runtime exception")
  } finally {
    // code that will get executed NO MATTER WHAT
    // optional
    // does not influence the return type of this expression
    // user finally only for side effects eg logging
    println("finally")
  }
  println(potentialFail)

  // 3. how to define you own exceptions
  class MyException extends Exception

  val exception = new MyException

//  throw exception

  /**
   * 1. Crash your program with an OutOfMemoryError
   * 2. Crash with Stack Overflow Error
   * 3. PocketCalculator
   *    - add(x,y)
   *    - subtract(x,y)
   *    - multiple(x,y)
   *    - divide (x,y)
   *
   *    Throw
   *    - OverflowException if add(x,y) exceeds Int.MAX_VALUE
   *    - UnderflowException if subtract(x,y) exceed Int.MIN_VALUE
   *    - MathCalculationException for division by 0
   */

  // 1 - OOM
//  val array = Array.ofDim[Int](Int.MaxValue)

  // 2 - SO
//  def infinite: Int = 1 + infinite
//  val noLimit = infinite


  class OverflowException extends Exception
  class UnderflowException extends Exception
  class MathCalculationException extends Exception

  class PocketCalculator {
    def add(x: Int, y: Int): Int =
      val ans = x.toLong + y.toLong
      checkException(ans)
    def subtract(x: Int, y: Int): Int =
      val ans = x.toLong - y.toLong
      checkException(ans)
    def multiple(x: Int, y: Int): Int =
      val ans = x.toLong * y.toLong
      checkException(ans)
    def divide(x: Int, y: Int): Int =
      if(y == 0) throw new MathCalculationException
      else x / y

    def checkException(ans: Long): Int =
      if (ans > Int.MaxValue) throw new OverflowException
      else if (ans < Int.MinValue) throw new UnderflowException
      else ans.toInt
  }

  val pocketCalculator = new PocketCalculator
//  println(pocketCalculator.add(Int.MinValue, Int.MinValue))
//  println(pocketCalculator.subtract(Int.MinValue, 777))
//  println(pocketCalculator.multiple(Int.MaxValue, Int.MaxValue))
//  println(pocketCalculator.divide(10, 0))

}
