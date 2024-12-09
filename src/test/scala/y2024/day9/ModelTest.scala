package y2024.day9

import org.scalatest.flatspec.AnyFlatSpec
import y2024.day9.Model.{BlocFile, DiskMap, FreeSpace}

class ModelTest extends AnyFlatSpec {
  behavior of "DiskMap"

  "apply" should "parse a string a return a DiskMap" in {
    val input = "12345"
    val expected = DiskMap(List(BlocFile(1), FreeSpace(2), BlocFile(3), FreeSpace(4), BlocFile(5)))
    assert(DiskMap(input) == expected)
  }
}
