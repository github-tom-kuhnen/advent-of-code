package y2024.day4

import services.FileReaderService
import y2024.day4.Model.XmasGrid

object Main extends App {

  val input = FileReaderService.read("y2024/day4.txt")

  val result = XmasGrid(input)

  println(s"XMas count ", result.nbXmas)
  println(s"MasX count ", result.nbMasX)
}