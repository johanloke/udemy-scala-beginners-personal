package lectures.part2oop

import playground.{PrinceCharming, Cinderella as Princess}

import java.sql.SQLData
//import playground._ // import everything in this package
import java.util.Date
import java.sql.{Date => MySqlDate}

object PackagingAndImports extends App {

  // package members are accessible by their simple name
  val writer = new Writer("Daniel", "RockTheJVM", 2010)

  // import the package
//  val princess = new Cinderella // playground.Cinderella = fully qualified name
  val princess = new Princess // alias

  // packages are in hierarchy
  // matching folder structure

  // package object
  sayHello
  println(SPEED_OF_LIGHT)

  // import
  val prince = new PrinceCharming

  // 1. User FQ names
  val date = new Date
  val sqlDate = new MySqlDate(2010,5,3)

  // 2. user aliasing

  // default imports
  // java.lang - String, Object, Exception
  // scala - Int, Nothing, Function
  // scala,Predef - println, ???
}
