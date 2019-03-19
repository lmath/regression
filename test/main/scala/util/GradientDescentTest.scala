package main.scala.util

import main.scala.model.{House, SimplePoint}
import org.specs2.mutable.Specification
import main.scala.util.GradientDescent.LearnedParameterSet

class GradientDescentTest extends Specification{

  "theta0Iter" should {
    "probably not be zero" in {

      val expected = List(
        HeightWeight("Male",174.0,96.0),
        HeightWeight("Male",189.0,87.0)
      )

      val data = expected.map(dataPoint => SimplePoint(dataPoint.height, dataPoint.weight))
      val gmm: Double = GradientDescent.theta0Iter(data, 1, 1, 1)

      gmm shouldEqual (-90)
    }
  }

  "theta1iter" should {
    "probably not be zero" in {

      val expected = List(
      HeightWeight("Female",185.0,110.0),
      HeightWeight("Female",195.0,104.0)
      )

      val data = expected.map(dataPoint => SimplePoint(dataPoint.height, dataPoint.weight))
      val gmm: Double = GradientDescent.theta1Iter(data, 1, 1, 1)

      gmm shouldEqual (-15999)
    }
  }

  "gradientDescent" should {
//    "get closer to the right answer" in {
//
//      def transformer(strings: Array[String]) = House(strings(0), strings(4).toDouble, strings(80).toDouble)
//      val housePrices = CsvReader.asCaseClassList("house-prices-training-resources.data-test.csv", true, transformer)
//
//      val resources.data = housePrices.map(dataPoint => SimplePoint(dataPoint.lotArea, dataPoint.salePrice))
//      val gmm = GradientDescent.gradientDescent(resources.data, 0, 1, 2, 1000)
//
//      gmm shouldEqual (LearnedParameterSet(5, 5))
//    }

    "get closer to the right answer" in {

      def transformer(strings: Array[String]) = HeightWeight(strings(0), strings(1).toDouble, strings(2).toDouble)
      val heightWeights = CsvReader.asCaseClassList("height-weight-test.csv", true, transformer)

      val data = heightWeights.map(dataPoint => SimplePoint(dataPoint.height, dataPoint.weight))
      val gmm = GradientDescent.gradientDescent(data, 0, 1, 0.001, 1000)

      gmm shouldEqual (LearnedParameterSet(5, 5, Nil))
    }

//    "pass the coursera test case" in {
//
//      //[1 5; 1 2; 1 4; 1 5],[1 6 4 2]',[0 0]',0.01,1000
//
//      def transformer(strings: Array[String]) = House(strings(0), strings(4).toDouble, strings(80).toDouble)
//      val housePrices: Seq[House] = CsvReader.asCaseClassList("house-prices-training-resources.data-test.csv", true, transformer)
//
//      val resources.data = List(
//        SimplePoint(1, 5),
//        SimplePoint(1, 2),
//        SimplePoint(1, 4),
//        SimplePoint(1, 5),
//      )
////      val resources.data: Seq[SimplePoint] = housePrices.map(dataPoint => SimplePoint(dataPoint.lotArea, dataPoint.salePrice))
//      val gmm = GradientDescent.gradientDescent(resources.data, 0, 1, 2, 1000)
//
//      gmm shouldEqual (LearnedParameterSet(5, 5))
//    }
  }
}
