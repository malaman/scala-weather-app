package weatherApp.models

case class GithubUser(
                       login: String = "",
                       id: Int = -1,
                       avatar_url: String = "",
                       html_url: String = "",
                       name: Option[String] = None
                     )

case class UserResponse (
                          user: GithubUser,
                          cities: Seq[OpenWeatherBaseCity]
                        )

case class CityForUser (
                         city: OpenWeatherBaseCity,
                         userId: Int
                       )


