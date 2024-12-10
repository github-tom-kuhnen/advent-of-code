package y2024.day9

import org.scalatest.flatspec.AnyFlatSpec
import y2024.day9.Model.{BlocFile, DiskMap, DiskUnit, FreeSpace}

class ModelTest extends AnyFlatSpec {
  behavior of "DiskMap"

  "apply" should "parse a string a return a DiskMap" in {
    val expected = DiskMap(List(BlocFile(0, 1), FreeSpace(2), BlocFile(1, 3), FreeSpace(4), BlocFile(2, 5)))
    assert(DiskMap("12345") == expected)
  }


  "diskUnits" should "List of DiskUnits even after 10" in {
    val result = DiskMap.apply("111111111111111111111111").diskUnits

    //   "0.1.2.3.4.5.6.7.8.9.10.11."
    val expected = List(
      DiskUnit(0),
      DiskUnit.Empty,
      DiskUnit(1),
      DiskUnit.Empty,
      DiskUnit(2),
      DiskUnit.Empty,
      DiskUnit(3),
      DiskUnit.Empty,
      DiskUnit(4),
      DiskUnit.Empty,
      DiskUnit(5),
      DiskUnit.Empty,
      DiskUnit(6),
      DiskUnit.Empty,
      DiskUnit(7),
      DiskUnit.Empty,
      DiskUnit(8),
      DiskUnit.Empty,
      DiskUnit(9),
      DiskUnit.Empty,
      DiskUnit(10),
      DiskUnit.Empty,
      DiskUnit(11),
      DiskUnit.Empty
    )
    assert(result == expected)
  }

  "diskUnitsString" should "output in string format the units" in {
    val expected = "0..111....22222"
    val result = DiskMap.apply("12345").diskUnits
    assert(DiskMap.diskUnitsToString(result) == expected)
  }

  "diskUnitsString" should "output string complex input" in {
    val result = DiskMap.apply("2333133121414131402").diskUnits
    val expected = "00...111...2...333.44.5555.6666.777.888899"
    assert(DiskMap.diskUnitsToString(result) == expected)
  }

  "reorderDiskUnits" should "reorder disk units" in {
    // "0..111....22222"
    val diskMap = DiskMap.apply("12345").diskUnits
    val result = DiskMap.diskUnitsToString(DiskMap.reorderDiskUnits(diskMap))

    val expected = "022111222......"

    assert(result == expected)
  }


  "reorderDiskUnits" should "reorder disk units complex" in {
    // "00...111...2...333.44.5555.6666.777.888899"
    val diskMap = DiskMap.apply("2333133121414131402").diskUnits
    val result = DiskMap.diskUnitsToString(DiskMap.reorderDiskUnits(diskMap))

    val expected = "0099811188827773336446555566.............."

    assert(result == expected)
  }


  "diskUnits" should "get disk units > 10 indices" in {
    // "0.1.2.3.4.5.6.7.8.9.10.11.
    val result = DiskMap.apply("111111111111111111111111").diskUnits

    val expected = "0.1.2.3.4.5.6.7.8.9.10.11."

    assert(DiskMap.diskUnitsToString(result) == expected)
  }

  "reorderDiskUnits" should "reorder disk units complex > 10 indices" in {
    // "0.1.2.3.4.5.6.7.8.9.10.11.
    val diskMap = DiskMap.apply("111111111111111111111111").diskUnits
    val result = DiskMap.diskUnitsToString(DiskMap.reorderDiskUnits(diskMap))

    val expected = "01111029384756............"

    assert(result == expected)
  }

  "checksum" should "calculate the checksum" in {
    val diskMap = DiskMap.apply("2333133121414131402").diskUnits
    val orderedDiskUnits = DiskMap.reorderDiskUnits(diskMap)

    val result = DiskMap.checksum(orderedDiskUnits)

    assert(result == 1928)
  }
}
