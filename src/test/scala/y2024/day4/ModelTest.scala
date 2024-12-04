package y2024.day4

import org.scalatest.flatspec.AnyFlatSpec
import services.FileReaderService
import y2024.day4.Model.XmasGrid

class ModelTest extends AnyFlatSpec {
  behavior of "XmasGrid"
  "apply" should "parse the input and create new XmasGrid" in {

    val inputTxt = FileReaderService.read("y2024/day4.txt")

    val result = XmasGrid.apply(inputTxt)

    assert(result.rows.head.mkString == "MMMSXXMASM")
    assert(result.rows.last.mkString == "MXMXAXMASX")
  }

  "nbXmas" should "return the number of Xmas in a simple grid" in {
    val inputTxt =
      """..X...
        |.SAMX.
        |.A..A.
        |XMAS.S
        |.X....""".stripMargin

    val result = XmasGrid.apply(inputTxt)

    assert(result.nbXmas == 4)
  }

  "nbXmas" should "return the number of Xmas in a mid-complex grid" in {
    val inputTxt =
      """MMMSXXMASM
        |MSAMXMSMSA
        |AMXSXMAAMM
        |MSAMASMSMX
        |""".stripMargin

    /*
      ....XXMAS.
      .SAMXM....
      ......A...
      .......S..
     */

    val result = XmasGrid.apply(inputTxt)

    assert(result.nbXmas == 3)
  }

  "nbXmas" should "return the number of Xmas in a complex grid" in {
    val input = FileReaderService.read("y2024/day4.txt")

    val result = XmasGrid.apply(input)

    assert(result.nbXmas == 18)
  }

  "nbMasX" should "return the number of MasX in a simple grid" in {
    val inputTxt =
      """M.S
        |.A.
        |M.S""".stripMargin

    val result = XmasGrid.apply(inputTxt)

    assert(result.nbMasX == 1)
  }


  "nbMasX" should "return the number of MasX in a complex grid" in {
    val input = FileReaderService.read("y2024/day4.txt")

    val result = XmasGrid.apply(input)

    assert(result.nbMasX == 9)
  }
}
