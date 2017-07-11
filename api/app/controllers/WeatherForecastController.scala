package controllers

import play.api.mvc._

import scala.concurrent._
import javax.inject.{Inject, Singleton}

import play.api.libs.json.JsDefined
import services.WeatherForecastService

@Singleton
class WeatherForecastController @Inject() (
  cc: ControllerComponents,
  weatherForecastService: WeatherForecastService
)(implicit ec: ExecutionContext) extends AbstractController(cc) {

  def getRawForecastForCity() = Action.async { implicit request =>
    val cityName = request.getQueryString("city").mkString
    weatherForecastService.getForecastByCityName(cityName).map(resp => Ok(resp.body))
  }

  def getForecastForCity() = Action.async { implicit request =>
    val cityID = request.getQueryString("id").mkString
    weatherForecastService.getForecastForCityByID(cityID).map(resp => {
      val err = resp \ "error"
      err match {
        case err: JsDefined => BadRequest(resp)
        case _ => Ok(resp)
      }
    })
  }
}
