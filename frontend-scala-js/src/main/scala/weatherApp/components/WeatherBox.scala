package weatherApp.components

import scalajs.js
import scalajs.js.annotation._
import scala.scalajs.js.Dynamic.{global => g}

import japgolly.scalajs.react._
import japgolly.scalajs.react.vdom.html_<^._
import weatherApp.models.{WeatherResponse}

object WeatherBox {
  @JSImport("weather-icons/css/weather-icons.min.css", JSImport.Namespace)
  @js.native
  object CSS extends js.Any
  CSS

  val Component = ScalaComponent.builder[Option[WeatherResponse]]("Table")
    .render($ => {
      if ($.props.isDefined) {
        val props = $.props.get
        val iconStr = s"wi-owm-${props.weather(0).id}"
        <.div(
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
                <.div(props.name),
                <.div(
                  ^.fontSize := 15.px,
                  s"${(math rint props.main.temp * 10) / 10} °C"
                )

              ),
              <.div(
                <.div(
                  ^.className := s"wi ${iconStr}"
                ),
                <.div(
                  props.weather(0).main
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
