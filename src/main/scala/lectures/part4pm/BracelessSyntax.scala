package lectures.part4pm

object BracelessSyntax {

  // if - expression
  val anIfExpression = if (2 > 3) "bigger" else "smaller"

  // java-style
  val anIfExpression_v2 =
    if (2 > 3) {
      "bigger"
    } else {
      "smaller"
    }

  // compact style
  val anIfExpression_v3 =
    if (2 > 3) "bigger"
    else "smaller"

  // scala 3
  val anIfExpression_v4 =
    if 2 > 3 then
      "bigger" // higher indentation than the if part
    else
      "smaller"

  val anIfExpression_v5 =
    if 2 > 3 then
      val result = "bigger"
      result
    else
      val result = "smaller"
      result

  // scala 3 one-liner
  val anIfExpression_v6 = if 2 > 3 then "bigger" else "smaller"

  // for comprehensions
  val aForComprehension = for {
    n <- List(1, 2, 3)
    s <- List("black", "white")
  } yield s"$n$s"

  // scala 3
  val aForComprehension_v2 =
    for
      n <- List(1, 2, 3)
      s <- List("black", "white")
    yield s"$n$s"

  // pattern matching
  val meaningOfLife = 42
  val aPatternMatch = meaningOfLife match {
    case 1 => "the one"
    case 2 => "double of nothing"
    case _ => "something else"
  }

  // scala 3
  val aPatternMatch_v2 =
    meaningOfLife match
      case 1 => "the one"
      case 2 => "double of nothing"
      case _ => "something else"

  // methods without braces
  def computeMeaningOfLife(arg: Int): Int = {
    val partialResult = 40


    partialResult + 2
  }

  // scala 3
  def computeMeaningOfLife_v2(arg: Int): Int =
    val partialResult = 40
    /*
      code. It will return partialResult + 2
      because the code stick to the same indentation
    */

    partialResult + 2

  // class definition with significant indentation (same for traits, object, enums etc)
  class Animal {
    def eat(): Unit =
      println("I'm eating")
  }

  // scala 3
  // add : to start an indentation region.
  class Animal1: // compiler expects the body of Animal
    def eat(): Unit =
      println("I'm eating")

    def grow(): Unit =
      println("I'm growing")
    end grow

    // 3000 more lines of code
  end Animal1 // indicate to compiler. if, match, for, methods, classes, traits, enums, objects

  val aSpecialAnimal = new Animal :
    override def eat(): Unit = println("I'm special")

  // indentation = strictly larger indentation
  // 3 spaces + 2 tabs > 2 spaces + 2 tabs
  // 3 spaces + 2 tabs > 3 spaces + 1 tab
  // 2 tabs + 2 spaces ??? 2 tabs + 3 spaces
  // rule of thumb either spaces or tab

  def main(args: Array[String]): Unit = {
    println(anIfExpression_v5)
    println(computeMeaningOfLife_v2(3))
  }
}
