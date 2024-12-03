package y2024.day2

object Model {

  case class Report(levels: List[Int]) {

    val areAllLevelsIncreasing: Boolean = levels == levels.sorted
    val areAllLevelsDecreasing: Boolean = levels == levels.sorted.reverse

    val areTwoAdjacentLevelsBetween1and3: Boolean = levels.sliding(2).forall {
      case List(a, b) => Math.abs(a - b) >= 1 && Math.abs(a - b) <= 3
    }

    val isSafe: Boolean = (areAllLevelsIncreasing || areAllLevelsDecreasing) && areTwoAdjacentLevelsBetween1and3
  }
  case class UnusualData(reports: List[Report]) {

    val safeReports: List[Report] = reports.filter(_.isSafe)
  }

  object UnusualData {
    /**
     * Example input :
     * 7 6 4 2 1
     * 1 2 7 8 9
     * 9 7 6 2 1
     * 1 3 2 4 5
     * 8 6 4 4 1
     * 1 3 6 7 9
     */
    def apply(input: String): UnusualData = {
      val reports = input.linesIterator.map { line =>
        val levels = line.split("\\s+").map(_.toInt).toList
        Report(levels)
      }.toList
      UnusualData(reports)
    }
  }
}
