package y2024.day9

import services.FileReaderService
import y2024.day4.Model.XmasGrid
import y2024.day5.Model.SafetyManual

object Main extends App {

  val input = FileReaderService.read("y2024/day5.txt")

  private val manual = SafetyManual.apply(input)

  println("Total middle safety pages: " + manual.totalMiddleSafetyPages)
}