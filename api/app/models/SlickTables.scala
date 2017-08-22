package models

import java.util.Date

trait BaseTable {
  val created: Date
  val updated: Option[Date]
}

case class GithubUserSlick (
                             id: Int,
                             created: Date,
                             updated: Option[Date] = None
                           ) extends BaseTable

case class OpenWeatherCitySlick (
                                id: Int,
                                name: String,
                                lon: Float,
                                lat: Float,
                                created: Date,
                                updated: Option[Date] = None
                                ) extends BaseTable

case class UserCitySlick (
                    userId: Int,
                    cityId: Int,
                    created: Date,
                    updated: Option[Date] = None
                    ) extends BaseTable
