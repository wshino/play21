import play.api._
import models._
import mvc.{RequestHeader, Result}

import scala.slick.driver.MySQLDriver.simple._
import Database.threadLocalSession

import models._
import java.sql.{Date, Time, Timestamp}

object Global extends GlobalSettings {

  override def onStart(app: Application) {
    Logger.info("Application has started")

    Database.forURL("jdbc:mysql://localhost/myblog", driver = "com.mysql.jdbc.Driver", user = "root", password = "passwd") withSession {

      Instruments.ddl.drop
      Instruments.ddl.create
    }

    val ins = Instrument(0, "hoge")
//    Instruments.insert(ins)


  }

  override def onStop(app: Application) {
    Logger.info("Application shutdown...")
  }

  //  // スラッシュをつけてもリクエストできるようにする
  //  def onHandlerNotFound(request: RequestHeader): Result = {
  //    if (request.path.endsWith("/")) {
  //      MovedPermanently(request.path.take(request.path.length - 1))
  //    } else {
  //      super.onHandlerNotFound(request)
  //    }
  //  }
}