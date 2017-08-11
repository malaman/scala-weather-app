package models

import play.api.libs.json._
import java.util.Date

case class GithubUser(
                       login: String,
                       id: Int,
                       avatar_url: String,
                       html_url: String,
                       name: String
               )

object GithubUser {
  implicit val f = Json.format[GithubUser]
}


case class GithubToken(
                        access_token: String
                      )

object GithubToken {
  implicit val f = Json.format[GithubToken]
}
