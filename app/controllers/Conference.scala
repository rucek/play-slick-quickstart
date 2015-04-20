package controllers

import db.RoomsDao
import models.Room
import play.api.libs.json.{JsError, Json}
import play.api.mvc.{Action, Controller}

object Conference extends Controller {

  val roomsDao = new RoomsDao

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
}
