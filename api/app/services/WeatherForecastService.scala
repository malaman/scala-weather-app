package services

import javax.inject._
import javax.inject.{Inject, Singleton}
import play.api.libs.ws._
import scala.concurrent._
import scala.util.Properties

@Singleton
class WeatherForecastService @Inject() (ws: WSClient) {
  val API_KEY = Properties.envOrElse("WEATHER_API_KEY", "WEATHER_API_KEY")

  def getForecast(cityID: String): Future[WSResponse] = {
    val url = s"http://api.openweathermap.org/data/2.5/forecast?id=${cityID}&appid=${API_KEY}&units=metric"
    ws.url(url).get()
  }
}
