package controllers

import play.api.mvc._
import models.Instruments
import play.api.libs.json._
import models.Instrument

object Application extends Controller {

  def display = TODO

  def select(id: Long) = TODO

//    implicit lazy val instrumentReads = Json.reads[Instrument]
//    implicit lazy val instrumentWrites = Json.writes[Instrument]
  //
  //
    def index = Action {
      Ok(views.html.index("Your new application is ready."))
    }

    def insert = Action {
      val ins = Instrument(0, "hoge")
      Instruments.insert(ins)
      Ok("ok")
    }

//    def display = Action {
//      implicit request =>
//        val res = Instruments.findAll()
//        request.getQueryString("callback") match {
//          case None =>
//            Ok(Json.toJson(res))
//          case _ =>
//            Ok(request.getQueryString("callback").head + "({" + Json.toJson(res) + "});").as("text/javascript")
//        }
//    }
  //
  //  def select(id: Long) = Action {
  //    val res = Instruments.find(id)
  //    res match {
  //      case None => Ok("None")
  //      case _ => Ok(Json.toJson(res))
  //    }
  //
  //  }
}