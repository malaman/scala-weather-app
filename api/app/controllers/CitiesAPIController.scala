package controllers

import play.api.mvc._
import play.api.libs.json._
import play.api.libs.functional.syntax._
import play.api.libs.ws._
import scala.concurrent._
import ExecutionContext.Implicits.global
import javax.inject.{Inject, Singleton}
import scala.util.Properties
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
        }
    }
}
