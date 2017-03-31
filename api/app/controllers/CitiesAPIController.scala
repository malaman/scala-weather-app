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
    def getCitySuggestions = Action.async {
        val city = "Paris"
      ws.url(s"https://maps.googleapis.com/maps/api/place/autocomplete/json?input=${city}&types=geocode&key=${API_KEY}").get().map { response =>
        Ok(response.body)
      }
    }
}
