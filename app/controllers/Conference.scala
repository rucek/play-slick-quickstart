package controllers

import db.{TalksDao, RoomsDao}
import models.{TalkWithRoom, Talk, Room}
import play.api.libs.json.{JsError, Json}
import play.api.mvc.{Action, Controller}

object Conference extends Controller {

  val roomsDao = new RoomsDao
  val talksDao = new TalksDao

  implicit val roomFormat = Json.format[Room]
  implicit val talkReads = Json.reads[Talk]
  implicit val talkWithRoomWrites = Json.writes[TalkWithRoom]

  def getRooms = Action {
    Ok(Json.toJson(roomsDao.findAll))
  }

  def addRoom = Action(parse.json) { request =>
    request.body.validate[Room].fold(
      errors => BadRequest(JsError.toFlatJson(errors)),
      room => {
        val id = roomsDao.create(room)
        Created(Json.obj("id" -> id))
      }
    )
  }

  def getTalks = Action {
    Ok(Json.toJson(talksDao.findAllWithRooms))
  }

  def addTalk = Action(parse.json) { request =>
    request.body.validate[Talk].fold(
      errors => BadRequest(JsError.toFlatJson(errors)),
      talk => {
        val id = talksDao.create(talk)
        Created(Json.obj("id" -> id))
      }
    )
  }
}
