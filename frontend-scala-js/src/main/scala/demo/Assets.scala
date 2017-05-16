package demo

import scalajs.js
import scalajs.js.annotation._

object Assets {
  @JSImport("experiment-webpack/magpie.jpg", JSImport.Namespace)
  @js.native
  private object _magpie extends js.Any
  def magpie = _magpie.asInstanceOf[String]
}
