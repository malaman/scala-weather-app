package weatherApp.components

import scalajs.js
import scalajs.js.annotation._
import scala.scalajs.js.Dynamic.{global => g}

import japgolly.scalajs.react._
import japgolly.scalajs.react.vdom.html_<^._
import weatherApp.models.{WeatherResponse}


object WeatherBox {
  val Component = ScalaComponent.builder[Option[WeatherResponse]]("Table")
    .render($ => {
      if ($.props.isDefined) {
        val props = $.props.get
        <.div(
            ^.width := "25%",
            ^.display := "flex",
            ^.border := "1px solid",
            "hi: ", props.name
        )
      } else {
        <.div()
      }
    })
    .build
}
