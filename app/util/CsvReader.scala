package util

import java.io.{File, FileReader, InputStream}

import com.fasterxml.jackson.databind._
import com.fasterxml.jackson.dataformat.csv.{CsvMapper, CsvParser, CsvSchema}
import play.api.libs.json.JsValue


object CsvReader {

  def csvFileToJson(filePath: String, hasHeader: Boolean): String = {

    val inputStream = getClass().getResourceAsStream(filePath);

    val inputCsvFile: File = new File(filePath)

    val csvSchema = CsvSchema.builder().setUseHeader(hasHeader).build()
    val csvMapper = new CsvMapper()

    // java.util.Map[String, String] identifies they key values type in JSON
    val readAll = csvMapper
      .readerFor(classOf[java.util.Map[String, String]])
      .`with`(csvSchema)
      .readValues(inputStream)
      .readAll()

    val mapper = new ObjectMapper()

    // json return value
    mapper.writerWithDefaultPrettyPrinter().writeValueAsString(readAll)
  }

  def alsoCsvToJson(filePath: String, hasHeader: Boolean): String = {
    val input = new File(filePath)

    val csvSchema = CsvSchema.builder().setUseHeader(true).build();
    val csvMapper = new CsvMapper();

    // Read data from CSV file
    val readAll = csvMapper.readerFor(classOf[java.util.Map[String,String]]).`with`(csvSchema).readValues(input).readAll()

    val mapper = new ObjectMapper();

    val outputString = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(readAll);

    outputString
  }
}


