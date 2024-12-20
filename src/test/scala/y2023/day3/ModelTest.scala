package y2023.day3

import y2023.day3.Model.{Coordinates, Engine, Part}
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers._

class ModelTest extends AnyFlatSpec {
  behavior of "Engine"
  "apply" should "parse input and create Engine" in {
    val input =
      """467..114..
        |...*......
        |..35..633.""".stripMargin

    Engine.apply(input).parts should contain only(
      Part.Number(467, Coordinates(0, 0)),
      Part.Number(114, Coordinates(5, 0)),
      Part.Number(35, Coordinates(2, 2)),
      Part.Number(633, Coordinates(6, 2)),
      Part.Symbol("*", Coordinates(3, 1)))
  }

  "validNumberParts" should "return only number parts without adjacent symbols" in {
    val input =
      """467..114..
        |...*......
        |..35..633.""".stripMargin

    Engine.apply(input).validNumberParts should contain only(
      Part.Number(467, Coordinates(0, 0)),
      Part.Number(35, Coordinates(2, 2)))
  }


  "sumValidNumbers" should "return the sum of valid Numbers" in {
    val input =
      """467..114..
        |...*......
        |..35..633.
        |......#...
        |617*......
        |.....+.58.
        |..592.....
        |......755.
        |...$.*....
        |.664.598..""".stripMargin

    Engine.apply(input).sumValidNumbers shouldBe 4361
  }


  "gears" should "return all gears" in {
    val input =
      """467..114..
        |...*......
        |..35..633.
        |......#...
        |617*......
        |.....+.58.
        |..592.....
        |......755.
        |...$.*....
        |.664.598..""".stripMargin

    Engine.apply(input).gears.map(_.symbol) should contain only(
      Part.Symbol("*", Coordinates(3, 1)),
      Part.Symbol("*", Coordinates(5, 8)),
    )
  }

  "sumGearsRatio" should "return the sum of all gear ratios" in {
    val input =
      """467..114..
        |...*......
        |..35..633.
        |......#...
        |617*......
        |.....+.58.
        |..592.....
        |......755.
        |...$.*....
        |.664.598..""".stripMargin

    Engine.apply(input).sumGearsRatio shouldBe 467835
  }

  behavior of "Part.Number"
  "forbiddenSymbolCoordinates" should "return all forbidden coordinates" in {
    val input = Part.Number(12, Coordinates(0, 0))

    input.adjacentCoordinates should contain only(
      Coordinates(-1, -1),
      Coordinates(0, -1),
      Coordinates(1, -1),
      Coordinates(2, -1),
      Coordinates(-1, 1),
      Coordinates(0, 1),
      Coordinates(1, 1),
      Coordinates(2, 1),
      Coordinates(-1, 0),
      Coordinates(2, 0))
  }
}
