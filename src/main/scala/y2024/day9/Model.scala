package y2024.day9

object Model {

  trait Unit {
    val value: Int
  }
  case class BlocFile(index: Int, value: Int) extends Unit
  case class FreeSpace(value: Int) extends Unit

  case class DiskUnit(value: Int)
  object DiskUnit {
    object Empty extends DiskUnit(-1)
  }

  case class DiskMap(units: List[Unit]) {
    val diskUnits: List[DiskUnit] = units.flatMap {
      case BlocFile(index, value) => List.fill(value)(DiskUnit(index))
      case FreeSpace(value) => List.fill(value)(DiskUnit.Empty)
    }
  }

  object DiskMap {
    def apply(input: String): DiskMap = {
      val units = input.zipWithIndex.map {
        case (value, index) =>
          if (index % 2 == 0) BlocFile(index / 2, value.toString.toInt)
          else FreeSpace(value.toString.toInt)
      }.toList
      DiskMap(units)
    }

    def reorderDiskUnits(diskUnits: Seq[DiskUnit]): Seq[DiskUnit] = {

      var maxIndex = diskUnits.length - 1

      diskUnits.zipWithIndex.map {
        case (_, index) if index > maxIndex => DiskUnit.Empty
        case (DiskUnit.Empty, index) =>
          var lastElement: Option[DiskUnit] = None
          while (lastElement.isEmpty && maxIndex > index) {
            diskUnits(maxIndex) match {
              case DiskUnit.Empty =>
              case unit =>
                lastElement = Some(unit)
            }
            maxIndex -= 1
          }
          lastElement.getOrElse(DiskUnit.Empty)
        case (unit, _) => unit
      }
    }

    def checksum(diskUnits: Seq[DiskUnit]): Long = {
      diskUnits.zipWithIndex.map {
        case (DiskUnit.Empty, _) => 0.toLong
        case (DiskUnit(value), index) => value.toLong * index.toLong
      }.sum
    }

    def diskUnitsToString(diskUnits: Seq[DiskUnit]): String = diskUnits.map {
      case DiskUnit.Empty => '.'
      case DiskUnit(value) => value.toString
    }.mkString
  }
}
