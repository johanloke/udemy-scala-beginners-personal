package lectures.part2oop

import scala.language.postfixOps

object MethodNotations extends App {

  class Person(val name: String, favoriteMovie: String, val age: Int = 0) {
    def likes(movie: String): Boolean = movie == favoriteMovie

    def hangOutWith(person: Person): String = s"${this.name} is hanging out with ${person.name}"

    def +(person: Person): String = s"${this.name} plus ${person.name}"

    //+ is not a reserved word for method
    def unary_! : String = s"$name. what the heck!"

    def isAlive: Boolean = true

    def apply(): String = s"Hi, my name is $name and I like $favoriteMovie"

    //if an object is called directly, it will look for apply()
    def +(nickname: String): Person = new Person(s"$name ($nickname)", favoriteMovie)

    def unary_+ : Person = new Person(name, favoriteMovie, age + 1)

    def learns(course: String): String = s"$name learn $course"

    def learnsScala: String = this.learns("Scala")

    def apply(number: Int): String = s"$name watched $favoriteMovie $number times"

  }

  val mary = new Person("Mary", "Inception")
  println(mary.likes("Inception"))
  println(mary likes "Inception") // equivalent
  // infix notation = operator notation (syntatic sugar)
  // only works if method has 1 parameter

  // "operators" in Scala
  val tom = new Person("Tom", "Fight Club")
  println(mary hangOutWith tom)
  println(mary hangOutWith (tom))
  println(mary + tom)

  println(1 + 2)
  println(1.+(2))

  //  ALL OPERATORS ARE METHODS

  //prefix notation
  val x = -1 // equivalent with 1.unary_-
  val y = 1.unary_-
  // unary_ prefix only works with - + ~ !

  println(!mary)
  println(mary.unary_!)

  // postfix notation
  println(mary.isAlive)
  println(mary isAlive)

  //apply
  println(mary.apply())
  println(mary()) //equivalent


  /**
   * 1. Overload the + operator
   * mary + "the rockstart" => new person "Mary (the rockstar)"
   *
   * 2. Add an age to the Person class
   * Add a unary + operator => new person with the age + 1
   * +mary => mary with the age incrementer
   * 3. Add a "learns" method in the Person class => "Mary learns Scala"
   * Add a learnsScala method, calls learns method with "Scala"
   * Use it in postfix notation.
   * 4. Overload the apply method
   * mary.apply(2) => "Mary watched Inception 2 times"
   */

  println((mary + "the Rockstar") ())

  println((+mary).age)

  println(mary learnsScala)

  println(mary(10))
}
