package lectures.part3fp

object MapFlatmapFilterFor extends App {

  val list = List(1, 2, 3)
  println(list.head)
  println(list.tail)

  // map
  println(list.map(_ + 1))
  val addTwo = (x: Int) => x + 2
  println(list.map(addTwo))
  println(list.map((x: Int) => x + 3))

  // filter
  println(list.filter(_ % 2 == 0))

  // flatMap
  val toPair = (x: Int) => List(x, x + 2, x + 4)
  println(toPair)
  println(list.flatMap(toPair))

  // print all combination between two lists
  val numbers = List(1, 2, 3, 4)
  val chars = List('a', 'b', 'c', 'd')
  val colors = List("black", "white")
  // List("a1","a2","a3"......."d3","d4")
  val combinations1 = numbers.flatMap(n => chars.map(c => "" + c + n))
  val combinations2 = numbers.flatMap(n => chars.flatMap(c => colors.map(color => "" + c + n + "-" + color)))
  println(combinations1)
  println(combinations2)

  // foreach
  list.foreach(println)

  // for comprehension
  // The function below will translate into flatMap and map behind the scene
  // You can also add filtering like if n % 2 == 0
  // It will be translated into like this numbers.filter(_ % 2 ==0).flatMap(n => chars.flatMap(c => colors.map(color => "" + c + n + "-" + color)))
  val forCombinations = for {
    n <- numbers if n % 2 == 0
    c <- chars
    color <- colors
  } yield "" + c + n + "-" + color
  println(forCombinations)

  for {
    n <- numbers
  } println(n)

  // syntax overload
  list.map {
    x => x * 2
  }

  /**
   * 1. MyList supports for comprehension
   * map(f: A -> B) => MyList[B]
   * filter(p: A => Boolean) => MyList[A]
   * flatMap(f:A => MyList[B]) => MyList[B]
   * 2. A small collection of at most ONE element - Maybe[+T]
   *    - map, flatMap, filter
   */
}
