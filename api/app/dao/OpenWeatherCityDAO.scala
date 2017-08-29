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
  val Cities = TableQuery[OpenWeatherCityTable]

  def setup(): Future[Unit] = db.run(Cities.schema.create)

  def get(id: Int): Future[Option[OpenWeatherCitySlick]] = {
    val query = Cities.filter(_.id === id)
    db.run(query.result).map(cities => cities.headOption)
  }

  def upsert(cityToAdd: OpenWeatherCitySlick): Future[Int] = {
    get(cityToAdd.id).flatMap(cityOption => {
      val city = cityOption.getOrElse(cityToAdd).copy(updated = Some(new Date()))
      db.run(Cities.insertOrUpdate(city))
        .recover { case ex: Throwable =>
          println("Error occured when updating city", ex)
          -1
        }
    })
  }

}
