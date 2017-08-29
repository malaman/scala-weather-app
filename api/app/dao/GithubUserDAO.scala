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

  def setup(): Future[Unit] = db.run(Users.schema.create)

  def get(id: Int): Future[Option[GithubUserSlick]] = {
    val query = Users.filter(_.id === id)
    db.run(query.result)
      .map(users => users.headOption)
      .recover { case ex: Throwable =>
        println("Error occured when getting a user", ex)
        None
      }
  }

  def list(): Future[Seq[GithubUserSlick]] = db.run(Users.result)

  def update(user: GithubUserSlick): Future[Int] = {
    val query = Users.filter(_.id === user.id).update(user)
    db.run(query)
  }

  def upsert(githubUserOption: Option[GithubUser]): Future[Int] = {
    if (githubUserOption.isDefined) {
      val githubUser = githubUserOption.get
      return get(githubUser.id).flatMap(userOption => {
        val newUser = GithubUserSlick(id = githubUser.id, created = new Date())
        val user = userOption.getOrElse(newUser).copy(lastLogin = Some(new Date()))
        db.run(Users.insertOrUpdate(user))
          .recover { case ex: Throwable =>
            println("Error occured when updating user", ex)
            -1
          }
      })
    }
    Future(-1)
  }

  def saveLoggedInTime(userId: Int): Future[Int] = {
    implicit val myDateColumnType = MappedColumnType.base[Date, Long](dt => dt.getTime, ts => new Date(ts))
    val query = Users.filter(_.id === userId)
      .map(user => user.lastLogin)
      .update(Some(new Date()))
    db.run(query)
  }

}
