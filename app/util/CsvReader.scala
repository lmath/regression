package util

import java.io.{File, FileReader, InputStream}

import com.fasterxml.jackson.databind._
import com.fasterxml.jackson.dataformat.csv.{CsvMapper, CsvParser, CsvSchema}
import play.api.libs.json.JsValue

import scala.io.BufferedSource


object CsvReader {

  def csvFileToJson(filePath: String, hasHeader: Boolean): String = {

    val what = scala.io.Source.fromResource(filePath).reader()

    val inputStream: InputStream = getClass().getResourceAsStream(filePath)

    val inputCsvFile: File = new File(filePath)

    val csvSchema = CsvSchema.builder().setUseHeader(hasHeader).build()
    val csvMapper = new CsvMapper()

    // java.util.Map[String, String] identifies they key values type in JSON
    val readAll = csvMapper
      .readerFor(classOf[java.util.Map[String, String]])
      .`with`(csvSchema)
      .readValues(what)
      .readAll()

    val mapper = new ObjectMapper()

    // json return value
    mapper.writerWithDefaultPrettyPrinter().writeValueAsString(readAll)
  }

  case class HeightWeight(Gender: String, Height:String, Weight:String, Index: String)

  def alsoCsvToJson(filePath: String, hasHeader: Boolean): String = {

    val bufferedSource = scala.io.Source.fromResource(filePath)
    for (line <- bufferedSource.getLines) {
      val cols = line.split(",").map(_.trim)
      // do whatever you want with the columns here
      println(s"${cols(0)}|${cols(1)}|${cols(2)}|${cols(3)}")
    }
    bufferedSource.close

    "stuff"
  }
}


