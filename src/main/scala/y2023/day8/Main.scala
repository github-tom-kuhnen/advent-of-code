package y2023.day8

import services.FileReaderService
import y2023.day8.Model.MoveTable

object Main extends App {

  val input = FileReaderService.read("y2023/day8.txt")

  private def firstPart(): Unit = {
    val table = MoveTable.apply(input)
    println(table.startSimpleRun())
  }

  private def secondPart(): Unit = {
    val table = MoveTable.apply(input)
    println(table.startGhostRun()) //Too long, not finished
  }

  firstPart() //12169
  secondPart() //Too long, not finished
}