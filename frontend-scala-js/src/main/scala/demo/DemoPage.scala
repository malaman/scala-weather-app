package demo

import scalajs.js
import scalajs.js.annotation._
import scala.scalajs.js.Dynamic.{global => g}

import japgolly.scalajs.react._
import japgolly.scalajs.react.vdom.html_<^._

object DemoPage {

  val Component = ScalaComponent.static("Demo") {

    def icon = <.span(^.cls := "glyphicon glyphicon-tower")
    val map = GoogleMap.Component(GoogleMap.props(137.0537453, -35.8177544, 9))()
    <.div(
      ^.paddingLeft := 1.em,
      <.h3("Enter city to get weather: "),
      <.div(Timer.Component()),
      <.div(WeatherPage.Component())
    )
  }

}
