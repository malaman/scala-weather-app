package services

import javax.inject.{Inject, Singleton}

import models.{GoogleCity, WeatherResponse}
import play.api.libs.json.Json
import play.api.libs.ws._

import scala.collection.mutable.ListBuffer
import scala.concurrent._
import scala.util.Properties
import play.api.libs.json.JsValue

@Singleton
class WeatherService (ws: WSClient, baseUrl: String)(implicit ec: ExecutionContext) {
  @Inject() def this(ws: WSClient, ec: ExecutionContext) = this(ws, "http://api.openweathermap.org")(ec)

  val API_KEY = Properties.envOrElse("WEATHER_API_KEY", "WEATHER_API_KEY")

  def getWeather(city: String): Future[WSResponse] = {
    val url = s"${baseUrl}/data/2.5/weather?q=${city}&appid=${API_KEY}&units=metric"
    ws.url(url).get()
  }

  def getWeatherForCity(citiesFuture: Future[WSResponse]): Future[JsValue] = {
    citiesFuture.flatMap {
      case response =>
        val resp = Json.parse(response.body)
        val predictions = resp \ "predictions"
        var futures: Seq[Future[WSResponse]] = Seq.empty[Future[WSResponse]]
        val jsr = predictions.validate[Seq[GoogleCity]]
        jsr.fold(
          errors => Future(Json.obj("error" -> "City response error")),
          cities => {
            futures = cities.map { item =>
              getWeather(s"${item.structured_formatting.main_text},${item.structured_formatting.secondary_text}")
            }
          }
        )
        val f = Future.sequence(futures)
        f map { results => {
          var list = new ListBuffer[WeatherResponse]
          results.map { result => {
            val resp = Json.parse(result.body)
            val jsresp = (resp).validate[WeatherResponse]
            jsresp.fold (
              err => Json.obj("error" -> "Weather response validation error"),
              weath => list += weath
            )
          }
          }
          Json.toJson(list.toList)
        }
      }
    }
  }
}
