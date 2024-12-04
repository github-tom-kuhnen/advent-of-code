package y2024.day3

import org.scalatest.flatspec.AnyFlatSpec
import y2024.day3.Model.{Multiplier, MultiplierEngine}

class ModelTest extends AnyFlatSpec {
  behavior of "MultiplierEngine"
  "apply" should "parse input and create a simple MultiplierEngine" in {
    val input = """mul(2,4)"""
    val expected = MultiplierEngine(List(Multiplier(0, 2, 4)))

    val result = MultiplierEngine.apply(input)
    assert(result == expected)
  }

  "apply" should "parse input and create a complex MultiplierEngine" in {
    val input = """xmul(2,4)%&mul[3,7]!@^do_not_mul(5,5)+mul(32,64]then(mul(11,8)mul(8,5))"""
    val expected = MultiplierEngine(List(Multiplier(1, 2, 4), Multiplier(29, 5, 5), Multiplier(53, 11, 8), Multiplier(62, 8, 5)))

    val result = MultiplierEngine.apply(input)
    assert(result == expected)

    assert(result.totalsSum == 161)
  }

  "filteredMultipliers" should "don't keep multipliers after don't" in {
    val input = """-mul(2,4)-----don't()--mul(4,5)"""
    val result = MultiplierEngine.apply(input)

    val expected = List(Multiplier(1, 2, 4))

    assert(result.filteredMultipliers(input) == expected)
  }

  "filteredMultipliers" should "re-enable future multiplier after do " in {
    val input = """-mul(2,4)---don't()--mul(4,5)---do()---mul(1,1)"""
    val result = MultiplierEngine.apply(input)

    val expected = List(Multiplier(1, 2, 4), Multiplier(39, 1, 1))

    assert(result.filteredMultipliers(input) == expected)
  }

  "filteredMultipliers" should "work for a complex case" in {
    val input = """xmul(2,4)&mul[3,7]!^don't()_mul(5,5)+mul(32,64](mul(11,8)undo()?mul(8,5))"""
    val result = MultiplierEngine.apply(input)

    assert(result.filteredTotalSum(input) == 48)
  }

}
