package main.scala.app

import com.cibo.evilplot._
import com.cibo.evilplot.colors.HTMLNamedColors
import com.cibo.evilplot.numeric.{Bounds, Point}
import com.cibo.evilplot.plot._
import com.cibo.evilplot.plot.aesthetics.DefaultTheme._
import main.scala.model.{HeightWeight, House, SimplePoint}
import main.scala.util.GradientDescent.LearnedParameterSet
import main.scala.util.{CsvReader, FeatureScaler, GradientDescent, Plotter}

object GradientDescentApp {
  def transformer(strings: Array[String]) = HeightWeight(strings(0), strings(1).toDouble, strings(2).toDouble)
  val heightWeights = CsvReader.asCaseClassList("/nlys-1999.csv", true, transformer)

  val data = heightWeights.map(dataPoint => SimplePoint(dataPoint.height, dataPoint.weight))
  val gmm: LearnedParameterSet = GradientDescent.gradientDescent(data, 0, 1, 0.001, 1000)
  def main(args: Array[String]): Unit = {

    val heightWeightScaled = FeatureScaler.scaledDividedByMax(data)
    displayPlot(Plotter.costItersPlot(gmm.history))
    displayPlot(Plotter.heightWeightPlot(heightWeightScaled.map(p => HeightWeight("", p.x, p.y)), gmm.theta0, gmm.theta1))

    println("Hello, world!")
  }
}
