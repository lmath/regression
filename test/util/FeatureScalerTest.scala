package util

import model.SimplePoint
import org.specs2.mutable.Specification

class FeatureScalerTest extends Specification {

  "FeatureScalerTest" should {
    "scaledFeatures" in {
      val inputData = List(
        SimplePoint(78, 90),
        SimplePoint(55, 50),
        SimplePoint(54, 56),
        SimplePoint(77, 85),
        SimplePoint(88, 90)
      )

      val expected = List(
        SimplePoint(0.22352941176470573,0.3949999999999999),
        SimplePoint(-0.4529411764705884,-0.6050000000000001),
        SimplePoint(-0.48235294117647076,-0.45500000000000007),
        SimplePoint(0.19411764705882337,0.2699999999999999),
        SimplePoint(0.5176470588235292,0.3949999999999999)
      )

      val scaled = FeatureScaler.scaledFeatures(inputData)
      scaled shouldEqual(expected)
    }

  }
}
