package models

import scala.slick.driver.MySQLDriver.simple._
import scala.language.postfixOps
import java.sql.{DatabaseMetaData, Connection}

// database withSession みたいな書き方するときに必要

import slick.session.{DatabaseCapabilities, Session}

import play.api.db.DB
import play.api.Play.current

case class Instrument(id: Long, name: String)

object Instruments extends Table[Instrument]("instruments") {

  lazy val database = Database.forDataSource(DB.getDataSource())

  def id = column[Long]("id", O PrimaryKey, O AutoInc, O NotNull)

  def name = column[String]("name", O DBType "varchar(10)")

  def * = id ~ name <>(Instrument.apply _, Instrument.unapply _)

  def ins = name returning id

  def find(id: Long) = database.withSession {
    implicit session: Session =>
      Query(Instruments).where(_.id === id).firstOption
  }

  def findAll() = database.withSession {
    implicit session: Session =>
      Query(Instruments).sortBy(_.id).list()
  }

  def insert(instrument: Instrument) = database.withSession {
    implicit session: Session =>
      Instruments.ins.insert(instrument.name)
  }

  def delete(id: Long) = database.withSession {
    implicit session: Session =>
      Instruments.where(_.id === id).delete
  }

  def update(instrument: Instrument) = database.withSession {
    implicit session: Session =>
      Instruments.where(_.id === instrument.id).update(instrument)
  }

}
