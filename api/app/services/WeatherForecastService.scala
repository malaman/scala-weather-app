package services

import javax.inject.{Inject, Singleton}
import play.api.libs.ws._
import scala.concurrent._
import scala.util.Properties

@Singleton
class WeatherForecastService (ws: WSClient, baseUrl: String) {
  @Inject() def this (ws: WSClient) = this(ws, "http://api.openweathermap.org")

  val API_KEY = Properties.envOrElse("WEATHER_API_KEY", "WEATHER_API_KEY")

  def getForecast(cityID: String): Future[WSResponse] = {
    val url = s"${baseUrl}/data/2.5/forecast?id=${cityID}&appid=${API_KEY}&units=metric"
    ws.url(url).get()
  }
}
