package models

import play.api.libs.json._

case class GithubUser(
                       login: String = "",
                       id: Int = -1,
                       avatar_url: String = "",
                       html_url: String = "",
                       name: Option[String] = None
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


case class UserResponse (
                          user: GithubUser,
                          cities: Seq[OpenWeatherBaseCity]
                        )

object UserResponse {
  implicit val f = Json.format[UserResponse]
}

case class CityForUser (
                       city: OpenWeatherBaseCity,
                       userId: Int
                       )

object CityForUser {
  implicit val f = Json.format[CityForUser]
}
