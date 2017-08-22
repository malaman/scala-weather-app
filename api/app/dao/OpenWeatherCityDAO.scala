package dao

import java.util.Date
import javax.inject.{Inject, Singleton}

import play.api.db.slick.DatabaseConfigProvider
import slick.jdbc.JdbcProfile
import models.OpenWeatherCitySlick

import scala.concurrent.{ExecutionContext, Future}

@Singleton
class OpenWeatherCityDAO @Inject() (dbConfigProvider: DatabaseConfigProvider)(implicit ec: ExecutionContext) {
  val dbConfig = dbConfigProvider.get[JdbcProfile]

  import dbConfig._
  import profile.api._

  class OpenWeatherCityTable(tag: Tag) extends Table[OpenWeatherCitySlick](tag, "cities") {
    implicit val dateColumnType = MappedColumnType.base[Date, Long](d => d.getTime, d => new Date(d))

    def id    = column[Int]("ID", O.PrimaryKey)

    def name  = column[String]("NAME")
    def lon   = column[Float]("LON")
    def lat   = column[Float]("LAT")
    def created = column[Date]("CREATED")
    def updated = column[Option[Date]]("UPDATED")

    def * = (id, name, lon, lat, created, updated) <> ((OpenWeatherCitySlick.apply _).tupled, OpenWeatherCitySlick.unapply)
  }
}
