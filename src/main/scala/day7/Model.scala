package day7

import day7.Model.HandKind._

import scala.annotation.tailrec

object Model {
  case class HandBid(hand: Hand, bid: Int)
  object HandBid {
    def apply(input: String): HandBid = {
      val regex = """^(\w+)\s+(\d+)$""".r
      input match {
        case regex(hand, bid) => HandBid(Hand.apply(hand), bid.toInt)
      }
    }
  }

  case class Table(handBids: List[HandBid]) {
    val totalWinnings: Int = handBids.sortBy(_.hand).zipWithIndex.map {
      case (handBid, index) => handBid.bid * (index + 1)
    }.sum
  }
  object Table {
    def apply(input: String): Table = Table(input.linesIterator.map(HandBid.apply).toList)
  }

  case class Hand(cards: List[Char]) {
    val handKind: HandKind = {
      val maxCardCombination = cards.groupBy(identity).values.map(_.size).max
      maxCardCombination match {
        case 5 => FiveCards
        case 4 => FourCards
        case 3 if cards.distinct.size == 2 => FullHouse
        case 3 => ThreeCards
        case 2 if cards.distinct.size == 3 => TwoPair
        case 2 => OnePair
        case _ => HighCard
      }
    }

    def compareTo(hand: Hand): Int = {
      if (this == hand) {
        0
      } else {
        val comparedValue = handKindsSortedByValue.indexOf(hand.bestHandKindWithJoker).compareTo(handKindsSortedByValue.indexOf(this.bestHandKindWithJoker))
        if (comparedValue == 0) {
          Hand.compareSameHandKind(this, hand)
        } else {
          comparedValue
        }
      }
    }

    def bestHandKindWithJoker: HandKind = {
      cards.count(_ == 'J') match {
        case 5 => FiveCards
        case 0 => handKind
        case nbJokers =>
          val otherCards = cards.filter(_ != 'J')
          val replacedHands = otherCards.distinct.map(card => otherCards ++ List.fill(nbJokers)(card)).map(Hand.apply)
          replacedHands.max.handKind
      }
    }
  }
  object Hand {
    private val cardsSortedByValue: List[Char] = List('A', 'K', 'Q', 'T', '9', '8', '7', '6', '5', '4', '3', '2', 'J')
    def apply(input: String): Hand = Hand(input.toList)

    @tailrec
    private def compareSameHandKind(handA: Hand, handB: Hand, index: Int = 0): Int = {
      val comparedCard = compareCard(handA.cards(index), handB.cards(index))
      if (comparedCard == 0) {
        compareSameHandKind(handA, handB, index + 1)
      } else {
        comparedCard
      }
    }

    def compareCard(cardA: Char, cardB: Char): Int = {
      cardsSortedByValue.indexOf(cardB).compareTo(cardsSortedByValue.indexOf(cardA))
    }

    implicit def ordering: Ordering[Hand] = (x: Hand, y: Hand) => x.compareTo(y)
  }
  sealed trait HandKind
  object HandKind {
    object FiveCards extends HandKind
    object FourCards extends HandKind
    object FullHouse extends HandKind
    object ThreeCards extends HandKind
    object TwoPair extends HandKind
    object OnePair extends HandKind
    object HighCard extends HandKind

    val handKindsSortedByValue: List[HandKind] = List(FiveCards, FourCards, FullHouse, ThreeCards, TwoPair, OnePair, HighCard)
  }
}