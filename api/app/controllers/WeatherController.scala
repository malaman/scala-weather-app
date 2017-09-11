package controllers

import play.api.mvc._
import play.api.libs.json._
import scala.concurrent._
import javax.inject.{Inject, Singleton}
import services.WeatherService

@Singleton
class WeatherController @Inject() (
                                    cc: ControllerComponents,
                                    weatherService: WeatherService
                                  )(implicit ec: ExecutionContext) extends AbstractController(cc) {

  def getWeatherForCityByName() = Action.async { implicit request =>
    val city = request.getQueryString("city").mkString
    weatherService.getWeatherForCity(city).map(resp => {
      val err = resp \ "error"
      err match {
        case err: JsDefined => BadRequest(resp)
        case _ => Ok(resp)
      }
    })
  }

  def getWeatherForCityById() = Action.async { implicit request =>
    val id = request.getQueryString("id").mkString
    weatherService.getWeatherForCityById(id).map(resp => Ok(resp.body))
  }
}
