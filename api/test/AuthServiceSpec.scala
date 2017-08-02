import services.AuthService
import org.scalatestplus.play._

import play.core.server.Server
import play.api.routing.sird._
import play.api.test._

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Await
import scala.concurrent.duration._
import models.GithubUser

class AuthServiceSpec extends PlaySpec {
  "AuthServiceSpec" should {
    "return return an access_token via postCode method call" in {
      Server.withRouter() {
        case POST(p"/login/oauth/access_token") => ActionMocks.getGithubTokenResponse()
      } { implicit port =>
        WsTestClient.withClient { client =>
          val authService = new AuthService(client, "", "")
          val result = Await.result(authService.postCode("1234"), 10.seconds)
          result must be ("12345551233123")
        }
      }
    }

    "return a github user object via getUserInfo method" in {
      Server.withRouter() {
        case GET(p"/user") => ActionMocks.getGithubUserResponse()
      } { implicit port =>
        WsTestClient.withClient { client =>
          val authService = new AuthService(client, "", "")
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

    "return a github user object via authenticate method when token is stored in user session" in {
      Server.withRouter() {
        case GET(p"/user") => ActionMocks.getGithubUserResponse()
        case POST(p"/login/oauth/access_token") => ActionMocks.getGithubTokenResponse()
      } { implicit port =>
        WsTestClient.withClient { client =>
          val authService = new AuthService(client, "", "")
          val resp = Await.result(authService.authenticate(Some("some access token"), "code"), 10.seconds)
          val jsresp = resp.user.validate[GithubUser]
          jsresp.fold(
            error => assertTypeError("GithubUser validation fails"),
            result => {
              result.login must be ("poweruser")
              result.id must be (12313)
            }
          )
          resp.token must be ("some access token")
        }
      }
    }

    "return a github user object via authenticate method when no token exists in user session" in {
      Server.withRouter() {
        case GET(p"/user") => ActionMocks.getGithubUserResponse()
        case POST(p"/login/oauth/access_token") => ActionMocks.getGithubTokenResponse()
      } { implicit port =>
        WsTestClient.withClient { client =>
          val authService = new AuthService(client, "", "")
          val resp = Await.result(authService.authenticate(None, "code"), 10.seconds)
          val jsresp = resp.user.validate[GithubUser]
          jsresp.fold(
            error => assertTypeError("GithubUser validation fails"),
            result => {
              result.login must be ("poweruser")
              result.id must be (12313)
            }
          )
          resp.token must be ("12345551233123")
        }
      }
    }
  }
}
