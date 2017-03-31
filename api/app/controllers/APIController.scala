package controllers

import play.api.mvc._
import play.api.libs.json._
import play.api.libs.functional.syntax._

case class Location(lat: Double, long: Double)

case class Weather(temperature: Double, humidity: Double, weatherType: String)

case class Place(name: String, location: Location, weather: Weather)

object Place {

  var list: List[Place] = {
    List(
      Place(
        "Hamburg",
        Location(53.552046, 9.997050),
        Weather(15.5, 80, "sun")
      ),
      Place(
        "Berlin",
        Location(52.534786, 13.365689),
        Weather(17.5, 60, "rain")
      )
    )
  }

  def save(place: Place) = {
    list = list ::: List(place)
  }
}

class APIController extends Controller {
    implicit val locationWrites: Writes[Location] = (
      (JsPath \ "lat").write[Double] and
      (JsPath \ "long").write[Double]
    )(unlift(Location.unapply))

    implicit val weatherWrites: Writes[Weather] = (
      (JsPath \ "temperature").write[Double] and
      (JsPath \ "humidity").write[Double] and
      (JsPath \ "weatherType").write[String]
    )(unlift(Weather.unapply))

    implicit val placeWrites: Writes[Place] = (
      (JsPath \ "name").write[String] and
      (JsPath \ "location").write[Location] and
      (JsPath \ "weather").write[Weather]
    )(unlift(Place.unapply))

    implicit val locationReads: Reads[Location] = (
      (JsPath \ "lat").read[Double] and
      (JsPath \ "long").read[Double]
    )(Location.apply _)

    implicit val weatherReads: Reads[Weather] = (
      (JsPath \ "temperature").read[Double] and
      (JsPath \ "humidity").read[Double] and
      (JsPath \ "weatherType").read[String]
  )(Weather.apply _)

    implicit val placeReads: Reads[Place] = (
      (JsPath \ "name").read[String] and
      (JsPath \ "location").read[Location] and
      (JsPath \ "weather").read[Weather]
    )(Place.apply _)

    def listPlaces = Action {
        val json = Json.toJson(Place.list)
        Ok(json)
    }

    def savePlace = Action(BodyParsers.parse.json) { request =>
        val placeResult = request.body.validate[Place]
        placeResult.fold(
            errors => {
                BadRequest(Json.obj("status" -> "KO", "message" -> JsError.toJson(errors)))
            },
            place => {
                Place.save(place)
                Ok(Json.obj("status" -> "OK", "message" -> ("Place '"+place.name+"' saved.") ))
            }
        )
    }
}
