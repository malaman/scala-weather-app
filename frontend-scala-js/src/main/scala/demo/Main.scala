package demo

import org.scalajs.dom
import scala.scalajs.js.JSApp
import japgolly.scalajs.react.WebpackRequire

object Main extends JSApp {

  def require(): Unit = {
    WebpackRequire.React
    WebpackRequire.ReactDOM
    ()
  }

  override def main(): Unit = {
    require()
    val component = DemoPage.Component()
    val target = dom.document.getElementById("target")
    component.renderIntoDOM(target)
  }

}
