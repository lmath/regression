package main.scala.util

import java.io.{File, FileReader, InputStream}

import scala.io.Source

object CsvReader {


  def testResourceFile(fileName: String): File = {
    val classLoader = getClass().getClassLoader()
    new File(classLoader.getResource(fileName).getFile())
  }

  def asCaseClassList[T](csvFile: String, hasHeader: Boolean, transformer: Array[String] => T): List[T] = {
    import scala.collection.mutable.ListBuffer


    val file = testResourceFile(csvFile)//getClass.getResource(s"/$csvFile").getPath
//    val file = new File(path)

//    val fileStream: InputStream = getClass.getResourceAsStream(s"$csvFile")
//    val lines: Iterator[String] = Source.fromInputStream(fileStream).getLines

    var horribleMutableList = new ListBuffer[T]()
    val bufferedSource = scala.io.Source.fromFile(file)
    val iterator = if(hasHeader) bufferedSource.getLines().drop(1) else bufferedSource.getLines()
//    val iterator = if(hasHeader) lines.drop(1) else lines

    for (line <- iterator) {
      val cols: Array[String] = line.split(",").map(_.trim)
      val newItem = transformer(cols)
      horribleMutableList += newItem
    }
    bufferedSource.close

    horribleMutableList.toList
  }
}


