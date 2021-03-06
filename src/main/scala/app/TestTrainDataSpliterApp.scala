package main.scala.app

import com.cibo.evilplot.displayPlot
import main.scala.model.{HeightWeight, SimplePoint}
import main.scala.util.GradientDescent.LearnedParameterSet
import main.scala.util._
import com.cibo.evilplot.plot.aesthetics.DefaultTheme._


object TestTrainDataSpliterApp {

  def transformer(strings: Array[String]) = HeightWeight(strings(0), strings(1).toDouble, strings(2).toDouble)
  val heightWeights = CsvReader.asCaseClassList("/nlys-1999.csv", true, transformer)

  val heightWeightsShuffled = scala.util.Random.shuffle(heightWeights)

  val splitPoint = (heightWeightsShuffled.size * 0.2).toInt

  val (testSet, trainSet) = heightWeightsShuffled.splitAt(splitPoint)

  def toStringsTransformer(data: HeightWeight) = Array(data.gender, data.height.toString, data.weight.toString)
  CSVWriter.writeCaseClassListToCsv("nlys-test.csv", toStringsTransformer, testSet)
  CSVWriter.writeCaseClassListToCsv("nlys-train.csv", toStringsTransformer, trainSet)

  def main(args: Array[String]): Unit = {

    println("Hello, world!")
  }

}
