package day3

import scala.util.matching.Regex

object Model {
  case class Engine(parts: List[Part]) {
    private val symbols: List[Part.Symbol] = parts.collect {
      case part: Part.Symbol => part
    }
    val validNumberParts: List[Part.Number] = parts.collect {
      case part: Part.Number if isNumberAdjacent(part) => part
    }
    val sumValidNumbers: Int = validNumberParts.map(_.value).sum
    private def isNumberAdjacent(part: Part.Number) = {
      part.adjacentCoordinates.exists(coordinates => symbols.exists(_.coordinates == coordinates))
    }
  }
  object Engine {
    def apply(input: String): Engine = {
      val parts: List[Part] = input.split("\n").zipWithIndex.flatMap {
        case (line, index) =>
          def matchRegexWithIndex(regex: Regex): List[(String, Coordinates)] =
            regex.findAllMatchIn(line).map(matched => (matched.group(0), Coordinates(matched.start, index))).toList

          matchRegexWithIndex("""\d+""".r).map {
            case (value, coordinates) => Part.Number(value.toInt, coordinates)
          } ++
            matchRegexWithIndex("""[^\w.]""".r).map {
              case (value, coordinates) => Part.Symbol(value, coordinates)
            }
      }.toList

      Engine(parts)
    }
  }
  case class Coordinates(x: Int, y: Int)

  sealed trait Part {
    val coordinates: Coordinates
  }
  object Part {
    case class Number(value: Int, coordinates: Coordinates) extends Part {
      val adjacentCoordinates: List[Coordinates] = {
        val listX = (coordinates.x - 1) to (coordinates.x + value.toString.length)

        listX.map(Coordinates(_, coordinates.y - 1)) ++
          listX.map(Coordinates(_, coordinates.y + 1)) :+
          Coordinates(coordinates.x - 1, coordinates.y) :+
          Coordinates(coordinates.x + value.toString.length, coordinates.y)
      }.toList
    }
    case class Symbol(value: String, coordinates: Coordinates) extends Part
  }
}