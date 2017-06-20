package weatherApp.models

import io.circe.generic.extras._
import io.circe.generic.extras.defaults._
import io.circe.syntax._

case class OpenWeatherCity (
  id: Int,
  name: String,
  coord: Coord,
  country: String
)

@ConfiguredJsonCodec case class Rain (
  @JsonKey("3h") three_h: Option[Float]
)
case class ForecastWeather (
  dt: Int,
  main: Main,
  weather: List[Weather],
  wind: Wind,
  rain: Option[Rain],
  dt_txt: String
)

case class DailyForecast (
  day: Int,
  weather: Weather,
  temp_min: Float,
  temp_max: Float
)

case class WeatherForecastResponse (
  cod: String,
  message: Float,
  cnt: Int,
  list: List[ForecastWeather],
  city: OpenWeatherCity,
  daily: List[DailyForecast]
)
