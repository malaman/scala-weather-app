package weatherApp.diode

import diode._
import diode.react.ReactConnector
import weatherApp.models.{GithubUser, WeatherForecastResponse, WeatherResponse}

object AppCircuit extends Circuit[AppModel] with ReactConnector[AppModel] {
  def initialModel = AppModel(
    AppState(
      weatherSuggestions = List.empty[WeatherResponse],
      forecast = None: Option[WeatherForecastResponse],
      selectedWeather = None: Option[WeatherResponse],
      isLoading = false,
      user = None : Option[GithubUser]
    )
  )

  override val actionHandler = composeHandlers(
    new WeatherPageHandler(zoomTo(_.state)),
    new AppHandler(zoomTo(_.state))
  )
}

class WeatherPageHandler[M](modelRW: ModelRW[M, AppState]) extends ActionHandler(modelRW) {
  override def handle = {
    case GetWeatherSuggestions(weatherSuggestions) => updated(value.copy(weatherSuggestions = weatherSuggestions))
    case GetWeatherForecast(forecast) => updated(value.copy(forecast = forecast))
    case ClearForecast() => updated(value.copy(forecast = None))
    case SelectWeather(weather) => updated(value.copy(selectedWeather = weather))
  }
}

class AppHandler[M](modelRW: ModelRW[M, AppState]) extends ActionHandler(modelRW) {
  override def handle = {
    case SetLoadingState() => updated(value.copy(isLoading = true))
    case ClearLoadingState() => updated(value.copy(isLoading = false))
    case GetUserInfo(user) => updated(value.copy(user = user))
  }
}
