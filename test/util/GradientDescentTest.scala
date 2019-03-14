package util

import model.SimplePoint
import org.specs2.mutable.Specification
import util.GradientDescent.LearnedParameterSet

class GradientDescentTest extends Specification{

  "theta0Iter" should {
    "probably not be zero" in {

      val expected = List(
        HeightWeight("Male",174.0,96.0),
        HeightWeight("Male",189.0,87.0),
        //        HeightWeight("Female",185.0,110.0),
        //        HeightWeight("Female",195.0,104.0),
        //        HeightWeight("Male",149.0,61.0),
        //        HeightWeight("Male",189.0,104.0),
        //        HeightWeight("Male",147.0,92.0),
        //        HeightWeight("Male",154.0,111.0),
        //        HeightWeight("Male",174.0,90.0),
        //        HeightWeight("Female",169.0,103.0)
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
      HeightWeight("Female",195.0,104.0),
        //        HeightWeight("Male",149.0,61.0),
        //        HeightWeight("Male",189.0,104.0),
        //        HeightWeight("Male",147.0,92.0),
        //        HeightWeight("Male",154.0,111.0),
        //        HeightWeight("Male",174.0,90.0),
        //        HeightWeight("Female",169.0,103.0)
      )

      val data = expected.map(dataPoint => SimplePoint(dataPoint.height, dataPoint.weight))
      val gmm: Double = GradientDescent.theta1Iter(data, 1, 1, 1)

      gmm shouldEqual (-15999)
    }
  }

  "gradientDescent" should {
    "get closer to the right answer" in {

//      val expected = List(
//        HeightWeight("Female",185.0,110.0),
//        HeightWeight("Female",195.0,104.0),
//        HeightWeight("Male",149.0,61.0),
//        HeightWeight("Male",189.0,104.0),
//        HeightWeight("Male",147.0,92.0),
//        HeightWeight("Male",154.0,111.0),
//        HeightWeight("Male",174.0,90.0),
//        HeightWeight("Female",169.0,103.0)
//      )


      val expected = List(
        HeightWeight("Female",0,1),
        HeightWeight("Female",1,4),
        HeightWeight("Male",2,7),
        HeightWeight("Male",3,10),
      )


      val data = expected.map(dataPoint => SimplePoint(dataPoint.height, dataPoint.weight))
      val gmm = GradientDescent.gradientDescent(data, 1, 1, 0.01, 1000)

      gmm shouldEqual (LearnedParameterSet(5, 5))
    }
  }
}
