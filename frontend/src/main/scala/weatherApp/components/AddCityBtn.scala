package weatherApp.components

import japgolly.scalajs.react._
import japgolly.scalajs.react.vdom.html_<^._
import org.scalajs.dom
import io.circe.syntax._
import io.circe.generic.auto._

import weatherApp.diode.AppCircuit.dispatch
import weatherApp.config.Config
import weatherApp.models.{OpenWeatherBaseCity, WeatherResponse, CityForUser}

import scala.concurrent.Future


object AddCityBtn {
  case class Props(city: OpenWeatherBaseCity, userId: Int)

  class Backend(bs: BackendScope[Props, Unit]) {
    val host: String = Config.AppConfig.apiHost

    def addCityForUser(city: OpenWeatherBaseCity, userId: Int): Callback = {
      val cityForUser = CityForUser(city, userId).asJson.asInstanceOf[dom.ext.Ajax.InputData]
      Callback {
        dom.ext.Ajax.get(url = s"$host/weather?city=$city", cityForUser)
      }
    }


    def render(props: Props): VdomElement =
      <.div(
        <.button(
          ^.cls := "weather-box-btn",
          ^.marginLeft := 5.px,
          ^.border := "none",
          ^.cursor := "pointer",
          ^.color := "white",
          ^.padding := "5px 10px",
          ^.borderRadius := "2px",
          ^.onClick --> addCityForUser(props.city, props.userId),
          "Add to favorites"
        )
      )
  }

  val Component = ScalaComponent.builder[Props]("AddCityBtn")
    .renderBackend[Backend].build
}
