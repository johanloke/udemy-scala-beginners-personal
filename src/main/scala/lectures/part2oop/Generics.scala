package lectures.part2oop

object Generics extends App{

  class MyList[+A] {
    // use the type A to denote a generic type
    def add[B >: A](element: B): MyList[B] = ???

    /**
     * A = Cat
     * B = Animal
     *
     */
  }

  class MyMap[Key, Value]

  val listOfIntegers = new MyList[Int]
  val listOfStrings = new MyList[String]

  // generic methods
  object MyList {
    def empty[A]: MyList[A] = ???
  }

  val emptyListOfIntegers = MyList.empty[Int]

  //variance problem
  class Animal
  class Cat extends Animal
  class Dog extends Animal

  // 1. yes, List[Cat] extends List[Animal] = COVARIANCE
  class CovariantList[+A]
  val animal: Animal = new Cat
  val animalList: CovariantList[Animal] = new CovariantList[Cat]
  // animalList.add(new Dog) ?? It will turn into something that is higher (Animal)

  // 2. NO = INVARIANCE
  class InvariantList[A]
  val invariantAnimalList: InvariantList[Animal] = new InvariantList[Animal]

  // 3. Hell, no! CONTRAVARIANCE
  class Trainer[-A]
  val trainer: Trainer[Cat] = new Trainer[Animal]

  // bounded type
  class Cage[A <: Animal](animal: A) // <: means subtype
  val cage = new Cage(new Dog)

  class Car
  //  val newCage = new Cage(new Car) // Doesn't work as Car do not conform to subtype

  class Earth[A >: Animal](animal: A) // >: means supertype

  // expand MyList to be generic


}
