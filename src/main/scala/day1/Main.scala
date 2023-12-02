package day1

import day1.Model.Calibration
import services.FileReaderService

object Main extends App {

  val input = FileReaderService.read("day1.txt")
  val calibration = Calibration.apply(input)

  println(calibration.sum)

}