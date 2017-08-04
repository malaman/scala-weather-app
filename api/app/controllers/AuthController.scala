package controllers

import play.api.mvc._
import scala.concurrent._
import javax.inject.{Inject, Singleton}
import services.AuthService

@Singleton
class AuthController @Inject() (
                                 cc: ControllerComponents,
                                 authService: AuthService
                               )(implicit ec: ExecutionContext) extends AbstractController(cc) {

  def authenticate() = Action { implicit request =>
    Redirect(s"https://github.com/login/oauth/authorize?scope=user:email&client_id=${authService.GITHUB_CLIENT_ID}&redirect_uri=${authService.HOST}/api/callback&response_type=code")
  }

  def callback() = Action.async { implicit request =>
    val code = request.getQueryString("code").mkString
    authService.postCode(code).map {token =>
      Redirect(authService.HOST).withSession("access_token" -> token)
    }
  }

  def getUserInfo() = Action.async { implicit request =>
    val tokenOption: Option[String] = request.session.get("access_token").map {token => token}
    if (tokenOption.isDefined) {
      val token = tokenOption.get
      authService.getUserInfo(token).map { resp =>
        Ok(resp).withSession("access_token" -> token)
      }
    } else {
      Future(Ok("{}"))
    }
  }

  def logout() = Action {implicit request =>
    Redirect(authService.HOST).withNewSession
  }
}
