package weatherApp.components

import japgolly.scalajs.react._
import japgolly.scalajs.react.vdom.html_<^._

object WeatherBoxBtn {
  case class Props(
                    label: String,
                    onClick: Callback
                  )

  val Component = ScalaFnComponent[Props]{ props =>
    <.div(
      <.button(
        ^.cls := "secondary-hover",
        ^.marginLeft := 5.px,
        ^.border := "none",
        ^.cursor := "pointer",
        ^.color := "black",
        ^.padding := "5px 10px",
        ^.borderRadius := "2px",
        ^.backgroundColor := "white",
        ^.border := "1px solid black",
        ^.onClick --> props.onClick,
        props.label
      )
    )
  }

  def apply(props: Props) = Component(props)
}
