package util

import org.scalatest.FunSuite

class CsvReaderTest extends FunSuite {

  test("testCsvFileToJson") {
    val parsed: String = CsvReader.alsoCsvToJson("height-weight-test.csv", false)

    assert(parsed == "hi")
  }

}
