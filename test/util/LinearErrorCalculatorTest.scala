package util

import org.specs2.mutable.Specification

class LinearErrorCalculatorTest extends Specification {

  "linearMSE" should {
    "probably not be zero" in {

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

      val asTupleList = expected.map(dataPoint => (dataPoint.height, dataPoint.weight))
      val gmm: Double = LinearErrorCalculator.linearMSE(asTupleList, 1, 1)

      gmm shouldNotEqual(0)
    }

  }
}
