package services

import javax.inject._
import play.api.libs.functional.syntax._
import javax.inject.{Inject, Singleton}
import play.api.libs.ws._
import scala.concurrent._
import ExecutionContext.Implicits.global
import scala.util.Properties

@Singleton
class WeatherService @Inject() (ws: WSClient) {

    val API_KEY = Properties.envOrElse("WEATHER_API_KEY", "WEATHER_API_KEY")

    def getWeather(city: String): Future[WSResponse] = {
        val url = s"http://api.openweathermap.org/data/2.5/weather?q=${city}&appid=${API_KEY}&units=metric"
        ws.url(url).get()
    }
}
