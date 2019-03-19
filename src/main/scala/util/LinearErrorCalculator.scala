package main.scala.util

import main.scala.model.SimplePoint

object LinearErrorCalculator {

  def linearMSE(data: List[SimplePoint], theta0: Double, theta1: Double): Double = {
    def h(x: Double) = theta1 * x + theta0
    def errorForAPoint(point: SimplePoint) = (h(point.x) - point.y) * (h(point.x) - point.y)

    val summedErrors = data.foldLeft(0.0)((sum, point) => sum + errorForAPoint(point))

    val numberOfDataPointsDivisor: Double = 0.5 * (1/data.length.toDouble)
    summedErrors * numberOfDataPointsDivisor
  }
}
