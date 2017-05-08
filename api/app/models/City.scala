package models

import play.api.libs.json._

case class GoogleCity (
    description: String
)

object GoogleCity {
    implicit val city = Json.format[GoogleCity]
}
