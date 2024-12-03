package y2023.day8

import y2023.day8.Model.MoveTable
import y2023.services.FileReaderService

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