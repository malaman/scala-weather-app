package demo.models

case class APIWeather (
  id: Int,
  main: String,
  description: String
)

case class WeatherInfo (
  temp: Float,
  pressure: Int,
  humidity: Int,
  temp_min: Float,
  temp_max: Float
)

case class WeatherResponse (
  name: String,
  weather: Array[APIWeather],
  main: WeatherInfo
)
