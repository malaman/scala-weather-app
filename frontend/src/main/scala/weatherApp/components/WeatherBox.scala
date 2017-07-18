package weatherApp.components

import japgolly.scalajs.react._
import japgolly.scalajs.react.vdom.html_<^._
import japgolly.scalajs.react.extra.router.RouterCtl

import weatherApp.models.WeatherResponse
import weatherApp.router.AppRouter

object WeatherBox {
  
  case class Props (
    weather: Option[WeatherResponse],
    ctl: RouterCtl[AppRouter.Page]
  )
  val Component = ScalaFnComponent[Props](props => {
    if (props.weather.isDefined) {
      val weather = props.weather.get
      val iconStr = s"wi-owm-${weather.weather.head.id}"
      props.ctl.link(AppRouter.CityRoute(weather.name.toLowerCase(), weather.id))(
        ^.cls := "weather-box",
        ^.maxWidth := "400px",
        ^.display := "flex",
        ^.border := "1px solid",
        ^.color := "black",
        <.div(
          ^.margin := "5px",
          ^.display := "flex",
          ^.flexDirection := "row",
          ^.justifyContent := "space-between",
          ^.width := "100%",
          ^.fontSize := 15.px,
          <.div(
            <.div(weather.name),
            <.div(
              s"${(math rint weather.main.temp * 10) / 10} °C"
            )
          ),
          <.div(
            ^.display := "flex",
            ^.justifyContent := "center",
            ^.alignItems := "center",
            <.div(
              ^.className := s"wi $iconStr",
              ^.marginRight := "10px",
              ^.fontSize := 25.px,
              ^.fontWeight := "100"
            ),
            <.div(
              weather.weather.head.main
            )
          ),
          <.div(
            ^.display := "flex",
            ^.flexDirection := "row",
            ^.justifyContent := "center",
            <.div(
              ^.display := "flex",
              ^.justifyContent := "center",
              ^.alignItems := "center",
              WeatherBoxBtn.Component()
            )
          )
        )
      )
    } else {
      <.div()
    }
  })
  def apply(props: Props) = Component(props)
}
