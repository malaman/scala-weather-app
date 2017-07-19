package weatherApp.components

import japgolly.scalajs.react._
import japgolly.scalajs.react.vdom.html_<^._
import weatherApp.models.WeatherResponse

object WindInformation {
  case class Props(weather: WeatherResponse)

  val Component = ScalaFnComponent[Props](props => {
    val deg = props.weather.wind.deg.getOrElse(0)
    val speed = props.weather.wind.speed
    <.div(
      <.div(
        ^.display := "flex",
        ^.justifyContent := "center",
        ^.alignItems := "center",
        <.div(
          ^.className := s"wi wi-wind towards-$deg-deg",
          ^.marginRight := "10px",
          ^.fontSize := 25.px,
          ^.fontWeight := "100"
        ),
        <.div(
          s"${(math rint speed* 10) / 10} km/h"
        )
      )
    )
  })

  def apply(props: Props) = Component(props)
}
