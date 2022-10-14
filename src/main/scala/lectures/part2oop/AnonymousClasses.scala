package lectures.part2oop

object AnonymousClasses extends App{

  trait Animal {
    def eat(): Unit
  }

  // anonymous class
  val funnyAnimal: Animal = new Animal {
    override def eat(): Unit = println("hahahahhahaahah")
  }
  println(funnyAnimal.getClass)

  //  In compiler the above is equivalent with
  //  class AnonymousClasses$$anon$1 extends Animal {
  //    override def eat: Unit = println("hahahahhahaahah")
  //  }
  //  val funnyAnimal: Animal = new AnonymousClasses$$anon$1

  class Person(name: String){
    def sayHi(): Unit = println(s"Hi, my name is $name, how can I help?")
  }

  val jim = new Person("Jim") {
    override def sayHi(): Unit = println(s"Hi, my name is Jim, how can I be of service?")
  }

  // Anonymous class works for abstract or non abstract type

  /**
   * 1. Generic traits MyPredicate[-T] with a little method test(T) => Boolean
   * 2. Generic trait MyTransformer[-A, B] with a method transform(A) => B
   * 3. MyList:
   *    - map(transformer) => MyList
   *    - filter(predicate) => MyList
   *    - flatMap(transformer from A to MyList[B]) => MyList[B]
   *
   *    class EvenPredicate extends MyPredicate[Int]
   *    class StringToIntTransformer extends MyTransformer[String, Int]
   *
   *    [1,2,3].map(n * 2) = [2,4,6]
   *    [1,2,3,4].filter(n % 2) = [2,4]
   *    [1,2,3].flatMap(n => [n, n+1]) => [1,2,2,3,3,4]
   *
   */

}
