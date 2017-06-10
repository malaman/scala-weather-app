package models

import play.api.libs.json._

case class Coord (
  lon: Float,
  lat: Float
)

object Coord {
  implicit val v = Json.format[Coord]
}

case class Weather (
  id: Int,
  main: String,
  description: String
)

object Weather {
  implicit val v = Json.format[Weather]
}

case class Main (
  temp: Float,
  pressure: Float,
  humidity: Int,
  temp_min: Float,
  temp_max: Float
)

object Main {
  implicit val v = Json.format[Main]
}

case class Wind (
  speed: Float,
  deg: Option[Float]
)

object Wind {
  implicit val v = Json.format[Wind]
}

case class Clouds (
  all: Int
)

object Clouds {
  implicit val v = Json.format[Clouds]
}

case class Sys (
  country: String,
  sunrise: Int,
  sunset: Int
)

object Sys {
  implicit val v = Json.format[Sys]
}

case class WeatherResponse (
  id: Int,
  coord: Coord = Coord(0, 0),
  weather: Array[Weather],
  main: Main,
  visibility: Option[Int],
  wind: Wind,
  clouds: Clouds,
  sys: Sys,
  name: String
)

object WeatherResponse {
  implicit val v = Json.format[WeatherResponse]
}
