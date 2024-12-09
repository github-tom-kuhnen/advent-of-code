package y2024.day9

object Model {

  trait Unit {
    val value: Int
  }
  case class BlocFile(value: Int) extends Unit
  case class FreeSpace(value: Int) extends Unit

  case class DiskMap(units: List[Unit])

  object DiskMap {
    def apply(input: String): DiskMap = {
      val units = input.zipWithIndex.map {
        case (value, index) =>
          if (index % 2 == 0) BlocFile(value.toString.toInt)
          else FreeSpace(value.toString.toInt)
      }.toList
      DiskMap(units)
    }
  }
}
