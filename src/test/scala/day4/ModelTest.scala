package day4

import day4.Model.{Card, CardTable}
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers._

class ModelTest extends AnyFlatSpec {
  behavior of "Card"
  "apply" should "create a Card" in {
    val input: String = """Card 1: 41 48 83 86 17 | 83 86  6 31 17  9 48 53"""
    val expected = Card(id = 1, winningNumbers = List(41, 48, 83, 86, 17), playerNumbers = List(83, 86, 6, 31, 17, 9, 48, 53))
    assert(Card.apply(input) == expected)
  }

  "score" should "calculate the right score if there is multiple winning player numbers" in {
    val input: String = """Card 1: 41 48 83 86 17 | 83 86  6 31 17  9 48 53"""
    assert(Card.apply(input).score == 8)
  }
  "score" should "calculate the right score if there is 1 winning player numbers" in {
    assert(Card(1, List(1, 2, 3), List(1, 4, 5)).score == 1)
  }
  "score" should "calculate the right score if there no multiple player numbers" in {
    assert(Card(1, List(1, 2, 3), List(4, 5, 6)).score == 0)
  }

  behavior of "CardTable"
  "apply" should "create a CardTable" in {
    val input: String =
      """Card 1: 41 48 83 86 17 | 83 86  6 31 17  9 48 53
        |Card 2: 13 32 20 16 61 | 61 30 68 82 17 32 24 19""".stripMargin
    val expected = CardTable(cards = List(
      Card(id = 1, winningNumbers = List(41, 48, 83, 86, 17), playerNumbers = List(83, 86, 6, 31, 17, 9, 48, 53)),
      Card(id = 2, winningNumbers = List(13, 32, 20, 16, 61), playerNumbers = List(61, 30, 68, 82, 17, 32, 24, 19))))

    assert(CardTable.apply(input) == expected)
  }
  "score" should "calculate the right score of a CardTable" in {
    val input: String =
      """Card 1: 41 48 83 86 17 | 83 86  6 31 17  9 48 53
        |Card 2: 13 32 20 16 61 | 61 30 68 82 17 32 24 19
        |Card 3:  1 21 53 59 44 | 69 82 63 72 16 21 14  1
        |Card 4: 41 92 73 84 69 | 59 84 76 51 58  5 54 83
        |Card 5: 87 83 26 28 32 | 88 30 70 12 93 22 82 36
        |Card 6: 31 18 13 56 72 | 74 77 10 23 35 67 36 11""".stripMargin
    assert(CardTable.apply(input).score == 13)
  }
  "winningCardsForCard" should "return all winning cards for a card" in {
    val card1 = Card(id = 1, winningNumbers = List(1, 2, 3), playerNumbers = List(1, 2, 5)) // 2 winner numbers
    val card2 = Card(id = 2, winningNumbers = List(1, 2, 3), playerNumbers = List(1, 2, 5)) // 2 winner numbers
    val card3 = Card(id = 3, winningNumbers = List(1, 2, 3), playerNumbers = List(1, 2, 5)) // 2 winner numbers
    val card4 = Card(id = 4, winningNumbers = List(1, 2, 3), playerNumbers = List(1, 2, 5))
    val cardTable = CardTable(cards = List(card1, card2, card3, card4))

    assert(cardTable.winningCardsForCard(card1).length == 7)
  }
  "winningCards" should "return all winning cards for a simple case" in {
    val card1 = Card(id = 1, winningNumbers = List(1, 2, 3), playerNumbers = List(1, 2, 5)) // 2 winner numbers
    val card2 = Card(id = 2, winningNumbers = List(1, 2, 3), playerNumbers = List(1, 2, 5)) // 2 winner numbers
    val card3 = Card(id = 3, winningNumbers = List(1, 2, 3), playerNumbers = List(1, 2, 5)) // 2 winner numbers
    val card4 = Card(id = 4, winningNumbers = List(1, 2, 3), playerNumbers = List(4, 5, 6))
    val cardTable = CardTable(cards = List(card1, card2, card3, card4))

    assert(cardTable.winningCards.length == 14)
  }

  "winningCards" should "return all winning cards" in {
    val input: String =
      """Card 1: 41 48 83 86 17 | 83 86  6 31 17  9 48 53
        |Card 2: 13 32 20 16 61 | 61 30 68 82 17 32 24 19
        |Card 3:  1 21 53 59 44 | 69 82 63 72 16 21 14  1
        |Card 4: 41 92 73 84 69 | 59 84 76 51 58  5 54 83
        |Card 5: 87 83 26 28 32 | 88 30 70 12 93 22 82 36
        |Card 6: 31 18 13 56 72 | 74 77 10 23 35 67 36 11 """.stripMargin
    val cardTable = CardTable.apply(input)
    assert(cardTable.winningCards.length == 30)
  }
}
