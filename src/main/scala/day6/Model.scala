package day6

object Model {
  case class Race(timeAllowed: Int, bestDistance: Int) {
    def isHoldingTimeWinning(holdingTime: Int): Boolean = {
      val timeLeft = timeAllowed - holdingTime
      val distance = timeLeft * holdingTime
      distance > bestDistance
    }

    val differentWinningWays: Int = (0 to timeAllowed).count(isHoldingTimeWinning)
  }

  case class RaceTable(races: List[Race]) {
    val differentWinningWaysEachRace: Int = races.map(_.differentWinningWays).product
  }
  object RaceTable {
    def apply(input: String): RaceTable = {
      val lines: List[String] = input.linesIterator.toList
      def extractValues(line: String): List[Int] = line.split(":").last.trim.split("""\s+""").map(_.toInt).toList

      val times = extractValues(lines.head)
      val distances = extractValues(lines.last)

      val races = times.zipWithIndex.map {
        case (time, index) => Race(time, distances(index))
      }
      RaceTable(races)
    }
  }
}