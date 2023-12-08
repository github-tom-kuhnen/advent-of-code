package day8

import scala.annotation.tailrec

object Model {
  sealed trait Direction
  object Direction {
    object Left extends Direction
    object Right extends Direction
  }
  case class Node(value: String, ways: Ways)
  case class MoveTable(table: Map[String, Ways], moves: List[Direction]) {
    @tailrec
    final def moveFrom(node: String, index: Int = 0): Int = {
      if (node == "ZZZ") {
        index
      } else {
        val move = moves(index % moves.length)
        val ways = table(node)

        val nextNode = move match {
          case Direction.Left => ways.left
          case Direction.Right => ways.right
        }

        moveFrom(nextNode, index + 1)
      }
    }
  }

  object MoveTable {

    def apply(input: String): MoveTable = {
      val moves = toMoves(input.linesIterator.toList.head)
      val table = input.linesIterator.drop(2).flatMap(line => Node.unapply(toNode(line))).toMap
      MoveTable(table, moves)
    }

    def toMoves(input: String): List[Direction] = input.toList.map {
      case 'L' => Direction.Left
      case 'R' => Direction.Right
      case _ => throw new Exception("unknown move")
    }

    def toNode(input: String): Node = {
      val regexLine = """(\w+) = \((\w+), (\w+)\)""".r
      input match {
        case regexLine(node, left, right) => Node(node, Ways(left, right))
      }
    }
  }

  case class Ways(left: String, right: String)
}