package lectures.part3fp

import java.util.Random
import scala.util.{Failure, Success, Try}

object HandlingFailure extends App {

  // create success and failure
  val aSuccess = Success(3)
  val aFailure = Failure(new RuntimeException("SUPER FAILURE"))

  println(aSuccess)
  println(aFailure)

  def unsafeMethod(): String = throw new RuntimeException("NO STRING FOR YOU")

  // Try objects via the apply method
  val potentialFailure = Try(unsafeMethod())
  println(potentialFailure)

  // syntax sugar
  val anotherPotentialFailre = Try {
    // code that might throw

  }

  // utilities
  println(potentialFailure.isSuccess)

  //orElse
  def backupMethod(): String = "A valid result"

  val failbackTry = Try(unsafeMethod()).orElse(Try(backupMethod()))
  println(failbackTry)

  // IF  you design the API
  def betterUnsafeMethod(): Try[String] = Failure(new RuntimeException)

  def betterBackupMethod(): Try[String] = Success("A valid result")

  val betterFallback = betterUnsafeMethod() orElse betterBackupMethod()

  // map, flatmap, filter
  println(aSuccess.map(_ * 2))
  println(aSuccess.flatMap(x => Success(x * 10)))
  println(aSuccess.filter(_ % 2 == 0))

  // => for-comprehensions

  /**
   * Exercise
   */
  val host = "localhost"
  val port = "8080"

  def renderingHTML(page: String) = println(page)

  class Connection {
    def get(url: String): String = {
      val random = new Random(System.nanoTime())
      if (random.nextBoolean()) "<html>...</html>"
      else throw new RuntimeException("Connection interrupted")
    }

    def getSafe(url: String): Try[String] = Try(get(url))
  }

  object HttpService {
    val random = new Random(System.nanoTime())

    def getConnection(host: String, port: String): Connection =
      if (random.nextBoolean()) new Connection
      else throw new RuntimeException("Someone else took the port")

    def getSafeConnection(host: String, port: String): Try[Connection] = Try(getConnection(host, port))
  }

  // if you get the html page from the connection, print it to the console i.e call renderHTML
  val possibleConnection = HttpService.getSafeConnection(host, port)
  val possibleHTML = possibleConnection.flatMap(connection => connection.getSafe("/home"))
  possibleHTML.foreach(renderingHTML)

  // shorthand version
  HttpService.getSafeConnection(host, port).flatMap(connection => connection.getSafe("/home")).foreach(renderingHTML)

  // for-comprehension version
  for {
    possibleConnection <- HttpService.getSafeConnection(host, port)
    possibleHTML <- possibleConnection.getSafe("/home")
  } renderingHTML(possibleHTML)

}
