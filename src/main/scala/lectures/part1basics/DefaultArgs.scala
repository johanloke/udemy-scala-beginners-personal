package lectures.part1basics

import scala.annotation.tailrec

object DefaultArgs extends App{

  @tailrec
  def trFact(n: Int, acc: Int = 1): Int =
    if(n <= 1) acc
    else trFact(n-1, n*acc)

  val fact10 = trFact(10)

  def savePicture(format: String = "jpg", width: Int, height: Int): Unit = println("saving picture")
  savePicture("jpg", 800, 800)
  savePicture(width=800, 800)

  /**
   * 1. pass in every leading argument
   * 2. name the argument
   */

  savePicture(height=600, width = 200)
  savePicture(height=600, width = 200, format = "bmp")
}
