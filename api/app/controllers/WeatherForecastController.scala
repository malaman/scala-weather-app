package controllers

import play.api.mvc._
import play.api.libs.json._
import play.api.libs.functional.syntax._
import play.api.libs.ws._
import scala.concurrent._
import ExecutionContext.Implicits.global
import javax.inject.{Inject, Singleton}
import scala.util.Properties
import scala.collection.mutable.ListBuffer
import models._
import services.{WeatherForecastService}


@Singleton
class WeatherForecastController @Inject() (
  weatherForecastService: WeatherForecastService
) extends Controller {

  def getWeatherForCity(cityID: String) = Action.async {request =>
    val cityID = request.getQueryString("id").mkString
    val forecastFuture = weatherForecastService.getForecast(cityID)
    forecastFuture.map {
        case response => {
          val resp = Json.parse(response.body)
          val jsresp = (resp).validate[WeatherForecastResponse]
          jsresp.fold(
            errors => BadRequest(s"{error: Parsing error - ${errors}}"),
            forecast => {
              Ok(Json.toJson(forecast))
            }
          )
        }
        case failure => {
          BadRequest("{\"error\": \"Network error\"}")
        }
    }
  }
}
