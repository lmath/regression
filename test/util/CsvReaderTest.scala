package util
import org.specs2.mutable.Specification

class CsvReaderTest extends Specification {

  "csvFileToJson" should {
    "read a csv file" in {
      def transformer(strings: Array[String]) = HeightWeight(strings(0), strings(1).toDouble, strings(2).toDouble)

      val expected = List(
        HeightWeight("Male",174.0,96.0),
        HeightWeight("Male",189.0,87.0),
        HeightWeight("Female",185.0,110.0),
        HeightWeight("Female",195.0,104.0),
        HeightWeight("Male",149.0,61.0),
        HeightWeight("Male",189.0,104.0),
        HeightWeight("Male",147.0,92.0),
        HeightWeight("Male",154.0,111.0),
        HeightWeight("Male",174.0,90.0),
        HeightWeight("Female",169.0,103.0)
      )

      val heightsAndWeights = CsvReader.asCaseClassList("height-weight-test.csv", true, transformer)
      heightsAndWeights shouldEqual(expected)
    }
  }

}


case class HeightWeight(gender: String, height: Double, weight: Double)
