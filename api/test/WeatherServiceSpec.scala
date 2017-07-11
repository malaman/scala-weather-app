import services.{CitiesService, WeatherService}
import org.scalatestplus.play._
import play.core.server.Server
import play.api.routing.sird._
import play.api.test._

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Await
import scala.concurrent.duration._
import models.WeatherResponse

class WeatherServiceSpec extends PlaySpec {
  "WeatherService" should {
    "getWeatherForCity return an Array of Weather Response objects" in {
      Server.withRouter() {
        case GET(p"/maps/api/place/autocomplete/json") => ActionMocks.getCities()
        case GET(p"/data/2.5/weather") => ActionMocks.getWeather()
      } { implicit port =>
        WsTestClient.withClient { client =>
          val citiesService = new CitiesService(client, "")
          val weatherService = new WeatherService(client, "")
          val citiesFuture = citiesService.getCities("Some")
          val result = Await.result(weatherService.getWeatherForCity(citiesFuture), 10.seconds)
          val jsresp = result.validate[List[WeatherResponse]]
          jsresp fold (
            err => fail,
            resp => {
              resp.head.id must be (2950159)
              resp.head.name must be ("Berlin")
              assert(resp.head.main.temp - 17.49 < 0.001)
            }
          )
        }
      }
    }
  }
}
