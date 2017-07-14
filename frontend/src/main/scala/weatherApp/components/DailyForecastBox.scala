package weatherApp.components

import japgolly.scalajs.react._
import japgolly.scalajs.react.vdom.html_<^._
import weatherApp.models.DailyForecast

object DailyForecastBox {
  case class Props(forecast: DailyForecast)

  val days: List[String] = List("Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday")

  val Component = ScalaFnComponent[Props](props => {
    val forecast = props.forecast
    val tempMin = s"${math rint forecast.temp_min}°"
    val tempMax = s"${math rint forecast.temp_max}°"
    val iconStr = s"wi-owm-${forecast.weather.id}"
    val day = days(props.forecast.day)
    <.div(
      ^.display := "flex",
      ^.alignItems := "center",
      ^.paddingLeft := 10.px,
      <.div(
        ^.marginRight := 10.px,
        ^.width := 150.px,
        day
      ),
      <.div(
        ^.className := s"wi $iconStr",
        ^.marginTop := "10px",
        ^.marginBottom := "10px",
        ^.fontSize := 25.px,
        ^.fontWeight := "100",
        ^.marginRight := 10.px
      ),
      <.div(
        ^.marginRight := 10.px,
        ^.marginLeft := 10.px,
        ^.width := 25.px,
        tempMax
      ),
      <.div(
        ^.marginRight := 10.px,
        ^.width := 15.px,
        tempMin
      )
    )
  })

  def apply(props: Props) = Component(props)
}
