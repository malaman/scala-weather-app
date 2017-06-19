package controllers

import play.api.mvc._
import play.api.libs.json._
import scala.concurrent._
import ExecutionContext.Implicits.global
import javax.inject.{Inject, Singleton}
import models._
import services.{WeatherForecastService}

@Singleton
class WeatherForecastController @Inject() (
  weatherForecastService: WeatherForecastService
) extends Controller {

  def getForecastForCity(cityID: String) = Action.async {request =>
    val cityID = request.getQueryString("id").mkString
    val forecastFuture = weatherForecastService.getForecast(cityID)
    forecastFuture.map {
        case response => {
          val resp = Json.parse(response.body)
          val jsresp = (resp).validate[WeatherForecastResponse]
          jsresp.fold(
            errors => BadRequest("{\"error\": \"Forecast validation error\"}"),
            forecast => {
              Ok(Json.toJson(forecast))
            }
          )
        }
        case _ => BadRequest("{\"error\": \"Network error\"}")
    }
  }
}
