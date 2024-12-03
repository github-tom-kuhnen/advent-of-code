package y2023.day6

import y2023.day6.Model.{Race, RaceTable}
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers._

class ModelTest extends AnyFlatSpec {
  behavior of "Race"
  "isHoldingTimeWinning" should "return true if holding X seconds button wins the race" in {
    val race = Race(7, 9)

    race.isHoldingTimeWinning(0) shouldBe false
    race.isHoldingTimeWinning(1) shouldBe false
    race.isHoldingTimeWinning(2) shouldBe true
    race.isHoldingTimeWinning(3) shouldBe true
    race.isHoldingTimeWinning(4) shouldBe true
    race.isHoldingTimeWinning(5) shouldBe true
    race.isHoldingTimeWinning(6) shouldBe false
    race.isHoldingTimeWinning(7) shouldBe false
  }
  "differentWinningWays" should "return the number of different ways to win" in {
    Race(7, 9).differentWinningWays shouldBe 4
    Race(71530, 940200).differentWinningWays shouldBe 71503
  }

  behavior of "RaceTable"
  "apply" should "create a RaceTable with multiple races" in {
    val input =
      """Time:      7  15   30
        |Distance:  9  40  200""".stripMargin

    val expectedRaces = List(
      Race(7, 9),
      Race(15, 40),
      Race(30, 200),
    )

    assert(RaceTable.apply(input) == RaceTable(expectedRaces))
  }
  "apply" should "create a RaceTable with only one race" in {
    val input =
      """Time:      71530
        |Distance:  940200""".stripMargin

    val expectedRaces = List(
      Race(71530, 940200)
    )

    assert(RaceTable.apply(input) == RaceTable(expectedRaces))
  }
  "applyWithoutSpace" should "create a RaceTable with only one race" in {
    val input =
      """Time:      7  15   30
        |Distance:  9  40  200""".stripMargin

    val expectedRaces = List(
      Race(71530, 940200)
    )

    assert(RaceTable.apply(input, trimSpaces = true) == RaceTable(expectedRaces))
  }
  "differentWinningWaysEachRace" should "return the product of all differentWinningWays of all races " in {
    val raceTable = RaceTable(List(
      Race(7, 9),
      Race(15, 40),
      Race(30, 200),
    ))

    assert(raceTable.differentWinningWaysEachRace == 288)
  }
}
