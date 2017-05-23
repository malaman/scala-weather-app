package demo

import org.scalajs.dom
import scala.scalajs.js.{JSApp}
import scalajs.js
import scalajs.js.annotation._
import japgolly.scalajs.react.WebpackRequire
import demo.components.{WeatherPage}

object Main extends JSApp {
  @JSImport("../../assets/scss/main.scss", JSImport.Namespace)
  @js.native
  object CSS extends js.Any
  CSS

  def require(): Unit = {
    WebpackRequire.React
    WebpackRequire.ReactDOM
    ()
  }

  override def main(): Unit = {
    require()
    val component = WeatherPage.Component()
    val target = dom.document.getElementById("target")
    component.renderIntoDOM(target)
  }

}
