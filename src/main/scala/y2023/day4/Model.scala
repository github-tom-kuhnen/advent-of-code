package y2023.day4

object Model {
  case class Card(id: Int, winningNumbers: List[Int], playerNumbers: List[Int]) {
    val intersectNumbers: List[Int] = winningNumbers.intersect(playerNumbers)
    val score: Int = {
      intersectNumbers match {
        case ::(_, next) => scala.math.pow(2, next.length).toInt
        case Nil => 0
      }
    }
  }
  object Card {
    def apply(input: String): Card = {
      val regex = """^Card\s+(\d+):(.*?)\|(.*?)$""".r
      input match {
        case regex(id, winningNumbersInput, playerNumbersInput) =>
          val winningNumbers: List[Int] = winningNumbersInput.trim.split("""\s+""").map(_.trim.toInt).toList
          val playerNumbers: List[Int] = playerNumbersInput.trim.split("""\s+""").map(_.trim.toInt).toList
          Card(id = id.toInt, winningNumbers = winningNumbers, playerNumbers = playerNumbers)
        case _ => throw new Exception(
          s"""Regex doesn't match. Input :
             |$input""".stripMargin)
      }
    }
  }
  case class CardTable(cards: List[Card]) {
    val score: Int = cards.map(_.score).sum
    def winningCardsForCard(card: Card): List[Card] = {
      val copiedCards = cards.slice(card.id, card.id + card.intersectNumbers.length)
      List(card) ++ copiedCards.flatMap(winningCardsForCard)
    }
    val winningCards: List[Card] = cards.flatMap(winningCardsForCard)
  }
  object CardTable {
    def apply(input: String): CardTable = {
      CardTable(cards = input.split("\n").map(Card.apply).toList)
    }
  }
}