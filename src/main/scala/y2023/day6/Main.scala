package y2023.day6

import services.FileReaderService
import y2023.day6.Model.RaceTable

object Main extends App {

  val input = FileReaderService.read("y2023/day6.txt")

  private def firstPart(): Unit = {
    val raceTable = RaceTable.apply(input)
    println(raceTable.differentWinningWaysEachRace)
  }

  private def secondPart(): Unit = {
    val raceTable = RaceTable.apply(input, trimSpaces = true)
    println(raceTable.differentWinningWaysEachRace)
  }

  firstPart() //275724
  secondPart() //37286485
}