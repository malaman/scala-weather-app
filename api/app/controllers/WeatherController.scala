package controllers

import play.api.mvc._
import play.api.libs.json._
import scala.concurrent._
import javax.inject.{Inject, Singleton}
import services.{CitiesService, WeatherService}

@Singleton
class WeatherController @Inject() (
    cc: ControllerComponents,
    citiesService: CitiesService,
    weatherService: WeatherService
)(implicit ec: ExecutionContext) extends AbstractController(cc) {

  def getWeatherForCity() = Action.async { implicit request =>
    val city = request.getQueryString("city").mkString
    val citiesFuture = citiesService.getCities(city)
    weatherService.getWeatherForCity(citiesFuture).map(resp => {
      val err = resp \ "error"
      err match {
        case err: JsDefined => BadRequest(resp)
        case _ => Ok(resp)
      }
    })
  }
}
