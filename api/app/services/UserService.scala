package services

import javax.inject.{Inject, Singleton}

import scala.concurrent._
import scala.util.Properties
import play.api.libs.json.Json
import play.api.libs.ws._
import play.api.libs.json.JsValue
import models._
import play.api.{Configuration, Environment, Mode}
import dao.{GithubUserDAO, UserCityDAO}

@Singleton
class UserService(
                    ws: WSClient,
                    env: Environment,
                    configuration: Configuration,
                    userDAO: GithubUserDAO,
                    userCityDAO: UserCityDAO,
                    baseUrl: String,
                    baseAPIUrl: String
                  )(implicit ec: ExecutionContext) {
  @Inject() def this (
                       ws: WSClient,
                       ec: ExecutionContext,
                       env: Environment,
                       configuration: Configuration,
                       userDAO: GithubUserDAO,
                       userCityDAO: UserCityDAO
                     ) =
    this(ws, env, configuration, userDAO, userCityDAO, "https://github.com", "https://api.github.com")(ec)

  val HOST: String = if (env.mode == Mode.Prod)
    configuration.underlying.getString("variables.prod_host")
    else configuration.underlying.getString("variables.dev_host")
  val PROD_HOST: String = configuration.underlying.getString("variables.prod_host")

  val GITHUB_CLIENT_ID: String = Properties.envOrElse("GITHUB_CLIENT_ID", "GITHUB_CLIENT_ID")
  val GITHUB_CLIENT_SECRET: String = Properties.envOrElse("GITHUB_CLIENT_SECRET", "GITHUB_CLIENT_ID")

  case class AuthResponse(user: JsValue, token: String)

  def postCode(code: String): Future[String] = {
    val url = s"$baseUrl/login/oauth/access_token"
    val data = Json.obj(
      "client_id" -> GITHUB_CLIENT_ID,
      "client_secret" -> GITHUB_CLIENT_SECRET,
      "code" -> code
    )
    ws.url(url)
        .withHttpHeaders("Accept" -> "application/json")
        .post(data).map { response =>
          val body = Json.parse(response.body)
          val jsresp = body.validate[GithubToken]
          jsresp.fold(
            err => "",
            token => token.access_token
          )
    }
  }

  private def getInfoFromGithub(url: String): Future[Option[GithubUser]] = ws.url(url).get()
    .map { response =>
      val body = Json.parse(response.body)
      val jsresp = body.validate[GithubUser]
      jsresp.fold(
        err => {
          println("Error: ", err)
          None
        },
        user => Some(user))
    }

  def getUserInfo(token: String): Future[JsValue] = {
    val url = s"$baseAPIUrl/user?access_token=$token"
    for {
      userOption <- getInfoFromGithub(url)
      _ <- userDAO.upsert(userOption)
      cities <- {
        val user = userOption.fold(GithubUser())(user => user)
        userCityDAO.getCitiesForUser(user.id)
      }
    } yield {
      val baseCities = cities.map(city => OpenWeatherBaseCity(city.id, city.name, city.lon, city.lat))
      if (userOption.isDefined) Json.toJson(UserResponse(userOption.get, baseCities)) else Json.obj("error" -> "getUserInfo error")
    }
  }
  def upsertCityForUser(jsValueOption: Option[JsValue]): Future[Int] = {
    if (jsValueOption.isDefined) {
      val cityForUser = jsValueOption.get.as[CityForUser]
      return userCityDAO.upsertCityForUser(cityForUser)
    }
    Future(-1)
  }

  def deleteCityForUser(jsValueOption: Option[JsValue]): Future[Int] = {
    if (jsValueOption.isDefined) {
      val cityForUser = jsValueOption.get.as[CityForUser]
      return userCityDAO.deleteCityForUser(cityForUser)
    }
    Future(-1)
  }

}
