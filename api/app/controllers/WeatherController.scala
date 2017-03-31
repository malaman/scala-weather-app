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

    def getWeatherForCity = Action.async {
        val API_KEY = Properties.envOrElse("WEATHER_API_KEY", "WEATHER_API_KEY");
        val city = "Hamburg";
        ws.url(s"http://samples.openweathermap.org/data/2.5/weather?q=${city}&appid=${API_KEY}").get().map { response =>
            Ok(response.body)
        }
    }
}
