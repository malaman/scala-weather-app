import services.WeatherService
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
    "return an Array of Weather Response objects via getWeatherForCity method call" in {
      Server.withRouter() {
        case GET(p"/data/2.5/find") => ActionMocks.getWeatherList()
      } { implicit port =>
        WsTestClient.withClient { client =>
          val weatherService = new WeatherService(client, "")
          val result = Await.result(weatherService.getWeatherForCity("some"), 10.seconds)
          val jsresp = result.validate[List[WeatherResponse]]
          jsresp fold (
            err => fail,
            resp => {
              resp.head.id must be (2950159)
              resp.head.name must be ("Berlin")
              assert(math.abs(resp.head.main.temp - 14) < Fixtures.Eps)
            }
          )
        }
      }
    }
  }
}
