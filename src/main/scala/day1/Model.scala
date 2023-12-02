package day1

import day1.Model.Calibration.extractNumber

import scala.util.matching.Regex

object Model {
  case class Calibration(values: List[String]) {
    val listDigits: Seq[Int] = values.map(extractNumber)
    val sum: Int = listDigits.sum
  }

  object Calibration {
    private val numbersMap: Map[String, String] = Map(
      "one" -> "1",
      "two" -> "2",
      "three" -> "3",
      "four" -> "4",
      "five" -> "5",
      "six" -> "6",
      "seven" -> "7",
      "eight" -> "8",
      "nine" -> "9",
    )

    def apply(input: String): Calibration = Calibration(values = input.linesIterator.toList)

    private val allDigitValues: Iterable[String] = numbersMap.keys ++ numbersMap.values
    private val regex: Regex = ("(?=(" + allDigitValues.mkString("|") + "))").r

    private def replaceWordByDigit(input: String): String =
      if (numbersMap.contains(input)) numbersMap(input) else input

    def extractNumber(input: String): Int = {
      val matches = regex.findAllMatchIn(input).map(_.group(1)).toList
      (replaceWordByDigit(matches.head) + replaceWordByDigit(matches.last)).toInt
    }

  }
}
