package dao

import java.util.Date
import javax.inject.{Inject, Singleton}

import play.api.db.slick.DatabaseConfigProvider
import slick.jdbc.JdbcProfile
import models.UserCitySlick

import scala.concurrent.ExecutionContext

@Singleton
class UserCityDAO @Inject() (
                              dbConfigProvider: DatabaseConfigProvider,
                              userDAO: GithubUserDAO,
                              cityDAO: OpenWeatherCityDAO
                            )(implicit ec: ExecutionContext) {
  val dbConfig = dbConfigProvider.get[JdbcProfile]

  import dbConfig._
  import profile.api._

  class UserCityTable(tag: Tag) extends Table[UserCitySlick](tag, "users_cities") {
    implicit val dateColumnType = MappedColumnType.base[Date, Long](d => d.getTime, d => new Date(d))

    def userId = column[Int]("USER_ID")
    def cityId = column[Int]("CITY_ID")
    def created = column[Date]("CREATED")
    def updated = column[Option[Date]]("UPDATED")

    def * = (userId, cityId, created, updated) <> ((UserCitySlick.apply _).tupled, UserCitySlick.unapply)
    def pk = primaryKey("primaryKey", (userId, cityId))

    private def userFK = foreignKey("FK_USERS", userId, TableQuery[userDAO.GithubUserTable])(_.id, onDelete=ForeignKeyAction.Cascade)
    private def cityFK = foreignKey("FK_CITIES", cityId, TableQuery[cityDAO.OpenWeatherCityTable])(_.id, onDelete=ForeignKeyAction.Cascade)
  }
}
