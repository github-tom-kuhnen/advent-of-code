package y2024.day2

import org.scalatest.flatspec.AnyFlatSpec
import services.FileReaderService
import y2024.day2.Model.{Report, UnusualData}

class ModelTest extends AnyFlatSpec {
  behavior of "UnusualData"
  "apply" should "parse the input data" in {
    val input = FileReaderService.read("y2024/day2.txt")

    val result = UnusualData.apply(input)

    val report1 = Report(List(7, 6, 4, 2, 1))
    val report6 = Report(List(1, 3, 6, 7, 9))

    assert(result.reports.head == report1)
    assert(result.reports.last == report6)
  }

  "areAllLevelsIncreasing" should "return true if all levels are increasing" in {
    assert(Report(List(1, 2, 3, 4, 5)).areAllLevelsIncreasing)
    assert(!Report(List(1, 2, 4, 3, 5)).areAllLevelsIncreasing)
  }

  "areAllLevelsDecreasing" should "return true if all levels are decreasing" in {
    assert(Report(List(5, 4, 3, 2, 1)).areAllLevelsDecreasing)
    assert(!Report(List(5, 4, 3, 1, 2)).areAllLevelsDecreasing)
  }

  "areTwoAdjacentLevelsBetween1and3" should "return true if two adjacent levels are between 1 and 3" in {
    assert(Report(List(1, 2, 3, 4, 5)).areTwoAdjacentLevelsBetween1and3)
    assert(!Report(List(10, 6, 5)).areTwoAdjacentLevelsBetween1and3)
    assert(!Report(List(5, 6, 10)).areTwoAdjacentLevelsBetween1and3)
  }

  "isSafe" should "return true if the report is safe" in {

    val input = FileReaderService.read("y2024/day2.txt")
    val reports = UnusualData.apply(input).reports

    assert(reports.head.isSafe)
    assert(!reports(1).isSafe)
    assert(!reports(2).isSafe)
    assert(!reports(3).isSafe)
    assert(!reports(4).isSafe)
    assert(reports(5).isSafe)
  }

  "safeReports" should "return only safe reports" in {
    val input = FileReaderService.read("y2024/day2.txt")
    val reports = UnusualData.apply(input).reports

    val safeReports = reports.filter(_.isSafe)

    assert(safeReports.size == 2)
    assert(safeReports.head == reports.head)
    assert(safeReports.last == reports.last)
  }

  "isSafeByRemovingOneLevel" should "return true if the report is safe by removing one level" in {
    val input = FileReaderService.read("y2024/day2.txt")
    val reports = UnusualData.apply(input).reports

    assert(Report.isSafeByRemovingOneLevel(reports.head.levels))
    assert(!Report.isSafeByRemovingOneLevel(reports(1).levels))
    assert(!Report.isSafeByRemovingOneLevel(reports(2).levels))
    assert(Report.isSafeByRemovingOneLevel(reports(3).levels))
    assert(Report.isSafeByRemovingOneLevel(reports(4).levels))
    assert(Report.isSafeByRemovingOneLevel(reports(5).levels))
  }
}
