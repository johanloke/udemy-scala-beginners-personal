package lectures.part2oop

object InheritanceAndTraits extends App{

  // single class inheritance
  class Animal {
    val creatureType = "wild"
    protected def eat = println("nomnom")
    private def drink = println("gulp")
  }

  class Cat extends Animal {
    def crunch = {
      eat
      println("crunch crunch")
    }
  }

  val cat = new Cat
  cat.crunch
  //  Not accessible since its private
  //  cat.drink

  // constructors
  class Person(name: String, age: Int) {
    def this(name: String) = this(name, 0)
  }
  class Adult(name: String, age: Int, idCard: String) extends Person(name)

  //overriding
  class Dog extends Animal {
    override val creatureType = "domestic"
    override def eat = println("crunch, crunch")
  }

  //overriding
  class Pig(override val creatureType: String) extends Animal {
    override def eat = println("oink, oink")
  }

  val dog = new Dog
  dog.eat
  println(dog.creatureType)

  val pig = new Pig("pink")
  println(pig.creatureType)

  // type substitution (broad: polymorphism)
  val unknownAnimal = new Pig("K9")
  unknownAnimal.eat

  //OVERRIDING vs OVERLOADING

  //super
  class Cow(override val creatureType: String) extends Animal {
    override def eat = {
      super.eat
      println("moo, moo")
    }
  }

  val cow = new Cow("milk")
  cow.eat

  //preventing overrides
  // 1 - use final on member
  // 2 - use final on the entire class
  // 3 - seal the class = extend classes in THIS FILE, prevent extension in this file


}
