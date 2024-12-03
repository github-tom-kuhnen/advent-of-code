package y2023.day4

import services.FileReaderService
import y2023.day4.Model.CardTable

object Main extends App {

  val input = FileReaderService.read("y2023/day4.txt")
  private val cardTable = CardTable.apply(input)

  private def firstPart(): Unit = {
    println(cardTable.score)
  }

  private def secondPart(): Unit = {
    println(cardTable.winningCards.length)
  }

  firstPart() //28750
  secondPart() //10212704
}