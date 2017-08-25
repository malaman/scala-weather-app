package dao

import java.util.Date
import javax.inject.{ Inject, Singleton }

import play.api.db.slick.DatabaseConfigProvider
import slick.jdbc.JdbcProfile

import models.{GithubUserSlick, GithubUser}

import scala.concurrent.{ Future, ExecutionContext }

@Singleton
class GithubUserDAO  @Inject() (dbConfigProvider: DatabaseConfigProvider)(implicit ec: ExecutionContext) {
  val dbConfig = dbConfigProvider.get[JdbcProfile]
  import dbConfig._
  import profile.api._

  class GithubUserTable(tag: Tag) extends Table[GithubUserSlick](tag, "users") {
    implicit val myDateColumnType = MappedColumnType.base[Date, Long](
      dt => dt.getTime,
      ts => new Date(ts)
    )

    def id    = column[Int]("ID", O.PrimaryKey)
    def created = column[Date]("CREATED")
    def updated = column[Option[Date]]("UPDATED")
    def lastLogin = column[Option[Date]]("LAST_LOGIN")

    def * = (id, created, updated, lastLogin) <> (GithubUserSlick.tupled, GithubUserSlick.unapply)
  }

  val Users = TableQuery[GithubUserTable]

  def create(user: GithubUser): Future[Unit] = {
    val newUser = GithubUserSlick(id = user.id, created = new Date())
    db.run(Users += newUser)
      .map { _ => () }
      .recover { case ex: Throwable => println("Error occured when inserting user", ex)}
  }

  def update(user: GithubUserSlick): Future[Int] = {
    val query = Users.filter(_.id === user.id).update(user)
    db.run(query)
  }

  def get(id: Int): Future[GithubUserSlick] = {
    val query = Users.filter(_.id === id)
    db.run(query.result).map(users => users.head)
  }


  def saveLoggedInTime(userId: Int): Future[Int] = {
    implicit val myDateColumnType = MappedColumnType.base[Date, Long](dt => dt.getTime, ts => new Date(ts))
    val query = Users.filter(_.id === userId)
      .map(user => user.lastLogin)
      .update(Some(new Date()))
    db.run(query)
  }

  def list(): Future[Seq[GithubUserSlick]] = db.run(Users.result)
}
