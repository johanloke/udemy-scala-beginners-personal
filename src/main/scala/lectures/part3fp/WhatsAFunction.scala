package lectures.part3fp

object WhatsAFunction extends App{

  // Dream: using functions as first class elements
  // Problem: we come from an oop world. everything is an object

  val doubler = new MyFunction[Int, Int] {
    override def apply(element: Int): Int = element * 2
  }

  println(doubler(2))

  // function types = Function1[A, B]
  val stringToIntConverter = new Function1[String, Int] {
    override def apply(string: String): Int = string.toInt
  }

  println(stringToIntConverter("3") + 4)

  val adder: ((Int, Int) => Int) = new Function2[Int, Int, Int] {
    override def apply(v1: Int, v2: Int): Int = v1 + v2
  }

  // Function types Function2[A, B, R] == (A,B) => R

  //ALL SCALA FUNCTIONS ARE OBJECTS

  /**
   * 1. A function which takes 2 strings and concatenates them
   * 2. Transform the MyPredicate and MyTransfomer into function types
   * 3. define a function which takes an int and returns another function which takes an int and returns an int
   *    - what's the type of this function
   *    - how to do it
   */

  val concatenator: ((String, String) => String) = (v1: String, v2: String) => v1 + v2

  println(concatenator("Good ", "Morning"))

  // Function1 [Int, Function1[Int, Int]]
  val superAdder: Function1 [Int, Function1[Int, Int]] = new Function1[Int, Function1[Int, Int]] {
    override def apply(x: Int): Function1[Int, Int] = new Function1[Int, Int] {
      override def apply(y: Int): Int = x + y
    }
  }

  val adder3 = superAdder(3)
  println(adder3(4))

  println(superAdder(3)(4)) // curried function
  println(specialAdder(5)(9))

}

class FunctionAsObject {
  def execute(element: Int): String = ???
}

trait MyFunction[A, B] {
  def apply(element: A): B
}
