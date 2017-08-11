package models

import java.util.Date

trait BaseTable {
  val created: Option[Date]
  val updated: Option[Date]
}

case class GithubUserSlick (
                             id: Int,
                             login: String,
                             created: Option[Date] = None,
                             updated: Option[Date] = None
                           ) extends BaseTable
