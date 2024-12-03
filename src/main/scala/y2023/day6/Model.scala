package y2023.day6

object Model {
  case class Race(timeAllowed: BigInt, bestDistance: BigInt) {
    def isHoldingTimeWinning(holdingTime: BigInt): Boolean = {
      val timeLeft = timeAllowed - holdingTime
      val distance = timeLeft * holdingTime
      distance > bestDistance
    }

    val differentWinningWays: BigInt = (BigInt(0) to timeAllowed).count(time => isHoldingTimeWinning(time))
  }

  case class RaceTable(races: List[Race]) {
    val differentWinningWaysEachRace: BigInt = races.map(_.differentWinningWays).product
  }
  object RaceTable {
    def apply(input: String, trimSpaces: Boolean = false): RaceTable = {
      val lines: List[String] = input.linesIterator.toList
      def extractValues(line: String): List[String] = line.split(":").last.trim.split("""\s+""").toList
      def extractNumberValues(values: List[String], trimSpaces: Boolean): List[BigInt] = if (trimSpaces) {
        List(BigInt(values.mkString("")))
      } else {
        values.map(BigInt.apply)
      }
      val times = extractNumberValues(extractValues(lines.head), trimSpaces)
      val distances = extractNumberValues(extractValues(lines.last), trimSpaces)

      val races = times.zipWithIndex.map {
        case (time, index) => Race(time, distances(index))
      }
      RaceTable(races)
    }
  }
}