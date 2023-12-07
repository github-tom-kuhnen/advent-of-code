package day7

import day7.Model.{Hand, HandBid, HandKind, Table}
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers._

class ModelTest extends AnyFlatSpec {
  behavior of "Hand"
  "apply" should "return a Hand of 5 cards" in {
    val input: String = "T55J5"
    val expected = Hand(List('T', '5', '5', 'J', '5'))
    Hand.apply(input) shouldBe expected
  }

  "handKind" should "return the right hand kind" in {
    Hand.apply("AKQJT").handKind shouldBe HandKind.HighCard
    Hand.apply("AAKQJ").handKind shouldBe HandKind.OnePair
    Hand.apply("AAKKJ").handKind shouldBe HandKind.TwoPair
    Hand.apply("AAAQJ").handKind shouldBe HandKind.ThreeCards
    Hand.apply("AAKKK").handKind shouldBe HandKind.FullHouse
    Hand.apply("AAAAJ").handKind shouldBe HandKind.FourCards
    Hand.apply("AAAAA").handKind shouldBe HandKind.FiveCards
  }

  "compareTo" should "compare two hands of different kinds" in {
    Hand.apply("AAAAA").compareTo(Hand.apply("AAAAQ")) shouldBe 1
    Hand.apply("AKQJT").compareTo(Hand.apply("AAAAA")) shouldBe -1
    Hand.apply("TTTTT").compareTo(Hand.apply("TTTTT")) shouldBe 0
  }

  "compareTo" should "compare two hands of same kinds" in {
    Hand.apply("AAAAA").compareTo(Hand.apply("KKKKK")) shouldBe 1
    Hand.apply("AAT33").compareTo(Hand.apply("AAJ22")) shouldBe -1
  }

  "compareSameCard" should "compare two cards" in {
    Hand.compareCard('A', 'T') shouldBe 1
    Hand.compareCard('T', 'A') shouldBe -1
    Hand.compareCard('T', 'T') shouldBe 0
  }

  "sortHands" should "sort the hands" in {
    val given = List(
      Hand.apply("32T3K"),
      Hand.apply("T55J5"),
      Hand.apply("KK677"),
      Hand.apply("KTJJT"),
      Hand.apply("QQQJA"),
    )

    given.sorted shouldBe List(
      Hand.apply("32T3K"),
      Hand.apply("KK677"),
      Hand.apply("T55J5"),
      Hand.apply("QQQJA"),
      Hand.apply("KTJJT"),
    )
  }

  behavior of "Table"
  "apply" should "return a Table of 5 hand bids" in {
    val input: String =
      """32T3K 765
        |T55J5 684""".stripMargin
    val expected = Table(List(
      HandBid(Hand.apply("32T3K"), 765),
      HandBid(Hand.apply("T55J5"), 684)
    ))
    Table.apply(input) shouldBe expected
  }

  "totalWinnings" should "calculate the totalWinnings" in {
    val input: String =
      """32T3K 765
        |T55J5 684
        |KK677 28
        |KTJJT 220
        |QQQJA 483""".stripMargin

    Table.apply(input).totalWinnings shouldBe 5905
  }

  "bestHandKindWithJoker" should "return the best hand kind with jokers" in {
    Hand.apply("32T3K").bestHandKindWithJoker shouldBe HandKind.OnePair
    Hand.apply("T55J5").bestHandKindWithJoker shouldBe HandKind.FourCards
    Hand.apply("KK677").bestHandKindWithJoker shouldBe HandKind.TwoPair
    Hand.apply("KTJJT").bestHandKindWithJoker shouldBe HandKind.FourCards
    Hand.apply("QQQJA").bestHandKindWithJoker shouldBe HandKind.FourCards
  }

  "compareTo" should "compare with comparing with jokes" in {
    Hand.apply("TTTT2").compareTo(Hand.apply("JKKK2")) shouldBe 1
    Hand.apply("JJJJJ").compareTo(Hand.apply("JJJJJ")) shouldBe 0
  }
}
