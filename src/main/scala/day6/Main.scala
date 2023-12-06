package day6

import day6.Model.RaceTable
import services.FileReaderService

object Main extends App {

  val input = FileReaderService.read("day6.txt")

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