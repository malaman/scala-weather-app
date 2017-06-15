package weatherApp.pages

import scala.scalajs.js
import org.scalajs.dom

import diode.react.ModelProxy
import diode.Action

import scala.concurrent.ExecutionContext.Implicits.global
import scala.scalajs.js.Dynamic.{global => g}
import scala.util.{Failure, Success}

import japgolly.scalajs.react._
import japgolly.scalajs.react.vdom.html_<^._
import japgolly.scalajs.react.extra.router.{RouterCtl}

import io.circe.generic.auto._
import io.circe.parser.decode

import weatherApp.router.{AppRouter}
import weatherApp.models.{WeatherForecastResponse}
import weatherApp.config.{Config}
import weatherApp.diode.{AppState}
import weatherApp.components.{WeatherForecastBox}

object CityPage {
  case class State(
    var isLoading: Boolean,
    var forecast: Option[WeatherForecastResponse]
  )

  case class Props(
    proxy: ModelProxy[AppState],
    id: Int,
    name: String,
    ctl: RouterCtl[AppRouter.Page]
  )

  class Backend($: BackendScope[Props, State]) {

    private val props = $.props.runNow()

    def getForecastBoxes(forecast: WeatherForecastResponse) = {
      forecast.list.map(item => {
        WeatherForecastBox.Component.withKey(item.dt)(
          WeatherForecastBox.Props(item)
        )
      })

    }

    def render(props: Props, state: State): VdomElement = {
      val proxy = props.proxy()
      val forecastOption= proxy.forecast
      if (forecastOption.isDefined) {
        val forecast = forecastOption.get
        val Box = WeatherForecastBox.Component(
          WeatherForecastBox.Props(forecast.list(0))
        )
        return <.div(
          <.div(
            ^.fontWeight := "bold",
            ^.marginBottom := 15.px,
            s"${forecast.city.name}, ${forecast.city.country}",
          ),
          <.div(
            ^.display := "flex",
            ^.overflowY := "scroll",
            getForecastBoxes(forecast).toVdomArray
          )
        )
      }
      return <.div()
    }
  }

  val Component = ScalaComponent.builder[Props]("CityPage")
    .initialState(State(
      isLoading = false,
      forecast = None : Option[WeatherForecastResponse]
    ))
    .renderBackend[Backend]
    .build
}
