package controllers

import play.api.mvc._
import play.api.libs.json._
import play.api.libs.functional.syntax._
import play.api.libs.ws._
import scala.concurrent._
import ExecutionContext.Implicits.global
import javax.inject.{Inject, Singleton}
import scala.util.Properties

class WeatherController @Inject() (ws: WSClient) extends Controller {
    val API_KEY = Properties.envOrElse("WEATHER_API_KEY", "WEATHER_API_KEY")

    def getWeather(city: String): Future[WSResponse] = {
        val url = s"http://api.openweathermap.org/data/2.5/weather?q=${city}&appid=${API_KEY}"
        ws.url(url).get()
    }

    def getWeatherForCity(city: String) = Action.async { request =>
        val city = request.getQueryString("city").mkString
        getWeather(city).map { response =>
            Ok(response.body)
        }
    }
}
