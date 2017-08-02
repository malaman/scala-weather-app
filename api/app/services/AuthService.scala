package services

import javax.inject.{Inject, Singleton}
import scala.concurrent._
import scala.util.Properties

import play.api.libs.json.Json
import play.api.libs.ws._
import play.api.libs.json.JsValue

import models.GithubUser

import com.netaporter.uri.Uri.parse

@Singleton
class AuthService (ws: WSClient, baseUrl: String, baseAPIUrl: String)(implicit ec: ExecutionContext) {
  @Inject() def this (ws: WSClient, ec: ExecutionContext) = this(ws, "https://github.com", "https://api.github.com")(ec)

  val HOST: String = Properties.envOrElse("HOST", "http://localhost:8080")
  val GITHUB_CLIENT_ID: String = Properties.envOrElse("GITHUB_CLIENT_ID", "GITHUB_CLIENT_ID")
  val GITHUB_CLIENT_SECRET: String = Properties.envOrElse("GITHUB_CLIENT_SECRET", "GITHUB_CLIENT_ID")
  case class AuthResponse(user: JsValue, token: String)

  def postCode(code: String): Future[String] = {
    val url = s"$baseUrl/login/oauth/access_token"
    val data = Json.obj(
      "client_id" -> GITHUB_CLIENT_ID,
      "client_secret" -> GITHUB_CLIENT_SECRET,
      "accept" -> "json",
      "code" -> code
    )
    ws.url(url).post(data).map(resp => {
      // resp example: access_token=12345551233123&scope=user%3Aemail&token_type=bearer
      val result: Seq[(String, Option[String])] = parse(s"?${resp.body}").query.params
      if (result.nonEmpty && result.head._1 == "access_token") {
        result.head._2.getOrElse("")
      } else {
        ""
      }
    })
  }

  def getUserInfo(token: String): Future[JsValue] = {
    val url = s"$baseAPIUrl/user?access_token=$token"
    ws.url(url).get().map(response => {
      val body = Json.parse(response.body)
      val jsresp = body.validate[GithubUser]
      jsresp.fold(
        err => Json.obj("error" -> err.toString()),
        user => Json.toJson(user)
      )
    })
  }

  def authenticate(token: Option[String], code: String): Future[AuthResponse] = {
    if (token.isDefined) {
      return getUserInfo(token.get).map {resp => AuthResponse(resp, token.get)}
    }
    return postCode(code).flatMap { token =>
      getUserInfo(token).map {resp => AuthResponse(resp, token)}
    }
  }
}
