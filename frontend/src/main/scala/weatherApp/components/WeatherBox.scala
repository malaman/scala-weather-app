package weatherApp.components

import scalajs.js
import scalajs.js.annotation._
import scala.scalajs.js.Dynamic.{global => g}

import japgolly.scalajs.react._
import japgolly.scalajs.react.vdom.html_<^._
import japgolly.scalajs.react.extra.router.{RouterCtl}

import weatherApp.models.{WeatherResponse}
import weatherApp.router.{AppRouter}

object WeatherBox {
  @JSImport("weather-icons/css/weather-icons.min.css", JSImport.Namespace)
  @js.native
  object CSS extends js.Any
  CSS

  case class Props (
    weather: Option[WeatherResponse],
    ctl: RouterCtl[AppRouter.Page]
  )

  val Component = ScalaComponent.builder[Props]("Table")
    .render($ => {
      if ($.props.weather.isDefined) {
        val weather = $.props.weather.get
        val iconStr = s"wi-owm-${weather.weather(0).id}"
        $.props.ctl.link(AppRouter.CityRoute(weather.name.toLowerCase(), weather.id))(
          ^.cls := "weather-box",
          ^.width := "25%",
          ^.display := "flex",
          ^.border := "1px solid",
          <.div(
            ^.margin := "5px",
            ^.display := "flex",
            ^.flexDirection := "row",
            ^.justifyContent := "space-between",
            ^.width := "100%",
            <.div(
              <.div(weather.name),
              <.div(
                ^.fontSize := 15.px,
                s"${(math rint weather.main.temp * 10) / 10} °C"
              )
            ),
            <.div(
              ^.display := "flex",
              ^.flexDirection := "row",
              ^.justifyContent := "center",
              <.div(
                <.div(
                  ^.className := s"wi ${iconStr}"
                ),
                <.div(
                  weather.weather(0).main
                )
              ),
              <.div(
                ^.display := "flex",
                ^.justifyContent := "center",
                ^.alignItems := "center",
                <.button(
                  ^.height := 20.px,
                  ^.marginLeft := 5.px,
                  "more"
                )
              )
            )
          )
        )
      } else {
        <.div()
      }
    })
    .build
}
