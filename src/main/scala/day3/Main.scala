package day3

import day3.Model.Engine
import services.FileReaderService

object Main extends App {

  val input = FileReaderService.read("day3.txt")
  private val engine = Engine.apply(input)

  private def firstPart(): Unit = {
    println(engine.sumValidNumbers)
  }

  private def secondPart(): Unit = {
  }

  firstPart() //559667
  secondPart() //
}