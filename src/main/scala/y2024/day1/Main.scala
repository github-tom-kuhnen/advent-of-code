package y2024.day1

import services.FileReaderService
import y2024.day1.Model.LocationLists

object Main extends App {

  val input = FileReaderService.read("y2024/day1.txt")

  val result = LocationLists(input)

  println(s"Distance score : {}", result.distances.sum)
  println(s"Similarity score : {}", result.similarityScores.sum)
}