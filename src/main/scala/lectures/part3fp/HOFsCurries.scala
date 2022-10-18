package lectures.part3fp

object HOFsCurries extends App {

  val superFunction: (Int, (String, (Int => Boolean)) => Int) => (Int => Int) = null
  // higher order function (HOF)

  // map, flatmap, filter in MyList
  val plusOne = (x: Int) => x + 1

  // function that applied a function n times over a value x
  // nTimes(f, n, x)
  // nTimes(f, 3, x) = f(f(f(x))) = nTimes(f, 2, f(x))
  def nTimes(f: Int => Int, n: Int, x: Int): Int =
    if (n <= 0) x
    else nTimes(f, n - 1, f(x))

  println(nTimes(plusOne, 10, 1))

  // ntb(f,n) = x => f(f(f...(x)))
  // increment10 = ntb(plusOne, 10) = x => plusOne(PlusOne....(x))
  // val y = increment10(1)
  def nTimesBetter(f: Int => Int, n: Int): (Int => Int) =
    if (n <= 0) (x: Int) => x
    else (x: Int) => nTimesBetter(f, n - 1)(f(x))

  val plus10 = nTimesBetter(plusOne, 10)
  println(plus10(1))

  // curried functions
  val superAdder: Int => (Int => Int) = (x: Int) => ((y: Int) => x + y)
  val add3 = superAdder(3) // y => 3 + y
  println(add3(10))
  println(superAdder(3)(10))

  // functions with multiple parameter lists
  def curriedFormatter(c: String)(x: Double): String = c.format(x)

  val standardFormat: (Double => String) = curriedFormatter("%4.2f")
  val preciseFormat: (Double => String) = curriedFormatter("%10.8f")

  def curriedFormatterStilWorks(x: String)(c: Double): String = x.format(c)

  val standardFormatStilWorks: (Double => String) = curriedFormatter("%4.2f")
  val preciseFormatStilWorks: (Double => String) = curriedFormatter("%10.8f")

  println(standardFormat(Math.PI))
  println(preciseFormat(Math.PI))
  println(standardFormatStilWorks(Math.PI))
  println(preciseFormatStilWorks(Math.PI))

  /**
   * 1. Expand MyList
   *    - foreach method A => Unit
   *      [1,2,3].foreach(x => println(x))
   *
   *    - sort function (compares 2 functions) ((A, A) => Int) => MyList
   *      [1,2,3].sort((x,y) => (y - x) => [3,2,1]
   *
   *    - zipWith (list, (A, A) => B) => MyList(B)
   *      [1,2,3].zipWith([4,5,6], x * y) => [1*4, 2*5, 3*6] => [4,10,18]
   *
   *    - fold(start)(function) => a value
   *      [1,2,3].fold(0)(x+y) = 6
   *
   * 2. toCurry(f: (Int, Int) => Int) => (Int => Int => Int)
   * fromCurry(f: (Int => Int => Int)) => (Int, Int) => Int
   *
   * 3. compose(f,g) => x => f(g(x))
   * andThen(f,g) => x => g(f(x)
   */

  def toCurry(f: (Int, Double) => Int): (Int => Double => Int) =
    (x: Int) => (y: Double) => f(x, y)

  def fromCurry(f: (Int => Double => Int)): (Int, Int) => Int =
    (x: Int, y: Int) => f(x)(y)

  // FunctionX


  def compose[A, B, T](f: A => B, g: T => A): T => B =
    x => f(g(x))

  def andThen[A, B, C](f: A => B, g: B => C): A => C =
    x => g(f(x))

  val intdouble: (Int, Double) => Int = (a: Int, b: Double) => (a * b).toInt
  val superAdderDouble: Int => Double => Int = (x: Int) => (y: Double) => x + y.toInt

  def superAdder2: (Int => Double => Int) = toCurry(intdouble)

  println(superAdder2(9)(8))

  val simpleAdder = fromCurry(superAdderDouble)
  println(simpleAdder(4, 17))

  val add2 = (x: Int) => x + 2
  val times3 = (x: Int) => x * 3
  val composed = compose(add2, times3)
  val ordered = andThen(add2, times3)

  println(composed(4))
  println(ordered(4))
}
