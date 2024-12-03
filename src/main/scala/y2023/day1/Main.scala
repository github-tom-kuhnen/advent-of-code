package y2023.day1

import services.FileReaderService
import y2023.day1.Model.Calibration

object Main extends App {

  val input = FileReaderService.read("day1.txt")
  val calibration = Calibration.apply(input)

  println(calibration.sum)

}