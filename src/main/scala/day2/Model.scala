package day2

object Model {
  private case class ColoredCubes(color: String, count: Int)
  private object ColoredCubes {
    def count(cubes: List[ColoredCubes], color: String): Int = cubes.find(_.color == color).map(_.count).getOrElse(0)
  }

  case class Bag(blue: Int, red: Int, green: Int) {
    def possibleGames(gameParty: GameParty): List[Game] =
      gameParty.games.filter { game =>
        game.rounds.forall(_.isPossibleRound(this))
      }
  }

  case class Round(blue: Int, red: Int, green: Int) {
    def isPossibleRound(bag: Bag): Boolean = red <= bag.red && blue <= bag.blue && green <= bag.green
  }
  object Round {
    def apply(input: String): Round = {
      val cubeGroups = input.split(",").map(_.trim).toList
      val regexCubeColor = """(\d+) (\w+)""".r
      val cubes: List[ColoredCubes] = cubeGroups.map {
        case regexCubeColor(nb, color) => ColoredCubes(color, nb.toInt)
      }
      val green: Int = ColoredCubes.count(cubes, "green")
      val red: Int = ColoredCubes.count(cubes, "red")
      val blue: Int = ColoredCubes.count(cubes, "blue")

      Round(blue, red, green)
    }
  }

  case class Game(id: Int, rounds: List[Round]) {
    val maxRed: Int = rounds.map(_.red).max
    val maxGreen: Int = rounds.map(_.green).max
    val maxBlue: Int = rounds.map(_.blue).max

    val power: Int = maxRed * maxGreen * maxBlue
  }
  object Game {
    def apply(input: String): Game = {
      val regexGameId = """Game (\d+):(.*)""".r

      input match {
        case regexGameId(gameId, roundsString) =>
          val rounds = roundsString.split(";").map(_.trim).map(Round.apply).toList
          Game(gameId.toInt, rounds)
        case _ => throw new Exception("Game regex didn't match")
      }
    }
  }

  case class GameParty(games: List[Game]) {
    val power: Int = games.map(_.power).sum
  }
  object GameParty {
    def apply(input: String): GameParty = GameParty(input.linesIterator.toList.map(Game.apply))
  }
}