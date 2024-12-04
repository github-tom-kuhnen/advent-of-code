package y2024.day3

object Model {

  case class Multiplier(a: Int, b: Int) {
    val total: Int = a * b
  }
  case class MultiplierEngine(multipliers: List[Multiplier]) {
    val totalsSum: Int = multipliers.map(_.total).sum
  }
  object MultiplierEngine {
    def apply(input: String): MultiplierEngine = {

      val regex = """mul\((\d+),(\d+)\)""".r

      val multipliers = regex.findAllMatchIn(input).map { matched =>
        Multiplier(matched.group(1).toInt, matched.group(2).toInt)
      }.toList

      MultiplierEngine(multipliers)
    }
  }
}
