package weatherApp.components

import japgolly.scalajs.react._
import japgolly.scalajs.react.vdom.html_<^._

object WeatherBoxBtn {
  case class Props (link: TagMod)
  val Component = ScalaFnComponent[Props](props => {
    <.div(
      props.link(
        <.button(
          ^.cls := "weather-box-btn",
          ^.marginLeft := 5.px,
          ^.border := "none",
          ^.cursor := "pointer",
          ^.color := "white",
          ^.padding := "5px 10px",
          ^.borderRadius := "2px",
          "more"
        )
      )
    )
  })

  def apply(props: Props) = Component(props)
}
