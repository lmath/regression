package util

import java.io.{File, FileReader, InputStream}

object CsvReader {


  def testResourceFile(fileName: String): File = {
    val classLoader = getClass().getClassLoader()
    new File(classLoader.getResource(fileName).getFile())
  }

  def asCaseClassList[T](csvFile: String, hasHeader: Boolean, transformer: Array[String] => T): List[T] = {
    import scala.collection.mutable.ListBuffer

    var horribleMutableList = new ListBuffer[T]()
    val bufferedSource = scala.io.Source.fromResource(csvFile)

    val iterator = if(hasHeader) bufferedSource.getLines().drop(1) else bufferedSource.getLines()

    for (line <- iterator) {
      val cols: Array[String] = line.split(",").map(_.trim)
      val newItem = transformer(cols)
      horribleMutableList += newItem
    }
    bufferedSource.close

    horribleMutableList.toList
  }
}


