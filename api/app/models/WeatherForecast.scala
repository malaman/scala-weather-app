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
  implicit val v = Json.format[OpenWeatherCity]
}

case class Rain (
  three_h: Option[Float] = None: Option[Float]
)

object Rain {
  implicit val writes: Writes[Rain] = (
    (JsPath \ "three_h").write[Option[Float]].contramap((f: Rain) => f.three_h)
  )

  implicit val reads: Reads[Rain] = (
    (JsPath \ "3h").readNullable[Float].map(Rain.apply _)
  )
}

case class ForecastWeather (
  dt: Int,
  main: Main,
  weather: Array[Weather],
  wind: Wind,
  rain: Rain,
  dt_txt: String
)

object ForecastWeather {
  implicit val v = Json.format[ForecastWeather]
}

case class WeatherForecastResponse (
  cod: String,
  message: Float,
  cnt: Int,
  list: Array[ForecastWeather],
  city: OpenWeatherCity
)

object WeatherForecastResponse {
  implicit val v = Json.format[WeatherForecastResponse]
}
