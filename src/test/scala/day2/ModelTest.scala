package day2

import day2.Model.{Bag,  Game, GameParty, Round}
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers._

class ModelTest extends AnyFlatSpec {
  behavior of "GameParty"
  "apply" should "create a GameParty" in {
    val input =
      """Game 1: 3 blue, 4 red; 1 red, 2 green, 6 blue; 2 green
        |Game 2: 1 blue, 2 green; 3 green, 4 blue, 1 red; 1 green, 1 blue""".stripMargin

    val gameParty = GameParty.apply(input)
    assert(gameParty.games.length == 2)
  }

  "power" should "sum all powers of games" in {
    val input =
      """Game 1: 3 blue, 4 red; 1 red, 2 green, 6 blue; 2 green
        |Game 2: 1 blue, 2 green; 3 green, 4 blue, 1 red; 1 green, 1 blue
        |Game 3: 8 green, 6 blue, 20 red; 5 blue, 4 red, 13 green; 5 green, 1 red
        |Game 4: 1 green, 3 red, 6 blue; 3 green, 6 red; 3 green, 15 blue, 14 red
        |Game 5: 6 red, 1 blue, 3 green; 2 blue, 1 red, 2 green""".stripMargin
    val gameParty = GameParty.apply(input)

    gameParty.power shouldBe 2286
  }

  behavior of "Game"
  "apply" should "create a game with id and rounds" in {
    val input = "Game 12: 3 blue, 4 red; 1 red, 2 green, 6 blue; 2 green"
    val game = Game.apply(input)
    assert(game.id == 12)
    assert(game.rounds.length == 3)
  }

  "maxColors" should "return the max of blue red & green" in {
    val possibleGameInput = "Game 12: 3 blue, 4 red; 1 red, 2 green, 6 blue; 2 green"
    val possibleGame = Game.apply(possibleGameInput)

    possibleGame.maxRed shouldBe 4
    possibleGame.maxBlue shouldBe 6
    possibleGame.maxGreen shouldBe 2

    possibleGame.power shouldBe 48

    val impossibleGameInput = "Game 12: 3 blue, 4 red; 1 red, 6 blue; 2 red"
    val impossibleGame = Game.apply(impossibleGameInput)

    impossibleGame.maxGreen shouldBe 0
  }

  behavior of "Round"
  "apply" should "create a round with cubes" in {
    val input = "3 blue, 4 red, 2 green"
    val round = Round.apply(input)
    round.blue shouldBe 3
    round.red shouldBe 4
    round.green shouldBe 2
  }

  "isPossibleRound" should "return true if its possible" in {
    val bag = Bag.apply(blue = 3, red = 4, green = 2)
    val input = "2 blue, 4 red, 2 green"
    val round = Round.apply(input)

    assert(round.isPossibleRound(bag))
  }
  "isPossibleRound" should "return false if its not possible" in {
    val bag = Bag.apply(blue = 3, red = 4, green = 2)
    val input = "4 blue, 4 red, 2 green"
    val round = Round.apply(input)

    assert(!round.isPossibleRound(bag))
  }


  behavior of "Bag"
  "possibleGames" should "return only possible games" in {
    val input =
      """Game 1: 3 blue, 4 red; 1 red, 2 green, 6 blue; 2 green
        |Game 2: 1 blue, 2 green; 3 green, 4 blue, 1 red; 1 green, 1 blue
        |Game 3: 8 green, 6 blue, 20 red; 5 blue, 4 red, 13 green; 5 green, 1 red
        |Game 4: 1 green, 3 red, 6 blue; 3 green, 6 red; 3 green, 15 blue, 14 red
        |Game 5: 6 red, 1 blue, 3 green; 2 blue, 1 red, 2 green""".stripMargin
    val gameParty = GameParty.apply(input)
    val bag = Bag.apply(blue = 14, red = 12, green = 13)

    val result = bag.possibleGames(gameParty)

    result.map(_.id) should contain only(1, 2, 5)
  }

}
