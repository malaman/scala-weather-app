package models

import play.api.libs.json._
import play.api.libs.functional.syntax._

case class OpenWeatherCity (
  id: Int,
  name: String,
  coord: Coord,
  country: String
)

object OpenWeatherCity {
  implicit val f = Json.format[OpenWeatherCity]
}

case class Rain (
  three_h: Option[Float] = None: Option[Float]
)

object Rain {
  implicit val writes: Writes[Rain] = (JsPath \ "three_h").write[Option[Float]].contramap((f: Rain) => f.three_h)


  implicit val reads: Reads[Rain] = (JsPath \ "3h").readNullable[Float].map(Rain.apply _)
}

case class ForecastWeather (
  dt: Int,
  main: Main,
  weather: List[Weather],
  wind: Wind,
  rain: Option[Rain],
  dt_txt: String
)

object ForecastWeather {
  implicit val f = Json.format[ForecastWeather]
}

case class DailyForecast (
  day: Int,
  weather: Weather,
  temp_min: Float,
  temp_max: Float
)

object DailyForecast {
  implicit val f = Json.format[DailyForecast]
}
case class WeatherForecastResponse (
  cod: String,
  message: Float,
  cnt: Int,
  list: List[ForecastWeather],
  city: OpenWeatherCity
)

object WeatherForecastResponse {
  implicit val f = Json.format[WeatherForecastResponse]
}

case class WeatherForecast (
  cod: String,
  message: Float,
  cnt: Int,
  list: List[ForecastWeather],
  city: OpenWeatherCity,
  daily: List[DailyForecast]
)

object WeatherForecast {
  implicit val f = Json.format[WeatherForecast]
}
