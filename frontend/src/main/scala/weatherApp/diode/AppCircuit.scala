package weatherApp.diode

import scala.scalajs.js.Dynamic.{global => g}

import diode._
import diode.react.ReactConnector

import weatherApp.models.{WeatherResponse}

object AppCircuit extends Circuit[AppModel] with ReactConnector[AppModel] {
  def initialModel = AppModel(
    AppState(
      Array.empty[WeatherResponse],
      0
    )
  )

  override val actionHandler = composeHandlers(
    new WeatherPageHandler(zoomTo(_.state.weatherSuggestions))
  )
}

class WeatherPageHandler[M](modelRW: ModelRW[M, Array[WeatherResponse]]) extends ActionHandler(modelRW) {
  override def handle = {
    case GetWeatherForCity(weatherSuggestions) => {
      updated(weatherSuggestions)
    }
  }
}
