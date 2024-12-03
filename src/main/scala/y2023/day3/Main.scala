package y2023.day3

import y2023.day3.Model.Engine
import y2023.services.FileReaderService

object Main extends App {

  val input = FileReaderService.read("y2023/day3.txt")
  private val engine = Engine.apply(input)

  private def firstPart(): Unit = {
    println(engine.sumValidNumbers)
  }

  private def secondPart(): Unit = {
    println(engine.sumGearsRatio)
  }

  firstPart() //559667
  secondPart() //86841457
}