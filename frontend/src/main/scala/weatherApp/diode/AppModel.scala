package weatherApp.diode

import diode.Action
import weatherApp.models.{WeatherResponse}

case class AppState (
  weatherSuggestions: Array[WeatherResponse],
  some: Int
)

case class AppModel(
  state: AppState
);

case class GetWeatherForCity(suggestions: Array[WeatherResponse]) extends Action
