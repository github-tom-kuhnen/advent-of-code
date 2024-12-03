package y2024.day2

import services.FileReaderService
import y2024.day1.Model.LocationLists
import y2024.day2.Model.UnusualData

object Main extends App {

  val input = FileReaderService.read("y2024/day2.txt")

  val result = UnusualData(input)

  println(s"Safe reports sum : ", result.safeReports.length)
  println(s"Extended safe reports sum : ", result.extendedSafeReports.length)
}