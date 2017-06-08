package weatherApp

import org.scalajs.dom
import scala.scalajs.js.{JSApp}
import scalajs.js
import scalajs.js.annotation._
import japgolly.scalajs.react.WebpackRequire
import japgolly.scalajs.react.vdom.html_<^._

import weatherApp.router.{AppRouter}

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
    val target = dom.document.getElementById("target")
    AppRouter.router().renderIntoDOM(target)
  }

}
