package y2023.day1

import y2023.day1.Model.Calibration
import org.scalatest.flatspec.AnyFlatSpec
import y2023.services.FileReaderService

class ModelTest extends AnyFlatSpec {
  behavior of "Calibration"
  "apply" should "create a Calibration from a string input" in {
    val input =
      """1abc2
        |pqr3stu8vwx
        |a1b2c3d4e5f
        |treb7uchet""".stripMargin

    val result = Calibration.apply(input)
    val expected = Calibration(values = List("1abc2", "pqr3stu8vwx", "a1b2c3d4e5f", "treb7uchet"))

    assert(result == expected)
  }

  "listDigits" should "return a list of digits and replace word by digit" in {
    val input = Calibration(values = List("1abc2", "pqr3stu8vwx", "a1b2c3d4e5f", "treb7uchet"))

    val expected = List(12, 38, 15, 77)

    assert(input.listDigits == expected)
  }

  "sum" should "sum all digits" in {
    val input = Calibration(values = List("1abc2", "pqr3stu8vwx", "a1b2c3d4e5f", "treb7uchet"))

    val expected = 142

    assert(input.sum == expected)
  }

  "replaceNumber" should "replace a digit in string to int" in {
    val input = "eighteightwothreeeightto"

    val expected = 88

    assert(Calibration.extractNumber(input) == expected)
  }

  behavior of "Day Results"
  "y2023/day1" should "assert result first part" in {
    val input =
      """two1nine
        |eightwothree
        |abcone2threexyz
        |xtwone3four
        |4nineeightseven2
        |zoneight234
        |7pqrstsixteen""".stripMargin
    val calibration = Calibration.apply(input)

    println(calibration.listDigits)
    assert(calibration.sum == 281)
  }

  "day1-2" should "assert result first part" in {
    val input = FileReaderService.read("y2023/day1.txt")
    val calibration = Calibration.apply(input)
    assert(calibration.sum == 55429)
  }


  "day1-2" should "other test debug" in {
    val input =
      """onebrcg1bnfivekftnzpclqxvhchloneightzrn
        |3eightwoqs
        |4oneightk""".stripMargin
    val calibration = Calibration.apply(input)


    assert(calibration.listDigits == List(18, 32, 48))
  }

}
