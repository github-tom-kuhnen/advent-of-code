package y2024.day9

import org.scalatest.flatspec.AnyFlatSpec
import y2024.day9.Model.{BlocFile, DiskMap, FreeSpace}

class ModelTest extends AnyFlatSpec {
  behavior of "DiskMap"

  "apply" should "parse a string a return a DiskMap" in {
    val expected = DiskMap(List(BlocFile(0, 1), FreeSpace(2), BlocFile(1, 3), FreeSpace(4), BlocFile(2, 5)))
    assert(DiskMap("12345") == expected)
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
    val result = DiskMap.reorderDiskUnits(diskMap)

    val expected = "022111222......"

    assert(DiskMap.diskUnitsToString(result) == expected)
  }


  "reorderDiskUnits" should "reorder disk units complex" in {
    // "00...111...2...333.44.5555.6666.777.888899"
    val diskMap = DiskMap.apply("2333133121414131402").diskUnits
    val result = DiskMap.reorderDiskUnits(diskMap)

    val expected = "0099811188827773336446555566.............."

    assert(DiskMap.diskUnitsToString(result) == expected)
  }

  "checksum" should "calculate the checksum" in {
    val diskMap = DiskMap.apply("2333133121414131402").diskUnits
    val orderedDiskUnits = DiskMap.reorderDiskUnits(diskMap)

    val result = DiskMap.checksum(orderedDiskUnits)

    assert(result == 1928)
  }

}
