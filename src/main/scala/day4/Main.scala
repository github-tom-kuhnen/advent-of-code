package day4

import day4.Model.CardTable
import services.FileReaderService

object Main extends App {

  val input = FileReaderService.read("day4.txt")
  private val cardTable = CardTable.apply(input)

  private def firstPart(): Unit = {
    println(cardTable.score)
  }

  private def secondPart(): Unit = {
  }

  firstPart() //
  secondPart() //
}