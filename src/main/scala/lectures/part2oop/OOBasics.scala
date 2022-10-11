package lectures.part2oop

object OOBasics extends App{

  val person = new Person("John", 26)
  println(person.age)
  println(person.x)
  person.greet("Daniel")
  person.greet()
  val author = new Writer("Charles", "Dickens", 1812)
  val imposter = new Writer("Charles", "Dickens", 1812)
  val novel = new Novel("Great Expectations", 1861, author)

  println(novel.authorAge)
  println(novel.isWrittenBy(author))
  println(novel.isWrittenBy(imposter))

  val counter = new Counter
  counter.inc.print
  counter.inc.inc.inc.print
  counter.inc(10).print
}

//constructor
class Person(name: String, val age: Int = 0){
  // body
  val x = 2

  println(1 + 3)

  def greet(name: String): Unit = println(s"${this.name} says: Hi, $name")

  // overloading
  def greet(): Unit = println(s"Hi, I am $name")

  // multiple constructors
  def this(name: String) = this(name, 0)
  def this() = this("Johan Doe")
}

/**
 * Novel and a Writer
 *
 * Writer: first name, surname, year
 *  - method fullname
 *
 * Novel: name, year of release, author
 * - authorAge
 * - isWrittenBy(author)
 * - copy (new year of release) = new instance of Novel
 */

class Writer(fname: String, surname: String, val year: Int){
  def fullname() = s"$surname $fname"
}

class Novel(name:String, year:Int, author:Writer){
  def authorAge: Int = year - author.year
  def isWrittenBy(author:Writer): Boolean = this.author == author
  def copy(newYear: Int): Novel = new Novel(name, newYear, author)
}

/**
 * Counter class
 *  - received an int value
 *  - method current count
 *  - method to increment and decrement => new Counter
 *  - overload inc/dec to receive an amount
 *
 */

class Counter(val count:Int = 0){
  def inc: Counter = {
    println("increment")
    new Counter(count + 1)
  } // immutability

  def dec: Counter = {
    println("decrement")
    new Counter(count - 1)
  }

  def inc(n: Int):Counter =
    if(n <= 0) this
    else inc.inc(n-1)

  def dec(n: Int):Counter =
    if (n <= 0) this
    else dec.dec(n - 1)

  def print = println(count)
}

//class parameters are NOT FIELDS