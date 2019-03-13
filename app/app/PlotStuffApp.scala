package app

import com.cibo.evilplot._
import com.cibo.evilplot.plot._
import com.cibo.evilplot.plot.aesthetics.DefaultTheme._
import com.cibo.evilplot.numeric.Point
import com.cibo.evilplot.plot.renderers.PointRenderer
import util.CsvReader

object PlotStuffApp {

  case class HeightWeight(gender: String, height: Double, weight: Double)

  val heightWeightDataShort = List(
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

  def transformer(strings: Array[String]) = HeightWeight(strings(0), strings(2).toDouble, strings(3).toDouble)

  val heightWeightData = CsvReader.asCaseClassList("data/nlys-1999.csv", true, transformer)


  val points = heightWeightData.map(d => Point(d.height, d.weight))

  val labelledPlot = ScatterPlot(
    points
  )
    .xAxis()
    .yAxis()
    .frame()
    .xLabel("height")
    .yLabel("weight")
    .render()
  displayPlot(labelledPlot)

  def main(args: Array[String]): Unit = {
    println("Hello, world!")
  }
}
