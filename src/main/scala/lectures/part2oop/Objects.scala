package lectures.part2oop

object Objects {

  //SCALA DOES NOT HAVE CLASS-LEVELFUNCTIONALITY ("static")
  object Person { // type + its only instance
    // Like static class in Java
    // "static"/"class" - level functionality
    val N_EYES = 2
    def canFly: Boolean = false

    def apply(mother: Person, father: Person): Person = new Person("Bobbie")
  }
  class Person (val name: String){
    // instance-level functionality
  }
  // COMPANIONS

  def main(args: Array[String]): Unit = {
    println(Person.N_EYES)
    println(Person.canFly)

    // Scala object = SINGLETON INSTANCE
    val mary = Person
    val john = Person
    println(mary == john)

    val mary1 = new Person("Mary")
    val john1 = new Person("John")
    println(mary1 == john1)

    val bobbie = Person(mary1, john1)

    // Scala Applications = Scala object with
    // def main(args: Array[String]): Unit
  }
}
