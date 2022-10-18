package lectures.part3fp

object TuplesAndMaps extends App {

  // tuples= finite ordered "lists"
  val aTuple = Tuple2(2, "Hello, Scala") // Tuple2[Int, String] = (Int, String)

  println(aTuple._1 + "-" + aTuple._2)
  println(aTuple.copy(_2 = "goodbye Java"))
  println(aTuple.swap) // ("Hello, Scala", 2)

  // Maps - keys -> values
  val aMap: Map[String, Int] = Map()

  val phonebook = Map(("Jim", 555), "Daniel" -> 789).withDefaultValue(-1) // -> is a syntactic sugar
  // a -> b is sugar for (a,b)
  println(phonebook)

  // map ops
  println(phonebook.contains("Jim"))
  println(phonebook("Jim"))
  println(phonebook("Mary")) // exception if default value not added

  // add a pairing
  val newPairing = "Mary" -> 678
  val newPhonebook = phonebook + newPairing
  println(newPhonebook)

  // functionals on maps
  // map, flatMap, filter

  println(phonebook.map(pair => pair._1.toLowerCase -> pair._2))

  // filterKeys
  println(phonebook.view.filterKeys(x => x.startsWith("J")).toMap)

  // mapValues
  println(phonebook.view.mapValues(number => "0245-" + number).toMap)

  // conversions to other collections
  println(phonebook.toList)
  println(List(("Daniel", 555)).toMap)

  val names = List("Bob", "James", "Angela", "Mary", " Daniel", "Jim")
  // HashMap(J -> List(James, Jim), A -> List(Angela), M -> List(Mary),   -> List( Daniel), B -> List(Bob))
  println(names.groupBy(name => name.charAt(0)))
}
