package util

import model.SimplePoint

object GradientDescent {
  def diffPoint(point: SimplePoint, theta0: Double, theta1: Double) = (theta1 * point.x + theta0) - point.y

  def theta0Iter(data: List[SimplePoint], theta0: Double, theta1: Double, learningRate: Double): Double = {
    def stepPoint(point: SimplePoint) = diffPoint(point, theta0, theta1)
    val numberOfDataPointsDivisor: Double = 1/data.length.toDouble
    val adjustment = data.foldLeft(0.0)((sum, point) => sum + stepPoint(point)) * numberOfDataPointsDivisor

    theta0 - (adjustment * learningRate)
  }

  def theta1Iter(data: List[SimplePoint], theta0: Double, theta1: Double, learningRate: Double): Double = {
    def stepPoint(point: SimplePoint) = diffPoint(point, theta0, theta1) * point.x
    val numberOfDataPointsDivisor: Double = 1/data.length.toDouble
    val adjustment = data.foldLeft(0.0)((sum, point) => sum + stepPoint(point)) * numberOfDataPointsDivisor

    theta1 - (adjustment * learningRate)
  }

  case class LearnedParameterSet(theta0: Double, theta1: Double)

  def gradientDescent(
    data: List[SimplePoint],
    startingTheta0: Double,
    startingTheta1: Double,
    learningRate: Double,
    iters: Int): LearnedParameterSet = {

    var theta0 = startingTheta0
    var theta1 = startingTheta1
    var cost = LinearErrorCalculator.linearMSE(data, theta0, theta1)
    println(s"starting cost ${cost}")

    var counter = iters
    while(counter > 0 && cost > 0.001) {

      println(s"old theta 0 ${theta0}")
      println(s"old theta 1 ${theta1}")

      println(s"theta 0 iter ${theta0Iter(data, theta0, theta1, learningRate)}")
      println(s"theta 1 iter ${theta1Iter(data, theta0, theta1, learningRate)}")

      val tempTheta0 = theta0Iter(data, theta0, theta1, learningRate)
      val tempTheta1 = theta1Iter(data, theta0, theta1, learningRate)

      theta0 = tempTheta0
      theta1 = tempTheta1

      cost = LinearErrorCalculator.linearMSE(data, theta0, theta1)
      println(s"new theta 0 ${theta0}")
      println(s"new theta 1 ${theta1}")
      println(s"new cost ${cost}")
      println(s"we are on iter ${counter}")

      counter = counter - 1
    }

    LearnedParameterSet(theta0, theta1)

  }
}
