import services.AuthService
import dao.GithubUserDAO
import org.scalatestplus.play._
import play.core.server.Server
import play.api.routing.sird._
import play.api.test._
import play.api.Application


import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Await
import scala.concurrent.duration._
import models.GithubUser
import org.scalatestplus.play.guice.GuiceOneAppPerSuite
import play.api.Configuration
import play.api.db.slick.DatabaseConfigProvider

class AuthServiceSpec extends PlaySpec with GuiceOneAppPerSuite {
  app.configuration.++(Configuration(
    "slick.dbs.default.driver" -> "slick.driver.H2Driver$",
    "slick.dbs.default.db.driver" -> "org.h2.Driver",
    "slick.dbs.default.db.url" -> "jdbc:h2:mem:play"
  ))

  "AuthServiceSpec" should {
    "return return an access_token via postCode method call" in {
      Server.withRouter() {
        case POST(p"/login/oauth/access_token") => ActionMocks.getGithubTokenResponse()
      } { implicit port =>
        WsTestClient.withClient { client =>
          val app2dao = Application.instanceCache[GithubUserDAO]
          val dao: GithubUserDAO = app2dao(app)
          val authService = new AuthService(client, app.environment, app.configuration, dao, "", "")
          val result = Await.result(authService.postCode("1234"), 10.seconds)
          result must be ("12345551233123a")
        }
      }
    }

    "return a github user object via getUserInfo method" in {
      Server.withRouter() {
        case GET(p"/user") => ActionMocks.getGithubUserResponse()
      } { implicit port =>
        WsTestClient.withClient { client =>
          val app2dao = Application.instanceCache[GithubUserDAO]
          val dao: GithubUserDAO = app2dao(app)
          val authService = new AuthService(client, app.environment, app.configuration, dao, "", "")
          val resp = Await.result(authService.getUserInfo("1234"), 10.seconds)
          val jsresp = resp.validate[GithubUser]
          jsresp.fold(
            error => assertTypeError("GithubUser validation fails"),
            result => {
              result.login must be ("poweruser")
              result.id must be (12313)
            }
          )
        }
      }
    }

  }
}
