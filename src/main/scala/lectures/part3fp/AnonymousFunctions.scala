package lectures.part3fp

object AnonymousFunctions extends App {

  // anonymous function (LAMBDA)
  val doublerT1: Int => Int = (x: Int) => x * 2

  //Type inference
  val doublerT2: Int => Int = x => x * 2

  // Multiple params in a lambda
  val adder: (Int, Int) => Int = (a: Int, b: Int) => a + b

  // no params
  val justDoSomething: () => Int = () => 3

  //careful
  println(justDoSomething) //function itself
  println(justDoSomething()) // call

  // curly braces with lambdas
  val stringToInt = { (str: String) =>
    str.toInt
  }

  // MORE syntactic sugar
  val niceIncrementer: Int => Int = _ + 1 //equivalent to x => x + 1
  val niceAdder: (Int, Int) => Int = _ + _ // equivalent to (a,b) => a + b

  /**
   * 1. MyList: replace all FunctionX calls with lambdas
   * 2. Rewrite the "special" adder as an anonymous function
   */

  val specialAdder: (Int) => ((Int) => Int) = (x: Int) => (y: Int) => x + y
  val specialAdder1 = (x: Int) => (y: Int) => x + y

}
