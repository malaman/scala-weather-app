package controllers

import play.api.mvc._

import scala.concurrent._
import javax.inject.{Inject, Singleton}

import play.api.{Environment, Mode, Configuration}
import services.UserService

@Singleton
class UserController @Inject()(
                                 cc: ControllerComponents,
                                 userService: UserService,
                                 env: Environment,
                                 configuration: Configuration
                               )(implicit ec: ExecutionContext) extends AbstractController(cc) {

  def authenticate() = Action { implicit request =>
    Redirect(s"https://github.com/login/oauth/authorize?scope=user:email&client_id=${userService.GITHUB_CLIENT_ID}&redirect_uri=${userService.PROD_HOST}/api/callback&response_type=code")
  }

  def callback() = Action.async { implicit request =>
    val code = request.getQueryString("code").mkString
    userService.postCode(code).map {token =>
      Redirect(userService.HOST).withSession("access_token" -> token)
    }
  }

  def getUserInfo() = Action.async { implicit request =>
    val tokenOption: Option[String] = request.session.get("access_token").map {token => token}
    if (tokenOption.isDefined) {
      val token = tokenOption.get
      userService.getUserInfo(token).map { resp =>
        Ok(resp).withSession("access_token" -> token)
      }
    } else {
      Future(Ok("{}"))
    }
  }

  def logout() = Action {implicit request =>
    Redirect(userService.HOST).withNewSession
  }

  def upsertCityForUser() = Action.async { implicit request =>
    val json  = request.body.asJson
    userService.upsertCityForUser(json).map {resp =>
      Ok(s"{resp: $resp}")
    }
  }

  def deleteCityForUser() = Action.async { implicit request =>
    val json  = request.body.asJson
    userService.deleteCityForUser(json).map {resp =>
      Ok(s"{resp: $resp}")
    }
  }
}
