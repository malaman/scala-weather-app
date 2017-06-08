package weatherApp.config

import scala.scalajs.js
import js.JSConverters._
import scalajs.js.annotation._

object Config {
  @js.native
  @JSGlobal("appConfig")
  object AppConfig extends js.Object {
    val apiHost: String = js.native
  }
}
