package weatherApp

import org.scalajs.dom
import scala.scalajs.js.{JSApp}
import scalajs.js
import scalajs.js.annotation._
import japgolly.scalajs.react.WebpackRequire
import japgolly.scalajs.react.vdom.html_<^._

import weatherApp.router.{AppRouter}

object Main extends JSApp {
  @JSImport("normalize.css", JSImport.Namespace)
  @js.native
  object Normalize extends js.Any


  @JSImport("../../assets/scss/main.scss", JSImport.Namespace)
  @js.native
  object CSS extends js.Any

  @JSImport("weather-icons/css/weather-icons.min.css", JSImport.Namespace)
  @js.native
  object WeatherIcons extends js.Any

  @JSImport("weather-icons/css/weather-icons-wind.min.css", JSImport.Namespace)
  @js.native
  object WeatherIconsWind extends js.Any

  def require(): Unit = {
    WebpackRequire.React
    WebpackRequire.ReactDOM
    Normalize
    CSS
    WeatherIcons
    WeatherIconsWind
  }

  override def main(): Unit = {
    require()
    val target = dom.document.getElementById("target")
    AppRouter.router().renderIntoDOM(target)
  }
}
