package dao

import java.util.Date
import javax.inject.{Inject, Singleton}

import play.api.db.slick.DatabaseConfigProvider
import slick.jdbc.JdbcProfile
import models.{CityForUser, OpenWeatherCitySlick, UserCitySlick}

import scala.concurrent.{ExecutionContext, Future}

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

  private val UsersCities = TableQuery[UserCityTable]

  def setup(): Future[Unit] = db.run(UsersCities.schema.create)

  def get(userId: Int, cityId: Int): Future[Option[UserCitySlick]] = {
    val query = UsersCities.filter(userCity => userCity.userId === userId && userCity.cityId === cityId)
    db.run(query.result).map(usersCities => usersCities.headOption)
  }

  def getCitiesForUser(userId: Int): Future[Seq[OpenWeatherCitySlick]] = {
    val query = for {
      ((user, userCity), city) <- userDAO.Users join UsersCities on ((user, userCity) => (user.id === userCity.userId) && (user.id === userId) ) join cityDAO.Cities on (_._2.cityId === _.id)
    } yield city
    db.run(query.result)
  }

  def upsert(userId: Int, cityId: Int): Future[Int] = {
    get(userId, cityId).flatMap(userCityOption => {
      if (userCityOption.isEmpty) {
        val userCity = UserCitySlick(userId, cityId, new Date())
        db.run(UsersCities.insertOrUpdate(userCity))
          .recover { case ex: Throwable =>
            println("Error occured when updating user_city table", ex)
            -1
          }
      } else {
        Future(1)
      }
    })
  }

  def upsertCityForUser(cityForUser: CityForUser): Future[Int] = {
    val city = cityForUser.city
    val cityToUpsert = OpenWeatherCitySlick(id=city.id, name=city.name, lon=city.lon, lat=city.lat, created = new Date())
    for {
      _ <- cityDAO.upsert(cityToUpsert)
      result <- upsert(cityForUser.userId, city.id)
    } yield result
  }

  def deleteCityForUser(cityForUser: CityForUser): Future[Int] = {
    val cityId = cityForUser.city.id
    val userId = cityForUser.userId
    val query = UsersCities.filter(userCity => userCity.cityId === cityId && userCity.userId === userId).delete
    db.run(query)
      .recover { case ex: Throwable =>
        println("Error occured when updating user_city table", ex)
        -1
      }
  }
}
