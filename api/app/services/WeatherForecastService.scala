package services

import javax.inject.{Inject, Singleton}
import scala.concurrent._
import scala.util.Properties

import play.api.libs.json.Json
import play.api.libs.ws._
import play.api.libs.json.JsValue

import models.{WeatherForecast, WeatherForecastResponse}
import utils.WeatherUtils

@Singleton
class WeatherForecastService (ws: WSClient, baseUrl: String)(implicit ec: ExecutionContext) {
  @Inject() def this (ws: WSClient, ec: ExecutionContext) = this(ws, "http://api.openweathermap.org")(ec)

  val API_KEY: String = Properties.envOrElse("WEATHER_API_KEY", "WEATHER_API_KEY")

  def getForecastByCityID(cityID: String): Future[WSResponse] = {
    val url = s"$baseUrl/data/2.5/forecast?id=$cityID&appid=$API_KEY&units=metric"
    ws.url(url).get()
  }

  def getForecastByCityName(cityName: String): Future[WSResponse] = {
    val url = s"$baseUrl/data/2.5/forecast?q=$cityName&appid=$API_KEY&units=metric"
    ws.url(url).get()
  }

  def getForecastForCityByID(id: String): Future[JsValue] = {
    val forecastFuture = getForecastByCityID(id)
    forecastFuture.map(response => {
      val resp = Json.parse(response.body)
      val jsresp = resp.validate[WeatherForecastResponse]
      jsresp.fold(
        err => Json.obj("error" -> err.toString()),
        forecast => {
          val daily = WeatherUtils.getDailyWeather(forecast)
          val result = WeatherForecast(
            forecast.cod,
            forecast.message,
            forecast.cnt,
            forecast.list,
            forecast.city,
            daily
          )
          Json.toJson(result)
        }
      )
    })
  }
}
