package weatherApp.components

import scala.scalajs.js
import japgolly.scalajs.react._
import japgolly.scalajs.react.vdom.html_<^._

object MapMarker {

  val Component = ScalaFnComponent[js.Dynamic](props => {
    <.div(
      ^.title := "Map Marker",
      <.img(
        ^.width := 30.px,
        ^.height := 20.px,
        ^.src := "/images/marker.png"
      )
    )
  })


  def apply(props: js.Dynamic) = Component(props)
}
