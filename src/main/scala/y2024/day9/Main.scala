package y2024.day9

import services.FileReaderService
import y2024.day9.Model.DiskMap

object Main extends App {

  val input = FileReaderService.read("y2024/day9.txt")

  val diskMap = DiskMap.apply(input)
  val orderedDiskUnits = DiskMap.reorderDiskUnits(diskMap.diskUnits)
  val checksum = DiskMap.checksum(orderedDiskUnits)
  println(checksum)
}