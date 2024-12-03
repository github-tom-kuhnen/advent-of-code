package services

import scala.io.Source

object FileReaderService {
  def read(filename: String): String = {
    Source.fromResource(s"$filename").getLines().toList.mkString("\n")
  }
}
