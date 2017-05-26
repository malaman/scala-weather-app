package weatherApp.models

case class Coord (
  lon: Float,
  lat: Float
)

case class Weather (
  id: Int,
  main: String,
  description: String
)

case class Main (
  temp: Float,
  pressure: Int,
  humidity: Int,
  temp_min: Float,
  temp_max: Float
)

case class Wind (
  speed: Float,
  deg: Int
)

case class Clouds (
  all: Int
)

case class Sys (
  country: String,
  sunrise: Int,
  sunset: Int
)

case class WeatherResponse (
  coord: Coord,
  weather: Array[Weather],
  main: Main,
  visibility: Int,
  wind: Wind,
  clouds: Clouds,
  sys: Sys,
  name: String
)
