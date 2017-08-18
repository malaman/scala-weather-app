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
