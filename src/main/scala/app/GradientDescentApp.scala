package main.scala.app

import com.cibo.evilplot._
import com.cibo.evilplot.plot.aesthetics.DefaultTheme._
import main.scala.model.{HeightWeight, SimplePoint}
import main.scala.util.GradientDescent.LearnedParameterSet
import main.scala.util._

object GradientDescentApp {
  def arrayToHeightWeight(strings: Array[String]) = HeightWeight(strings(0), strings(1).toDouble, strings(2).toDouble)
  def heightWeightTo2dPoint(data: List[HeightWeight]) = data.map(dataPoint => SimplePoint(dataPoint.height, dataPoint.weight))

  val heightWeights = CsvReader.asCaseClassList("/nlys-train.csv", true, arrayToHeightWeight)
  val data = heightWeightTo2dPoint(heightWeights)

  val learnedParameters: LearnedParameterSet = GradientDescent.gradientDescent(data, 0, 1, 0.1, 500)

  def main(args: Array[String]): Unit = {

    val heightWeightsTestData = CsvReader.asCaseClassList("/nlys-test.csv", true, arrayToHeightWeight)
    val heightWeightsTestDataPoints = heightWeightTo2dPoint(heightWeightsTestData)

    val heightWeightScaled = FeatureScaler.scaledFeatures(data)
    val heightWeightTestDataScaled = FeatureScaler.scaledFeatures(heightWeightsTestDataPoints)

    displayPlot(Plotter.costItersPlot(learnedParameters.history, None))
    displayPlot(Plotter.heightWeightPlot(heightWeightScaled.map(p => HeightWeight("", p.x, p.y)), learnedParameters.theta0, learnedParameters.theta1, Some("Height vs weight TRAINING data and line fit via linear regression")))
    displayPlot(Plotter.heightWeightPlot(heightWeightTestDataScaled.map(p => HeightWeight("", p.x, p.y)), learnedParameters.theta0, learnedParameters.theta1, Some("Height vs weight TEST data and line fit via linear regression")))


    val meanAbsoluteError = LinearErrorCalculator.linearMeanAbsoluteError(heightWeightScaled, learnedParameters.theta0, learnedParameters.theta1)
    println(s"Hello, world! This is the absolute error: $meanAbsoluteError")
  }

}
