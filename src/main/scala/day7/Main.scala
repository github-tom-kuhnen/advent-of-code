package day7

import day7.Model.Table
import services.FileReaderService

object Main extends App {

  val input = FileReaderService.read("day7.txt")

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