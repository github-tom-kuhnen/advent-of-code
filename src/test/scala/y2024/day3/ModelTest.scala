package y2024.day3

import org.scalatest.flatspec.AnyFlatSpec
import y2024.day3.Model.{Multiplier, MultiplierEngine}

class ModelTest extends AnyFlatSpec {
  behavior of "MultiplierEngine"
  "apply" should "parse input and create a simple MultiplierEngine" in {
    val input = """mul(2,4)"""
    val expected = MultiplierEngine(List(Multiplier(2, 4)))

    val result = MultiplierEngine.apply(input)
    assert(result == expected)
  }

  "apply" should "parse input and create a complex MultiplierEngine" in {
    val input = """xmul(2,4)%&mul[3,7]!@^do_not_mul(5,5)+mul(32,64]then(mul(11,8)mul(8,5))"""
    val expected = MultiplierEngine(List(Multiplier(2, 4), Multiplier(5, 5), Multiplier(11, 8), Multiplier(8, 5)))

    val result = MultiplierEngine.apply(input)
    assert(result == expected)

    assert(result.totalsSum == 161)
  }
}
