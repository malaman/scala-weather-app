package weatherApp.diode

import scala.scalajs.js.Dynamic.{global => g}

import diode._
import diode.react.ReactConnector
import diode.ActionResult.{ModelUpdate, ModelUpdateSilent}

import weatherApp.models.{WeatherResponse, WeatherForecastResponse}

object AppCircuit extends Circuit[AppModel] with ReactConnector[AppModel] {
  def initialModel = AppModel(
    AppState(
      Array.empty[WeatherResponse],
      None: Option[WeatherForecastResponse]
    )
  )

  override val actionHandler = composeHandlers(
    new WeatherPageHandler(zoomTo(_.state))
  )
}

class WeatherPageHandler[M](modelRW: ModelRW[M, AppState]) extends ActionHandler(modelRW) {
  override def handle = {
    case GetWeatherSuggestions(weatherSuggestions) => {
      updated(value.copy(weatherSuggestions = weatherSuggestions))
    }
    case GetWeatherForecast(forecast) => {
      updated(value.copy(forecast = forecast))
    }
  }
}
