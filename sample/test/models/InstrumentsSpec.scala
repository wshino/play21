package models

import org.specs2.mutable._
import org.specs2.specification._

import play.api.test._
import play.api.test.Helpers._

import scala.slick.driver.MySQLDriver.simple._

import play.api.db.DB
import play.api.Play.current

import slick.session.Session

class InstrumentsSpec extends Specification with BeforeExample with AfterExample {

  def before = {
    running(FakeApplication()) {
      Database.forDataSource(DB.getDataSource()).withSession {
        implicit session: Session =>
          Instruments.ddl.drop
          Instruments.ddl.create

      }
    }
  }

  def after = {
    println("after")
  }


  "Instruments" should {

    "insert" in {
      running(FakeApplication()) {
        val ins = Instrument(0, "hoge")
        Instruments.insert(ins)
        val instruments = Instruments.findAll()
        instruments.size mustEqual 1
      }
    }

    "select" in {
      running(FakeApplication()) {

        val instruments = Instruments.findAll()
        true mustEqual (true)
      }
    }

  }

}
