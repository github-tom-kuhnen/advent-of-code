package y2023.day2

import y2023.day2.Model.{Bag, GameParty}
import y2023.services.FileReaderService

object Main extends App {

  val input = FileReaderService.read("y2023/day2.txt")
  val gameParty = GameParty.apply(input)

  private def firstPart(): Unit = {
    val bag = Bag.apply(blue = 14, red = 12, green = 13)

    val possibleGames = bag.possibleGames(gameParty)
    println(possibleGames.map(_.id).sum)
  }

  private def secondPart(): Unit = {
    println(gameParty.power)
  }

  firstPart() //2685
  secondPart() //83707
}