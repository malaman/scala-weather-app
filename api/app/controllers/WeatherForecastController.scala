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

  def getForecastForCity() = Action.async { implicit request =>
    val cityID = request.getQueryString("id").mkString
    weatherForecastService.getForecastForCity(cityID).map(resp => {
      val err = resp \ "error"
      err match {
        case err: JsDefined => BadRequest(resp)
        case _ => Ok(resp)
      }
    })
  }
}
