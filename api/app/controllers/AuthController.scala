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
    Redirect(s"https://github.com/login/oauth/authorize?scope=user:email&client_id=${authService.GITHUB_CLIENT_ID}&redirect_uri=${authService.HOST}&response_type=code")
  }

  def getUserInfo() = Action.async { implicit request =>
    val token: Option[String] = request.session.get("access_token").map {token => token}
    val code = request.getQueryString("code").mkString
    authService.authenticate(token, code).map {resp =>
      Ok(resp.user).withSession("access_token" -> resp.token)
    }
  }

  def logout() = Action {implicit request =>
    Redirect(authService.HOST).withNewSession
  }
}
