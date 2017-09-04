import services.UserService
import dao.{GithubUserDAO, OpenWeatherCityDAO, UserCityDAO}
import org.scalatestplus.play._
import play.core.server.Server
import play.api.routing.sird._
import play.api.test._
import play.api.Application

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Await
import scala.concurrent.duration._
import models.UserResponse
import org.scalatestplus.play.guice.GuiceOneAppPerSuite
import play.api.inject.guice._
import org.scalatest.BeforeAndAfterAll

class UserServiceSpec extends PlaySpec with GuiceOneAppPerSuite with Injecting  with BeforeAndAfterAll {

  override def fakeApplication(): Application =
    new GuiceApplicationBuilder()
      .configure(Map(
        "slick.dbs.default.profile" -> "slick.jdbc.H2Profile$",
        "slick.dbs.default.db.driver" -> "org.h2.Driver",
        "slick.dbs.default.db.url" -> "jdbc:h2:mem:play",
        "play.allowGlobalApplication" -> false
    ))
      .build()

  val userApp2dao = Application.instanceCache[GithubUserDAO]
  val userDAO: GithubUserDAO = userApp2dao(app)

  val cityApp2dao = Application.instanceCache[OpenWeatherCityDAO]
  val cityDAO: OpenWeatherCityDAO = cityApp2dao(app)

  val userCityApp2dao = Application.instanceCache[UserCityDAO]
  val userCityDAO: UserCityDAO = userCityApp2dao(app)


  override def beforeAll() = {

    Await.result(
      for {
        _ <- userDAO.setup()
        _ <- cityDAO.setup()
        _ <- userCityDAO.setup()
      } yield 1
      , 5.seconds)
  }

  "UserServiceSpec" should {
    "return return an access_token via postCode method call" in {
      Server.withRouter() {
        case POST(p"/login/oauth/access_token") => ActionMocks.getGithubTokenResponse()
      } { implicit port =>
        WsTestClient.withClient { client =>
          val authService = new UserService(client, app.environment, app.configuration, userDAO, userCityDAO, "", "")
          val result = Await.result(authService.postCode("1234"), 10.seconds)
          result must be ("12345551233123a")
        }
      }
    }

    "return a github user object via getUserInfo method" in  {
      Server.withRouter() {
        case GET(p"/user") => ActionMocks.getGithubUserResponse()
      } { implicit port =>
        WsTestClient.withClient { client =>
          val authService = new UserService(client, app.environment, app.configuration, userDAO, userCityDAO, "", "")
          val resp = Await.result(authService.getUserInfo("1234"), 10.seconds)
          val jsresp = resp.validate[UserResponse]
          jsresp.fold(
            error => assertTypeError("GithubUser validation fails"),
            result => {
              result.user.login must be ("poweruser")
              result.user.id must be (12313)
            }
          )
        }
      }
    }
  }
}
