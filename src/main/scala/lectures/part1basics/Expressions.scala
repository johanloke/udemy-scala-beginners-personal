package lectures.part1basics

object Expressions extends App{
  val x = 1 + 2 //EXPRESSION
  println(x)

  //Math operation preserved operation order
  println(2 + 3 * 4)
  // + - * / & | ^ << >> >>> (right shift with zero extension)

  println(1 == x)
  // == !=  > >= < <=

  println(!(1 == x))
  // ! && ||

  var aVariable = 2
  aVariable += 3 // also works with -= *= /= .... side effects
  println(aVariable)

  //Instructions (DO) vs Expression (Value)

  // If expression
  val aCondition = true
  val aConditionedValue = if(aCondition) 5 else 3

  println(aConditionedValue)
  println(if(aCondition) 5 else 3)
  println(1 + 3)

  var i = 0
  while (i < 10){
    println(i)
    i += 1
  }

  //NEVER WRITE THIS AGAIN
  //EVERTHING in Scala is an expression!~

  val aWeirdValue = (aVariable = 3) // Unit == void
  println(aWeirdValue)

  //side effects: println(), whiles, reassigning of vars

  // Code blocks
  val aCodeBlock = {
    val y = 2
    val z = y + 1

    if(z > 2) "hello" else "goodbye"
  }

  //Error because z exist only within code block
  //val anotherValue = z + 1
}
