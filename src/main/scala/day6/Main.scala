package day6

import day6.Model.RaceTable
import services.FileReaderService

object Main extends App {

  val input = FileReaderService.read("day6.txt")
  private val raceTable = RaceTable.apply(input)

  private def firstPart(): Unit = {
    println(raceTable.differentWinningWaysEachRace)
  }

  private def secondPart(): Unit = {
    println("second part")
  }

  firstPart() //
  secondPart() //
}