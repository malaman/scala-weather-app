import scala.concurrent.{Future}
import services.{CitiesService}
import controllers.{CitiesAPIController}
import org.scalatestplus.play._
import play.api.mvc._
import play.core.server.Server
import play.api.routing.sird._
import play.api.test._

class CitiesAPIControllerSpec extends PlaySpec {
  "CitiesAPIController" should {
    "getCitySuggestions should return response from google maps API" in {
      
      Server.withRouter() {
        case GET(p"/maps/api/place/autocomplete/json") => ActionMocks.getCities()
      } { implicit port =>
        WsTestClient.withClient { client =>          
          import play.api.test.Helpers._
          val controller = new CitiesAPIController(stubControllerComponents(), new CitiesService(client, ""))
          val result: Future[Result] = controller.getCitySuggestions().apply(FakeRequest(GET, "/cities?city=Berlin"))
          val bodyText: String = contentAsString(result)
          bodyText must be (Fixtures.cities)
        }
      }
    }
  }
}
