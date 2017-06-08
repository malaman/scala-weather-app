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
  weather: Array[Weather],
  wind: Wind,
  rain: Rain,
  dt_txt: String
)

case class WeatherForecastResponse (
  cod: String,
  message: Float,
  cnt: Int,
  list: Array[ForecastWeather],
  city: OpenWeatherCity
)
