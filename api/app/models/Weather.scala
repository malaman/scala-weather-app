package models

import play.api.libs.json._

case class APIWeather (
    id: Int,
    main: String,
    description: String
)
object APIWeather {
    implicit val w = Json.format[APIWeather]
}

case class WeatherInfo (
    temp: Float,
    pressure: Int,
    humidity: Int,
    temp_min: Float,
    temp_max: Float
)

object WeatherInfo {
    implicit val w = Json.format[WeatherInfo]
}

case class WeatherResponse (
    name: String,
    weather: List[APIWeather],
    main: WeatherInfo
)

object WeatherResponse {
    implicit val w = Json.format[WeatherResponse]
}
