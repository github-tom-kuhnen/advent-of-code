package y2024.day9

object Model {

  trait Unit {
    val value: Int
  }
  case class BlocFile(index: Int, value: Int) extends Unit
  case class FreeSpace(value: Int) extends Unit

  case class DiskUnit(value: Char)
  object DiskUnit {
    object Empty extends DiskUnit('.')
  }

  case class DiskMap(units: List[Unit]) {
    val diskUnits: List[DiskUnit] = units.flatMap {
      case BlocFile(index, value) => (index.toString * value).map(DiskUnit(_))
      case FreeSpace(value) => ("." * value).map(DiskUnit(_))
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

    def reorderDiskUnits(diskUnits: List[DiskUnit]): List[DiskUnit] = {
      var reorderedDiskUnits = diskUnits

      var endDiskUnitsIndex = reorderedDiskUnits.length - 1
      var index = 0
      while (index < endDiskUnitsIndex) {
        if (reorderedDiskUnits(index) == DiskUnit.Empty) {
          var hasBeenReordered = false
          while (index < endDiskUnitsIndex && !hasBeenReordered) {
            reorderedDiskUnits(endDiskUnitsIndex) match {
              case DiskUnit.Empty =>
                endDiskUnitsIndex -= 1
              case unit =>
                reorderedDiskUnits = reorderedDiskUnits.updated(index, unit)
                reorderedDiskUnits = reorderedDiskUnits.updated(endDiskUnitsIndex, DiskUnit.Empty)
                hasBeenReordered = true
            }
          }
        }

        index += 1
      }

      reorderedDiskUnits
    }

    def checksum(diskUnits: List[DiskUnit]): Int = {
      diskUnits.zipWithIndex.map {
        case (DiskUnit.Empty, _) => 0
        case (DiskUnit(value), index) => value.toString.toInt * index
      }.sum
    }

    def diskUnitsToString(diskUnits: List[DiskUnit]): String = diskUnits.map(_.value).mkString
  }
}
