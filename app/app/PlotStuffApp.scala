package app

import com.cibo.evilplot._
import com.cibo.evilplot.colors.HTMLNamedColors
import com.cibo.evilplot.plot._
import com.cibo.evilplot.plot.aesthetics.DefaultTheme._
import com.cibo.evilplot.numeric.Point
import com.cibo.evilplot.plot.renderers.PointRenderer
import model.House
import util.CsvReader

import com.cibo.evilplot.colors.HTMLNamedColors._
import com.cibo.evilplot.numeric.Bounds
import com.cibo.evilplot.plot._


object PlotStuffApp {

  def heightWeightPlot() = {
    case class HeightWeight(gender: String, height: Double, weight: Double)

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

    val functionPlot = Overlay(
      FunctionPlot.series(x => 3.5 * x - 50, "y = 5x - 40",
        HTMLNamedColors.green, xbounds = Some(Bounds(0, 100))),
      ScatterPlot(
        points
      )
    ).title("A bunch of polynomials.")
      .overlayLegend()
      .standard()
      .render()

    displayPlot(functionPlot)
  }

    def transformer(strings: Array[String]) = House(strings(0), strings(4).toDouble, strings(80).toDouble)
    val housePrices = CsvReader.asCaseClassList("data/house-prices-training-data.csv", true, transformer)
    val points = housePrices.map(d => Point(d.salePrice, d.lotArea))

    val labelledPlot = ScatterPlot(
      points
    )
      .xAxis()
      .yAxis()
      .frame()
      .xLabel("sale price")
      .yLabel("lot area")
      .render()
    displayPlot(labelledPlot)

  val functionPlot = Overlay(
    FunctionPlot.series(x => 0.05 * x + 1, "y = 20x + 1",
      HTMLNamedColors.green, xbounds = Some(Bounds(0, 800000))),
    ScatterPlot(
      points
    )
  ).title("A bunch of polynomials.")
    .overlayLegend()
    .standard()
    .render()

  displayPlot(functionPlot)



  def main(args: Array[String]): Unit = {
    println("Hello, world!")
  }
}
