package y2024.day3

object Model {

  case class Multiplier(index: Int, a: Int, b: Int) {
    val total: Int = a * b
  }
  case class MultiplierEngine(multipliers: List[Multiplier]) {
    val totalsSum: Int = multipliers.map(_.total).sum

    def filteredTotalSum(input: String): Int =
      filteredMultipliers(input).map(_.total).sum

    def filteredMultipliers(input: String): List[Multiplier] = {

      val doRegex = """do\(\)""".r
      val dontRegex = """don't\(\)""".r

      val allDoIndexes = doRegex.findAllMatchIn(input).map(_.start).toList.sorted
      val allDontIndexes = dontRegex.findAllMatchIn(input).map(_.start).toList.sorted

      val disabledRanges = allDontIndexes.map {
        dontIndex =>
          val nextDoIndex: Int = allDoIndexes.find(_ > dontIndex).getOrElse(input.length - 1)
          Range(dontIndex, nextDoIndex)
      }

      multipliers.filterNot(multiplier => disabledRanges.exists(_.contains(multiplier.index)))
    }
  }
  object MultiplierEngine {
    def apply(input: String): MultiplierEngine = {

      val multiplyRegex = """mul\((\d+),(\d+)\)""".r

      val multipliers = multiplyRegex.findAllMatchIn(input).map { matched =>
        Multiplier(matched.start, matched.group(1).toInt, matched.group(2).toInt)
      }.toList

      MultiplierEngine(multipliers)
    }
  }
}
