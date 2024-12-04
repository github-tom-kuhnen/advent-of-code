package y2024.day4

object Model {

  case class Direction(rowMove: Int, columnMove: Int) {
    def opposite: Direction = Direction(-rowMove, columnMove)
  }
  private case class Cell(char: Char, iRow: Int, iColumn: Int)
  case class XmasGrid(rows: List[List[Char]]) {
    private val rowsCount: Int = rows.length
    private val columnsCount: Int = rows.head.length

    private def getOppositeXCells(from: Cell, direction: Direction): List[Cell] = {
      val oppositeDirection = direction.opposite
      val listCells = for {
        firstCell <- direction match {
          case Direction(x, _) => getCell(from, Direction(x * 2, 0))
        }
        secondCell <- getCell(firstCell, oppositeDirection)
        thirdCell <- getCell(secondCell, oppositeDirection)
      } yield List(firstCell, secondCell, thirdCell)
      listCells.getOrElse(List.empty)
    }

    private def getCell(from: Cell, direction: Direction): Option[Cell] = {
      val newRow = from.iRow + direction.rowMove
      val newColumn = from.iColumn + direction.columnMove
      if (newRow >= 0 && newRow < rowsCount && newColumn >= 0 && newColumn < columnsCount) {
        Some(Cell(rows(newRow)(newColumn), newRow, newColumn))
      } else {
        None
      }
    }

    val nbXmas: Int = {
      val xmasWords = for {
        (row, iRow) <- rows.zipWithIndex
        (char, iColumn) <- row.zipWithIndex
        if char == 'X'
      } yield nbXmasWords(Cell(char, iRow, iColumn))
      xmasWords.sum
    }

    val nbMasX: Int = {
      val masXWords = for {
        (row, iRow) <- rows.zipWithIndex
        (char, iColumn) <- row.zipWithIndex
        if char == 'M'
      } yield nbMasXWords(Cell(char, iRow, iColumn))
      masXWords.sum / 2
    }

    private def nbXmasWords(from: Cell): Int = {
      val adjacentCells = getAdjacentCells(from)
      val mCells = adjacentCells.filter(_.char == 'M')

      mCells.count {
        mCell =>
          val direction = Direction(mCell.iRow - from.iRow, mCell.iColumn - from.iColumn)
          getCell(mCell, direction) match {
            case Some(cell) if cell.char == 'A' =>
              getCell(cell, direction).exists(_.char == 'S')
            case _ => false
          }
      }
    }


    private def nbMasXWords(from: Cell): Int = {
      val aCells = getAdjacentXCells(from).filter(_.char == 'A')

      aCells.count {
        aCell =>
          val direction = Direction(aCell.iRow - from.iRow, aCell.iColumn - from.iColumn)
          getCell(aCell, direction) match {
            case Some(cell) if cell.char == 'S' =>
              val oppositeXCells = getOppositeXCells(from, direction).map(_.char)
              oppositeXCells == List('M', 'A', 'S') || oppositeXCells == List('S', 'A', 'M')
            case _ => false
          }
      }
    }

    private def getAdjacentCells(from: Cell): List[Cell] = {
      val directions = List(
        Direction(-1, 0), Direction(-1, 1), Direction(0, 1), Direction(1, 1),
        Direction(1, 0), Direction(1, -1), Direction(0, -1), Direction(-1, -1)
      )
      directions.flatMap(getCell(from, _))
    }


    private def getAdjacentXCells(from: Cell): List[Cell] = {
      val directions = List(
        Direction(-1, 1), Direction(1, 1),
        Direction(1, -1), Direction(-1, -1)
      )
      directions.flatMap(getCell(from, _))
    }
  }

  object XmasGrid {
    def apply(input: String): XmasGrid = {
      XmasGrid(rows = input.split("\n").map(_.toList).toList)
    }
  }
}
