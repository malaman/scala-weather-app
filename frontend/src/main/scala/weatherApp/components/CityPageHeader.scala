  package weatherApp.components

import japgolly.scalajs.react._
import japgolly.scalajs.react.vdom.html_<^._
import weatherApp.models.WeatherResponse

object CityPageHeader {
  case class Props(city: String, weatherOption: Option[WeatherResponse])

  val Component = ScalaFnComponent[Props](props => {
    if (props.weatherOption.isDefined) {
      val weather = props.weatherOption.get
      val iconStr = s"wi-owm-${weather.weather.head.id}"
      <.div(
        ^.margin := "5px",
        ^.display := "flex",
        ^.flexDirection := "row",
        ^.justifyContent := "space-between",
        ^.alignItems := "center",
        ^.maxWidth := 400.px,
        ^.fontSize := 15.px,
        ^.marginBottom := 15.px,
        <.div(
          ^.fontWeight := "bold",
          props.city
        ),
        <.div(
          <.div(
            s"${(math rint weather.main.temp * 10) / 10} °C"
          )
        ),
        <.div(
          ^.display := "flex",
          ^.justifyContent := "center",
          ^.alignItems := "center",
          ^.flexWrap := "wrap",
          <.div(
            ^.className := s"wi $iconStr",
            ^.marginRight := "10px",
            ^.fontSize := 25.px,
            ^.fontWeight := "100"
          ),
          <.div(
            weather.weather.head.main
          ),
          <.div(
            ^.marginLeft := 15.px,
            WindInformation(WindInformation.Props(weather))
          )

        )
      )
    } else {
      <.div(
        ^.fontWeight := "bold",
        ^.marginBottom := 15.px,
        props.city
      )
    }
  })

  def apply(props: Props) = Component(props)
}
