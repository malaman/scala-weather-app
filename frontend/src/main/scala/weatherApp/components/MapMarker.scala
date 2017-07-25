package weatherApp.components

import scala.scalajs.js
import japgolly.scalajs.react._
import japgolly.scalajs.react.vdom.html_<^._

import scala.scalajs.js.annotation.JSImport



object MapMarker {
  @JSImport("../../assets/images/marker.png", JSImport.Namespace)
  @js.native
  object MarkerImage extends js.Any

  val Component = ScalaFnComponent[js.Dynamic](props => {
    <.div(
      ^.title := "Map Marker",
      <.img(
        ^.width := 60.px,
        ^.src := MarkerImage.asInstanceOf[String]
      )
    )
  })

  def apply(props: js.Dynamic) = {
    MarkerImage
    Component(props)
  }
}
