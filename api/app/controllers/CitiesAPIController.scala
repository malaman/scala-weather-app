package controllers

import play.api.mvc._
import scala.concurrent._
import ExecutionContext.Implicits.global
import javax.inject.{Inject, Singleton}
import services.CitiesService

@Singleton
class CitiesAPIController @Inject() (citiesService: CitiesService) extends Controller {
  def getCitySuggestions(city: String) = Action.async { request =>
    val city = request.getQueryString("city").mkString
    val citiesFuture = citiesService.getCities(city);
    citiesFuture.map {
      case response => {
        Ok(response.body)
      }
      case _ => BadRequest("{\"error\": \"City suggestions response error\"}")
    }
    }
}
