package weatherApp.diode

import diode.Action
import weatherApp.models.{WeatherResponse, WeatherForecastResponse}

case class AppState (
  weatherSuggestions: List[WeatherResponse],
  forecast: Option[WeatherForecastResponse]
)

case class AppModel(
  state: AppState
)

case class GetWeatherSuggestions(suggestions: List[WeatherResponse]) extends Action

case class GetWeatherForecast(forecast: Option[WeatherForecastResponse]) extends Action
