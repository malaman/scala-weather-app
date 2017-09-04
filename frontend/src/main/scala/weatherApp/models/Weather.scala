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
                  pressure: Float,
                  humidity: Int,
                  temp_min: Float,
                  temp_max: Float
                )

case class Wind (
                  speed: Float,
                  deg: Option[Float]
                )

case class Clouds (
                    all: Int
                  )

case class Sys (
                 country: String,
                 sunrise: Option[Int],
                 sunset: Option[Int]
               )

case class WeatherResponse (
                             id: Int,
                             coord: Coord,
                             weather: List[Weather],
                             main: Main,
                             visibility: Option[Int],
                             wind: Wind,
                             clouds: Clouds,
                             sys: Sys,
                             name: String
                           )

case class OpenWeatherBaseCity (
                                 id: Int,
                                 name: String,
                                 lon: Float,
                                 lat: Float,
                               )
