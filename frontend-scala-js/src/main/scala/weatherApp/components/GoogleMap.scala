package weatherApp.components

import scalajs.js
import scalajs.js.annotation._
import japgolly.scalajs.react._

object GoogleMap {

  @JSImport("google-map-react", JSImport.Default)
  @js.native
  object JsComp extends js.Any

  @ScalaJSDefined
  trait BootstrapURLKeys extends js.Object {
    val key: String
  }

  @ScalaJSDefined
  trait Props extends js.Object {
    val bootstrapURLKeys: BootstrapURLKeys
    val center: js.Array[Double]
    val zoom: Int
  }

  val Component = JsFnComponent[Props, Children.Varargs](JsComp
    .asInstanceOf[js.Dynamic]) // TODO ← Remove after 1.0.0-RC2 is released

  def props(lat: Double, lng: Double, zoom: Int): Props = {
    val z = zoom
    val hardcodedCommittedToGitApiKeyOMG: BootstrapURLKeys = {
      val k = "Et-TLCS2oJ24"
      new BootstrapURLKeys {
        override val key = "AIzaSyDmGPSO7W4wNzXhRI2VNir" + k
      }
    }
    new Props {
      override val bootstrapURLKeys = hardcodedCommittedToGitApiKeyOMG
      override val center = js.Array(lng, lat)
      override val zoom = z
    }
  }
}
