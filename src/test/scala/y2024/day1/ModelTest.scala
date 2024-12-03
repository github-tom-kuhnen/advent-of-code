package y2024.day1

import org.scalatest.flatspec.AnyFlatSpec
import services.FileReaderService
import y2024.day1.Model.LocationLists

class ModelTest extends AnyFlatSpec {
  behavior of "LocationLists"
  "distances" should "create a list of sorted distances" in {

    val list1 = List(3, 4, 2, 1, 3, 3)
    val list2 = List(4, 3, 5, 3, 9, 3)

    val input = LocationLists(list1, list2)

    val result = input.distances
    val expected = List(2, 1, 0, 1, 2, 5)

    assert(result == expected)

    assert(result.sum == 11)
  }

  "apply" should "create a Lists class" in {

    val inputTxt = FileReaderService.read("y2024/day1.txt")

    val input = LocationLists(inputTxt)

    val expected = LocationLists(List(3, 4, 2, 1, 3, 3), List(4, 3, 5, 3, 9, 3))

    assert(input == expected)
  }

  "similarityScores" should "calculate the similarity scores" in {

    val inputTxt = FileReaderService.read("y2024/day1.txt")
    val input = LocationLists(inputTxt)

    val result = input.similarityScores

    val expected = List(9, 4, 0, 0, 9, 9)

    assert(result == expected)

    assert(result.sum == 31)
  }
}
