import services.WeatherForecastService
import org.scalatestplus.play._

import play.core.server.Server
import play.api.routing.sird._
import play.api.test._

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Await
import scala.concurrent.duration._
import models.WeatherForecast

class WeatherForecastServiceSpec extends PlaySpec {
  "WeatherForecastService" should {
    "return an WeatherForecast object via getForecastForCity method call" in {
      Server.withRouter() {
        case GET(p"/data/2.5/forecast") => ActionMocks.getWeatherForecast()
      } { implicit port =>
        WsTestClient.withClient { client =>
          val forecastService = new WeatherForecastService(client, "")
          val result = Await.result(forecastService.getForecastForCityByID("1234"), 10.seconds)
          val jsresp = result.validate[WeatherForecast]
          jsresp fold (
            err => fail,
            resp => {
              resp.daily.length must be (5)
              resp.daily.head.day must be (2)
              assert(math.abs(resp.daily.head.temp_max - 21.51) < Fixtures.Eps)
            }
          )
        }
      }
    }
  }
}
