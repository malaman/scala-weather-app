package models

import play.api.libs.json._

case class StructuredFormatting (main_text: String, secondary_text: String)

object StructuredFormatting {
    implicit val structored_formatting = Json.format[StructuredFormatting]
}

case class GoogleCity (
    description: String,
    structured_formatting: StructuredFormatting
)

object GoogleCity {
    implicit val city = Json.format[GoogleCity]
}
