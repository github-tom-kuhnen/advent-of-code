package day8

import day8.Model.{Direction, MoveTable, Node, Ways}
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers._

class ModelTest extends AnyFlatSpec {
  behavior of "MoveTable"
  "toMoves" should "return List of Directions from input" in {
    MoveTable.toMoves("LRL") shouldBe List(Direction.Left, Direction.Right, Direction.Left)
  }

  "toNode" should "return the table of moves from input" in {
    val input =
      "AAA = (BBB, CCC)"

    MoveTable.toNode(input) shouldBe Node("AAA", Ways("BBB", "CCC"))
  }

  "apply" should "return a MoveTable from input" in {
    val input =
      """RL
        |
        |AAA = (BBB, CCC)
        |BBB = (DDD, EEE)""".stripMargin

    val expectedTable = Map(
      "AAA" -> Ways("BBB", "CCC"),
      "BBB" -> Ways("DDD", "EEE")
    )
    val expectedMoves = List(Direction.Right, Direction.Left)
    val expected = MoveTable(expectedTable, expectedMoves)

    MoveTable.apply(input) shouldBe expected
  }
  "startSimpleRun" should "return the number of moves to go from AAA to ZZZ" in {
    val input =
      """LLR
        |
        |AAA = (BBB, BBB)
        |BBB = (AAA, ZZZ)
        |ZZZ = (ZZZ, ZZZ)""".stripMargin

    MoveTable.apply(input).startSimpleRun() shouldBe 6
  }
  "startSimpleRun" should "return the number of moves to go from AAA to ZZZ, simple case" in {
    val input =
      """RL
        |
        |AAA = (BBB, CCC)
        |BBB = (DDD, EEE)
        |CCC = (ZZZ, GGG)
        |DDD = (DDD, DDD)
        |EEE = (EEE, EEE)
        |GGG = (GGG, GGG)
        |ZZZ = (ZZZ, ZZZ)""".stripMargin

    MoveTable.apply(input).startSimpleRun() shouldBe 2
  }
    "startGhostRun" should "return the number of moves to go from all nodes ending with A to all nodes ending with Z" in {
      val input =
        """LR
          |
          |11A = (11B, XXX)
          |11B = (XXX, 11Z)
          |11Z = (11B, XXX)
          |22A = (22B, XXX)
          |22B = (22C, 22C)
          |22C = (22Z, 22Z)
          |22Z = (22B, 22B)
          |XXX = (XXX, XXX)""".stripMargin

      MoveTable.apply(input).startGhostRun() shouldBe 6
    }
}
