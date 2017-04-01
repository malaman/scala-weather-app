package controllers

import play.api.mvc._
import play.api.libs.json._
import play.api.libs.functional.syntax._
import play.api.libs.ws._
import scala.concurrent._
import ExecutionContext.Implicits.global
import javax.inject.{Inject, Singleton}
import scala.util.Properties


class CitiesAPIController @Inject() (ws: WSClient) extends Controller {
    val API_KEY = Properties.envOrElse("GOOGLE_API_KEY", "GOOGLE_API_KEY");

    def getCities(city: String): Future[WSResponse] = {
        ws.url(s"https://maps.googleapis.com/maps/api/place/autocomplete/json?input=${city}&types=geocode&key=${API_KEY}").get()
    }

    def getCitySuggestions(city: String) = Action.async { request =>
        val city = request.getQueryString("city").mkString
        val citiesFuture = getCities(city);
        citiesFuture.map {
            case response => {
                Ok(response.body)
            }
        }
    }
}
