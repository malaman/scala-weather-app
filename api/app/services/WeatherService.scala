package services

import javax.inject.{Inject, Singleton}

import models.{FindWeatherResponse}
import play.api.libs.json.Json
import play.api.libs.ws._

import scala.concurrent._
import scala.util.Properties
import play.api.libs.json.JsValue
import play.api.Logger

@Singleton
class WeatherService (ws: WSClient, baseUrl: String)(implicit ec: ExecutionContext) {
  @Inject() def this(ws: WSClient, ec: ExecutionContext) = this(ws, "http://api.openweathermap.org")(ec)

  val API_KEY: String = Properties.envOrElse("WEATHER_API_KEY", "WEATHER_API_KEY")
  val logger: Logger = Logger(this.getClass)

  def getWeatherForCityById(id: String): Future[WSResponse] = {
    val url = s"$baseUrl/data/2.5/weather?id=$id&appid=$API_KEY&units=metric"
    ws.url(url).get()
  }

  def getWeatherList(city: String): Future[WSResponse] = {
    val url = s"$baseUrl/data/2.5/find?q=$city&appid=$API_KEY&units=metric&type=like"
    ws.url(url).get()
  }

  def getWeatherForCity(city: String): Future[JsValue] = {
    getWeatherList(city).map(response => {
      val resp = Json.parse(response.body)
      val jsresp = resp.validate[FindWeatherResponse]
      jsresp.fold(
        err => {
          logger.error(s"getWeatherForCity: ${err.toString}")
          Json.obj("error" -> err.toString())
        },
        list => Json.toJson(list.list)
      )
    })
  }
}
