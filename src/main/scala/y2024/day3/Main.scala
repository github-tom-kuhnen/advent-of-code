package y2024.day3

import services.FileReaderService
import y2024.day3.Model.MultiplierEngine

object Main extends App {

  val input = FileReaderService.read("y2024/day3.txt")

  val result = MultiplierEngine(input)

  println(s"Totals sum : ", result.totalsSum)
}