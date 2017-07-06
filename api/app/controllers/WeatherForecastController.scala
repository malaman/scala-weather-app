package controllers

import play.api.mvc._
import play.api.libs.json._
import scala.concurrent._
import ExecutionContext.Implicits.global
import javax.inject.{Inject, Singleton}
import models._
import services.{WeatherForecastService}
import utils.{WeatherUtils}

@Singleton
class WeatherForecastController @Inject() (
  cc: ControllerComponents,
  weatherForecastService: WeatherForecastService
) extends AbstractController(cc) {

  def getForecastForCity() = Action.async {request =>
    val cityID = request.getQueryString("id").mkString
    val forecastFuture = weatherForecastService.getForecast(cityID)
    forecastFuture.map {
        case response => {
          val resp = Json.parse(response.body)
          val jsresp = (resp).validate[WeatherForecastResponse]
          jsresp.fold(
            errors => BadRequest("{\"error\": \"Forecast validation error\"}"),
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
              Ok(Json.toJson(result))
            }
          )
        }
    }
  }
}
