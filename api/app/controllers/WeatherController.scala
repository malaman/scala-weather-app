package controllers

import play.api.mvc._
import play.api.libs.json._
import play.api.libs.functional.syntax._
import play.api.libs.ws._
import scala.concurrent._
import ExecutionContext.Implicits.global
import javax.inject.{Inject, Singleton}
import scala.util.Properties
import scala.collection.mutable.ListBuffer
import models._
import services.{CitiesService, WeatherService}

@Singleton
class WeatherController @Inject() (
    citiesService: CitiesService,
    weatherService: WeatherService
) extends Controller {

    def getWeatherForCity(city: String) = Action.async {request =>
        val city = request.getQueryString("city").mkString
        val citiesFuture = citiesService.getCities(city);
        citiesFuture.flatMap {
            response => {
                val resp = Json.parse(response.body)
                val predictions = (resp \ "predictions")
                var futures: Seq[Future[WSResponse]] = Seq.empty[Future[WSResponse]]
                val jsr = predictions.validate[Seq[GoogleCity]]
                jsr.fold(
                  errors => Future(Ok("errors")),
                  cities => {
                    futures = cities.map { item =>
                        weatherService.getWeather(s"${item.structured_formatting.main_text}, ${item.structured_formatting.secondary_text}")
                    }
                  }
                )
                val f = Future.sequence(futures)
                f map {
                  case results => {
                    var list = new ListBuffer[WeatherResponse]
                    results.map { result => {
											val resp = Json.parse(result.body)
                        val jsresp = (resp).validate[WeatherResponse]
                        jsresp.fold (
                            err => println(err),
                            weath => list += weath
                        )
                    	}
                    }
                    Ok(Json.arr(list.toList))
                    }
                    case t => BadRequest("An error has occured: " + t)
                }
            }
        }
    }
}
