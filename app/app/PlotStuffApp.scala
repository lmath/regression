package app

object PlotStuffApp {

  import com.cibo.evilplot._
  import com.cibo.evilplot.plot._
  import com.cibo.evilplot.plot.aesthetics.DefaultTheme._
  import com.cibo.evilplot.numeric.Point

  val data = Seq.tabulate(100) { i =>
    Point(i.toDouble, scala.util.Random.nextDouble())
  }

  val labelledPlot = ScatterPlot(data)
    .xAxis()
    .yAxis()
    .frame()
    .xLabel("x")
    .yLabel("y")
    .render()
  displayPlot(labelledPlot)

  def main(args: Array[String]): Unit = {
    println("Hello, world!")
  }
}
