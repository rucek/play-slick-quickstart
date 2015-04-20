package db

import models.Room
import play.api.Play.current // source of the implicit session
import play.api.db.slick.Config.driver.simple._
import play.api.db.slick._

class RoomsDao extends DatabaseSchema {

  def findAll = DB.withSession { implicit session =>
    rooms.list
  }

  def create(room: Room) = DB.withTransaction { implicit session =>
    (rooms returning rooms.map(_.id)) += room
  }

  def test(roomId: Int) = DB.withTransaction { implicit session =>
    rooms.filter(_.id === roomId).delete
  }
}

