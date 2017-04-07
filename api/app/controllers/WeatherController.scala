package controllers

import play.api.mvc._
import play.api.libs.json._
import play.api.libs.functional.syntax._
import play.api.libs.ws._
import scala.concurrent._
import ExecutionContext.Implicits.global
import javax.inject.{Inject, Singleton}
import scala.util.Properties
import play.api.libs.json._
import scala.collection.mutable.ListBuffer



case class GoogleCity (
    description: String
)

object GoogleCity {
    implicit val city = Json.format[GoogleCity]
}

case class APIWeather (
    id: Int,
    main: String,
    description: String
)

object APIWeather {
    implicit val weather = Json.format[APIWeather]
}

case class WeatherResponse (
    weather: APIWeather
)

object WeatherResponse {
    implicit val weather = Json.format[WeatherResponse]
}

@Singleton
class WeatherController @Inject() (ws: WSClient, citiesController: CitiesAPIController) extends Controller {
    val API_KEY = Properties.envOrElse("WEATHER_API_KEY", "WEATHER_API_KEY")

    def getWeather(city: String): Future[WSResponse] = {
        val url = s"http://api.openweathermap.org/data/2.5/weather?q=${city}&appid=${API_KEY}"
        ws.url(url).get()
    }

    def getWeatherForCity(city: String) = Action.async {request =>
        val city = request.getQueryString("city").mkString
        val citiesFuture = citiesController.getCities(city);
        citiesFuture.flatMap {
            response => {
                val resp = Json.parse(response.body)
                val predictions = (resp \ "predictions")
                val jsr = predictions.validate[Seq[GoogleCity]]
                jsr.fold(
                    errors => {
                         Future(Ok("errors"))
                    },
                    cities => {
                        val futures = cities.map { item =>
                            getWeather(item.description)
                        }
                        val f = Future sequence futures
                        f map {
                            case results => {
                                Ok((results.map { result => result.body}).mkString("[", ",", "]"))
                            }
                            case t => Ok("An error has occured: " + t)
                        }
                })
            }
        }
    }
}
