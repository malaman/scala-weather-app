package controllers

import play.api.mvc._
import play.api.libs.json._
import scala.concurrent._
import javax.inject.{Inject, Singleton}
import services.AuthService

@Singleton
class AuthController @Inject() (
                                 cc: ControllerComponents,
                                 authService: AuthService
                               )(implicit ec: ExecutionContext) extends AbstractController(cc) {

  def authenticate() = Action { implicit request =>
    //TODO: change hard-coded URL here
    Redirect(s"https://github.com/login/oauth/authorize?scope=user:email&client_id=${authService.GITHUB_CLIENT_ID}&redirect_uri=http://ec2-52-59-160-108.eu-central-1.compute.amazonaws.com&response_type=code")
  }

  def githubCallback() = Action.async { implicit request =>
    val code = request.getQueryString("code").mkString

    authService.postCode(code).map { token =>
      Ok(token).withSession("access_token" -> token)
    }
  }

  def getUserInfo() = Action.async {implicit request =>
    val token = request.session.get("access_token").map {token => token}.getOrElse("")
    authService.getUserInfo(token).map { resp =>
      Ok(resp)
    }
  }
}
