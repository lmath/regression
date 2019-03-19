package main.scala.util

import com.cibo.evilplot.colors.HTMLNamedColors
import com.cibo.evilplot.geometry.Drawable
import com.cibo.evilplot.numeric.Point
import com.cibo.evilplot.plot.aesthetics.DefaultTheme._
import com.cibo.evilplot.plot.{FunctionPlot, Overlay, ScatterPlot, _}
import main.scala.model.{GradientDescentHistoryPoint, HeightWeight, SimplePoint}

object Plotter {


  def scatterPlot(data: List[SimplePoint], xLabel: String, yLabel: String) = {
    val points = data.map(d => Point(d.x, d.y))

    ScatterPlot(
      points
    )
      .xAxis()
      .yAxis()
      .frame()
      .xLabel(xLabel)
      .yLabel(yLabel)
      .render()
  }

  def costItersPlot(data: List[GradientDescentHistoryPoint]) = {
    val points = data.map(d => Point(d.iteration, d.cost))

    ScatterPlot(
      points
    )
      .xAxis()
      .yAxis()
      .frame()
      .xLabel("iteration number")
      .yLabel("cost")
      .render()

  }

  def heightWeightPlot(data: List[HeightWeight], theta0: Double, theta1: Double): Drawable = {

    val points = data.map(d => Point(d.height, d.weight))

    Overlay(
      FunctionPlot.series(x => theta1 * x + theta0, s"y = $theta1 + $theta0",
        HTMLNamedColors.green),
      ScatterPlot(
        points
      )
    ).title("Height vs weight data and line fit via linear regression")
      .overlayLegend()
      .standard()
      .render()
  }

}
