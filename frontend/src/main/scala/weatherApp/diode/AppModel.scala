package weatherApp.diode

import diode.Action
import weatherApp.models.{WeatherResponse, WeatherForecastResponse}

case class AppState (
  weatherSuggestions: List[WeatherResponse],
  forecast: Option[WeatherForecastResponse],
  selectedWeather: Option[WeatherResponse],
  isLoading: Boolean
)

case class AppModel(
  state: AppState
)

case class GetWeatherSuggestions(suggestions: List[WeatherResponse]) extends Action

case class GetWeatherForecast(forecast: Option[WeatherForecastResponse]) extends Action

case class ClearForecast() extends Action

case class SelectWeather(weather: Option[WeatherResponse]) extends Action

case class SetLoadingState() extends Action

case class ClearLoadingState() extends Action
