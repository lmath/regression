package util
import org.specs2.mutable.Specification

class CsvReaderTest extends Specification {

  "csvFileToJson" should {
    "read a csv file" in {
      val whatami = CsvReader.csvFileToJson("/height-weight-test.csv", true)
      whatami shouldEqual("abc")
    }
  }

}
