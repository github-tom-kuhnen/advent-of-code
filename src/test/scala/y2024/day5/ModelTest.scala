package y2024.day5

import org.scalatest.flatspec.AnyFlatSpec
import services.FileReaderService
import y2024.day5.Model.{PageOrderingRule, PageUpdate, SafetyManual}

class ModelTest extends AnyFlatSpec {
  behavior of "SafetyManual"

  "apply" should "parse an input a return a SafetyManual" in {

    val input =
      """47|53
        |97|13
        |
        |75,47,61,53,29
        |97,61,53,29,13""".stripMargin

    val result = SafetyManual.apply(input)

    val expected = SafetyManual(
      List(
        Model.PageOrderingRule(47, 53),
        Model.PageOrderingRule(97, 13)
      ),
      List(
        Model.PageUpdate(List(75, 47, 61, 53, 29)),
        Model.PageUpdate(List(97, 61, 53, 29, 13))
      )
    )

    assert(result == expected)
  }

  "getSafetyUpdates" should "return the updates that respect the ordering rules" in {
    val input =
      """47|53
        |97|13
        |
        |75,47,61,53,29
        |97,61,53,29,13""".stripMargin

    val safetyManual = SafetyManual.apply(input)

    val result = safetyManual.safetyUpdates

    val expected = List(
      PageUpdate(List(75, 47, 61, 53, 29)),
      PageUpdate(List(97, 61, 53, 29, 13))
    )

    assert(result == expected)
  }

  "getSafetyUpdates" should "return the updates that respect the ordering rules other case" in {
    val input =
      """47|53
        |97|13
        |
        |75,47,61,53,29
        |97,61,53,47,13""".stripMargin

    val safetyManual = SafetyManual.apply(input)

    val result = safetyManual.safetyUpdates

    val expected = List(
      PageUpdate(List(75, 47, 61, 53, 29))
    )

    assert(result == expected)
  }

  "safetyUpdates" should "return the updates that respect the ordering rules other case in a complex case" in {
    val input = FileReaderService.read("y2024/day5.txt")

    val safetyManual = SafetyManual.apply(input)

    val result = safetyManual.safetyUpdates

    val expected = List(
      PageUpdate(List(75, 47, 61, 53, 29)),
      PageUpdate(List(97, 61, 53, 29, 13)),
      PageUpdate(List(75, 29, 13))
    )

    assert(result == expected)
  }

  "middlePage" should "return the middle element of a pages list" in {
    val updates = PageUpdate(List(75, 47, 61, 53, 29))
    assert(updates.middlePage == 61)
  }

  "totalMiddleSafetyPages" should "return the sum of the middle pages of the safety updates" in {
    val input = FileReaderService.read("y2024/day5.txt")

    val safetyManual = SafetyManual.apply(input)

    val result = safetyManual.totalMiddleSafetyPages

    assert(result == 143)
  }

//  "computeSortedPages" should "return a list of pages sorted from the rules" in {
//    val input = """61|13
//                  |53|29
//                  |61|53""".stripMargin
//
//    val rules = List(PageOrderingRule(61, 13), PageOrderingRule(53, 29), PageOrderingRule(61, 53))
//
//    val result = SafetyManual.computeSortedPages(rules)
//
//    val expected = List(61, 13, 53, 29)
//
//    assert(result == expected)
//  }

//  "sortPagesUpdate" should "sort pages from ordered pages" in {
//    val toUpdate = PageUpdate(List(53, 47, 56, 61, 26))
//    val orderedPages = List(26, 61, 47, 56, 53)
//
//    val expected = PageUpdate(List(53, 26, 61, 47, 33))
//
//    val result = toUpdate.sortPagesUpdate(orderedPages)
//
//    assert(result == expected)
//  }
}
