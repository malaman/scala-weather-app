package dao

import java.util.Date
import javax.inject.{ Inject, Singleton }

import play.api.db.slick.DatabaseConfigProvider
import slick.jdbc.JdbcProfile

import models.GithubUserSlick

import scala.concurrent.{ Future, ExecutionContext }

@Singleton
class GithubUserDAO  @Inject() (dbConfigProvider: DatabaseConfigProvider)(implicit ec: ExecutionContext) {
  private val dbConfig = dbConfigProvider.get[JdbcProfile]
  import dbConfig._
  import profile.api._

  private class GithubUserTable(tag: Tag) extends Table[GithubUserSlick](tag, "users") {
    implicit val dateColumnType = MappedColumnType.base[Date, Long](d => d.getTime, d => new Date(d))

    def id    = column[Int]("ID", O.PrimaryKey)
    def login = column[String]("LOGIN")
    def created = column[Option[Date]]("CREATED")
    def updated = column[Option[Date]]("UPDATED")


    def * = (id, login, created, updated) <> ((GithubUserSlick.apply _).tupled, GithubUserSlick.unapply)
  }
}
