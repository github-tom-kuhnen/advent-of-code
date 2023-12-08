package day8

import day8.Model.MoveTable
import services.FileReaderService

object Main extends App {

  val input = FileReaderService.read("day8.txt")

  private def firstPart(): Unit = {
    val table = MoveTable.apply(input)
    println(table.moveFrom("AAA"))
  }

  private def secondPart(): Unit = {
  }

  firstPart() //
  secondPart() //
}