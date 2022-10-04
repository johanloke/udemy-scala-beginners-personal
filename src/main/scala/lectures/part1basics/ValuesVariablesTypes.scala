package lectures.part1basics

object ValuesVariablesTypes extends App {
  val x: Int = 42
  println(x)

  //VALS ARE IMMUTABLE

  //COMPILER can infer types

  val aString: String = "hello"
  val anotherString = "goodbye"

  val aBoolean: Boolean = false
  val aChar: Char = 'a'
  val anInt: Int = x
  val aShort: Short = 4613
  val aLong: Long = 34254364536575L
  val aFloat: Float = 2.0f
  val aDouble: Double = 3.1412

  //variables
  var aVariable: Int = 4
  aVariable = 5 // side effects

  //prefer vals over vars
  //all vals and vars have types
}
