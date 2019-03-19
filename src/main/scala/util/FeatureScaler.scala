package main.scala.util

import main.scala.model.SimplePoint

import scala.annotation.tailrec

object FeatureScaler {

  def scaledDividedByMax(data: List[SimplePoint]): List[SimplePoint] = {
    val xList = data.map(_.x)
    val yList = data.map(_.y)

    val maxX = xList.max
    val maxY = yList.max

    data.map { point =>
      SimplePoint(
        point.x / maxX,
        point.y / maxY
      )
    }
  }

  def scaledFeatures(data: List[SimplePoint]): List[SimplePoint] = {

    def sum(xs: List[Double]): Double = {
      @tailrec
      def inner(xs: List[Double], accum: Double): Double = {
        xs match {
          case x :: tail => inner(tail, accum + x)
          case Nil => accum
        }
      }

      inner(xs, 0)
    }

    def avg(nums: List[Double]): Double = sum(nums) / nums.length.toDouble

    def scaled(min: Double, max: Double, avg: Double, dataPoint: Double): Double = {
      val range = max - min
      (dataPoint - avg) / range
    }

    val xList = data.map(_.x)
    val yList = data.map(_.y)

    val minX = xList.min
    val minY = yList.min

    val maxX = xList.max
    val maxY = yList.max

    val xAvg = avg(xList)
    println(xAvg)
    val yAvg = avg(yList)
    println(yAvg)

    data.map { point =>
      SimplePoint(
        scaled(minX, maxX, xAvg, point.x),
        scaled(minY, maxY, yAvg, point.y),
      )
    }
  }
}
