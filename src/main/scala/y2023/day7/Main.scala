package y2023.day7

import y2023.day7.Model.Table
import y2023.services.FileReaderService

object Main extends App {

  val input = FileReaderService.read("y2023/day7.txt")

  private def firstPart(): Unit = {
    val table = Table.apply(input)
    println(table.totalWinnings)
  }

  private def secondPart(): Unit = {
    val table = Table.apply(input)
    println(table.totalWinnings) // changing the .compareTo with using bestHandKindWithJoker
  }

  firstPart() //250370104
  secondPart() //251735672
}