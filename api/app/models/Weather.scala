package models

import play.api.libs.json._

case class Coord (
  lon: Float,
  lat: Float
)

object Coord {
  implicit val f = Json.format[Coord]
}

case class Weather (
  id: Int,
  main: String,
  description: String
)

object Weather {
  implicit val f = Json.format[Weather]
}

case class Main (
  temp: Float,
  pressure: Float,
  humidity: Int,
  temp_min: Float,
  temp_max: Float
)

object Main {
  implicit val f = Json.format[Main]
}

case class Wind (
  speed: Float,
  deg: Option[Float]
)

object Wind {
  implicit val f = Json.format[Wind]
}

case class Clouds (
  all: Int
)

object Clouds {
  implicit val f = Json.format[Clouds]
}

case class Sys (
  country: String,
  sunrise: Option[Int],
  sunset: Option[Int]
)

object Sys {
  implicit val f = Json.format[Sys]
}

case class WeatherResponse (
  id: Int,
  coord: Coord = Coord(0, 0),
  weather: List[Weather],
  main: Main,
  visibility: Option[Int],
  wind: Wind,
  clouds: Clouds,
  sys: Sys,
  name: String
)

object WeatherResponse {
  implicit val f = Json.format[WeatherResponse]
}

case class FindWeatherResponse (
  message: String,
  cod: String,
  count: Int,
  list: List[WeatherResponse]
)

object FindWeatherResponse {
  implicit val f = Json.format[FindWeatherResponse]
}

case class OpenWeatherBaseCity (
                                 id: Int,
                                 name: String,
                                 lon: Float,
                                 lat: Float
                               )

object OpenWeatherBaseCity {
  implicit val f = Json.format[OpenWeatherBaseCity]
}
