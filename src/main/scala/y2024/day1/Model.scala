package y2024.day1

object Model {

  case class LocationLists(list1: Seq[Int], list2: Seq[Int]) {

    private val sortedList1: Seq[Int] = list1.sorted
    private val sortedList2: Seq[Int] = list2.sorted

    val distances: Seq[Int] = sortedList1.zip(sortedList2).map { case (a, b) => Math.abs(a - b) }

    val similarityScores: Seq[Int] = list1.map {
      firstListNumber =>
        //how many times the number is in the second list
        val multiplyBy = list2.count(_ == firstListNumber)

        firstListNumber * multiplyBy
    }
  }

  object LocationLists {
    def apply(input: String): LocationLists = {
      val lists = input.linesIterator.map(_.split("\\s+").map(_.toInt)).toList
      LocationLists(lists.map(_.head), lists.map(_.tail.head))
    }
  }
}
