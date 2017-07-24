package weatherApp.components

import scalajs.js
import scalajs.js.annotation._
import japgolly.scalajs.react._
import japgolly.scalajs.react._
import japgolly.scalajs.react.vdom.html_<^._

object GoogleMaps {

  @JSImport("google-map-react", JSImport.Default)
  @js.native
  object JsComp extends js.Any

  @ScalaJSDefined
  trait Props extends js.Object {
    val bootstrapURLKeys: js.Dynamic
    val center: js.Array[Double]
    val zoom: Int
  }

  val Component = JsFnComponent[Props, Children.Varargs](JsComp)

  def props(lat: Double, lng: Double, zoom: Int): Props = {
    val z = zoom
    val k = "AIzaSyBPfBs8b2Z"
    new Props {
      val bootstrapURLKeys  = js.Dynamic.literal(key=s"${k}jQWpR5TlbDWhYwp6FItvK10s")
      val center = js.Array(lat, lng)
      val zoom = z
    }
  }

  def apply(lat: Double, lng: Double, zoom: Int, children: CtorType.ChildArg) = Component(props(lat, lng, zoom))(children)
}
