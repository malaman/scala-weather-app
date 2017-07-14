package weatherApp.components

import japgolly.scalajs.react._
import japgolly.scalajs.react.vdom.html_<^._
import scalajs.js.Date
import weatherApp.models.ForecastWeather

object WeatherForecastBox {
  case class Props(forecast: ForecastWeather)

  val Component = ScalaFnComponent[Props](props => {
    val forecast = props.forecast
    val temp = s"${math rint forecast.main.temp}°"
    val date = new Date(forecast.dt * 1000L).getHours()
    val iconStr = s"wi-owm-${forecast.weather.head.id}"
    <.div(
      ^.display := "flex",
      ^.flexDirection := "column",
      ^.maxWidth := 100.px,
      ^.justifyContent := "center",
      ^.alignItems := "center",
      ^.margin := 5.px,
      <.div(
        s"$date:00"
      ),
      <.div(
        ^.className := s"wi $iconStr",
        ^.fontSize := 25.px,
        ^.fontWeight := "100"
      ),
      <.div(
        ^.marginBottom := 10.px,
        temp
      )
    )
  })
}
