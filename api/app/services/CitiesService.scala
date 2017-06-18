package services

import javax.inject._
import play.api.libs.functional.syntax._
import javax.inject.{Inject, Singleton}
import play.api.libs.ws._
import scala.concurrent._
import ExecutionContext.Implicits.global
import scala.util.Properties

@Singleton
class CitiesService @Inject() (ws: WSClient) {
	val API_KEY = Properties.envOrElse("GOOGLE_API_KEY", "GOOGLE_API_KEY");

	def getCities(city: String): Future[WSResponse] = {
		ws.url(s"https://maps.googleapis.com/maps/api/place/autocomplete/json?input=${city}&types=geocode&key=${API_KEY}").get()
	}
}
