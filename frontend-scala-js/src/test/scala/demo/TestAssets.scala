package demo

import scala.scalajs.js
import japgolly.scalajs.react.test.WebpackRequire

object TestAssets {

  def main(): Unit = {
    Main.require()
    WebpackRequire.ReactTestUtils
    ()
  }
}
