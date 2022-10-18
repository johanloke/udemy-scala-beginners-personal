package lectures.part3fp

abstract class MyList[+A] {

  /**
   * Head = first element of the list
   * Tail = remainder of the list
   * isEmpty = is this list empty
   * add(int) => new list with this element added
   * toString => a string representation of the list
   */

  def head: A

  def tail: MyList[A]

  def isEmpty: Boolean

  def add[B >: A](element: B): MyList[B]

  def printElements: String

  //polymorphic call
  override def toString: String = "[" + printElements + "]"

  //higher-order functions
  def map[B](transformer: A => B): MyList[B]

  def flatMap[B](transformer: A => MyList[B]): MyList[B]

  def filter(predicate: A => Boolean): MyList[A]

  def ++[B >: A](list: MyList[B]): MyList[B]

  //hofs
  def forEach(f: A => Unit): Unit

  def sort(compare: (A, A) => Int): MyList[A]

  def zipwith[B, C](list: MyList[B], zip: (A, B) => C): MyList[C]

  def fold[B](start: B)(operator: (B, A) => B): B
}

case object Empty extends MyList[Nothing] {
  def head: Nothing = throw new NoSuchElementException

  def tail: MyList[Nothing] = throw new NoSuchElementException

  def isEmpty: Boolean = true

  def add[B >: Nothing](element: B): MyList[B] = new Cons(element, Empty)

  def printElements: String = ""

  def map[B](transformer: Nothing => B): MyList[B] = Empty

  def flatMap[B](transformer: Nothing => MyList[B]): MyList[B] = Empty

  def filter(predicate: Nothing => Boolean): MyList[Nothing] = Empty

  def ++[B >: Nothing](list: MyList[B]): MyList[B] = list

  //hofs
  def forEach(f: Nothing => Unit): Unit = ()

  def sort(compare: (Nothing, Nothing) => Int) = Empty

  def zipwith[B, C](list: MyList[B], zip: (Nothing, B) => C): MyList[C] =
    if (!list.isEmpty) throw new RuntimeException("Lists do not have the same length")
    else Empty

  def fold[B](start: B)(operator: (B, Nothing) => B): B = start
}

case class Cons[+A](h: A, t: MyList[A]) extends MyList[A] {
  def head: A = h

  def tail: MyList[A] = t

  def isEmpty: Boolean = false

  def add[B >: A](element: B): MyList[B] = new Cons(element, this)

  def printElements: String =
    if (t.isEmpty) "" + h
    else s"$h ${t.printElements}"

  def map[B](transformer: A => B): MyList[B] =
    new Cons(transformer(h), t.map(transformer))

  /**
   * [1,2].flatMap(n => [n, n+1]) =
   * [1,2] ++ [2].flatMap(n => [n, n+1]) = =
   * [1,2] ++ [2,3] ++ Empty.flatMap(m => [n,n+1])) =
   * [1,2,2,3]
   */
  def flatMap[B](transformer: A => MyList[B]): MyList[B] =
    transformer(h) ++ t.flatMap(transformer)

  def filter(predicate: A => Boolean): MyList[A] =
    if (predicate(h)) new Cons(h, t.filter(predicate))
    else t.filter(predicate)

  /**
   * [1,2] += [3,4,5] =
   * new Cons(1, [2] ++ [3,4,5]) =
   * new Cons(1, new Cons(2, Empty ++ [3,4,5])) =
   * new Cons(1, new Cons(2, new Cons(3, new Cons(4, new Cons(5)))))
   *
   */
  def ++[B >: A](list: MyList[B]): MyList[B] = new Cons(h, t ++ list)

  def forEach(f: A => Unit): Unit = {
    f(h)
    t.forEach(f)
  }

  def sort(compare: (A, A) => Int): MyList[A] = {
    def insert(x: A, sortedList: MyList[A]): MyList[A] =
      if (sortedList.isEmpty) new Cons(x, Empty)
      else if (compare(x, sortedList.head) < 0) new Cons(x, sortedList)
      else new Cons(sortedList.head, insert(x, sortedList.tail))

    val sortedTail = t.sort(compare)
    insert(h, sortedTail)
  }

  def zipwith[B, C](list: MyList[B], zip: (A, B) => C): MyList[C] =
    if (list.isEmpty) throw new RuntimeException("Lists do not have the same length")
    else new Cons(zip(h, list.head), tail.zipwith(list.tail, zip))

  /**
   * [1,2,3].fold(0)(+) =
   * [2,3].fold(1)(+) =
   * [3].fold(3)(+) =
   * [].fold(6)(+) =
   * 6
   */
  def fold[B](start: B)(operator: (B, A) => B): B = {
    tail.fold(operator(start, h))(operator)
  }

}

object ListTest extends App {
  //Without Generic
  //  val list = new Cons(1, new Cons(2, new Cons(3, Empty)))
  //  println(list.head)
  //  println(list.tail.head)
  //  println(list.add(4).head)
  //  println(list.isEmpty)
  //
  //  println(list.toString)

  val listOfIntegers: MyList[Int] = Empty
  val listOfIntegers1: MyList[Int] = new Cons(1, new Cons(2, new Cons(3, Empty)))
  val cloneListOfIntegers: MyList[Int] = new Cons(1, new Cons(2, new Cons(3, Empty)))
  val anotherlistOfIntegers: MyList[Int] = new Cons(4, new Cons(5, Empty))
  val listOfString: MyList[String] = Empty
  val listOfString1: MyList[String] = new Cons("Hello", new Cons("Scala", Empty))

  println(listOfIntegers1.toString)
  println(listOfString1.toString)

  println(listOfIntegers1.map((elem: Int) => elem * 2))
  // same as above
  println(listOfIntegers1.map(_ * 2))

  println(listOfIntegers1.filter((elem: Int) => elem % 2 == 0))
  //same as above
  println(listOfIntegers1.filter(_ % 2 == 0))

  println(listOfIntegers1 ++ anotherlistOfIntegers)
  println(listOfIntegers1.flatMap((elem: Int) => new Cons(elem, new Cons(elem + 1, Empty))))

  println(listOfIntegers1 == cloneListOfIntegers)

  listOfIntegers1.forEach(println)
  println(listOfIntegers1.sort((x, y) => y - x))
  println(anotherlistOfIntegers.zipwith[String, String](listOfString1, _ + "-" + _))
  println(listOfIntegers1.fold(0)(_ + _))
}