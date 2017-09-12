package weatherApp.diode

import diode.Action
import weatherApp.models.{OpenWeatherBaseCity, UserResponse, WeatherForecastResponse, WeatherResponse}

case class AppState (
                      weatherSuggestions: List[WeatherResponse],
                      forecast: Option[WeatherForecastResponse],
                      selectedWeather: Option[WeatherResponse],
                      isLoading: Boolean,
                      userInfo: Option[UserResponse],
                      favCitiesWeather: List[WeatherResponse]
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

case class GetUserInfo(userInfo: Option[UserResponse]) extends Action

case class GetWeatherForFavCity(weather: WeatherResponse) extends Action

case class AddCityToFavs(city: OpenWeatherBaseCity, weather: WeatherResponse) extends Action

case class RemoveCityFromFavs(city: OpenWeatherBaseCity, weather: WeatherResponse) extends Action
