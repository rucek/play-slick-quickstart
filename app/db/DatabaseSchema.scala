package db

import models.{Talk, Room}
import play.api.db.slick.Config.driver.simple._

class RoomsTable(tag: Tag) extends Table[Room](tag, "rooms") {

  def id = column[Int]("id", O.PrimaryKey, O.AutoInc)

  def name = column[String]("name", O.NotNull)

  def * = (id.?, name) <>(Room.tupled, Room.unapply)
}

class TalksTable(tag: Tag) extends Table[Talk](tag, "talks") {

  def id = column[Int]("id", O.PrimaryKey, O.AutoInc)

  def roomId = column[Int]("room_id", O.NotNull)

  def title = column[String]("title", O.NotNull)

  def room = foreignKey("fk_room_id", roomId, TableQuery[RoomsTable])(_.id, onDelete = ForeignKeyAction.Cascade)

  def * = (id.?, roomId, title) <>(Talk.tupled, Talk.unapply)

}

trait DatabaseSchema {

  val rooms = TableQuery[RoomsTable]

  val talks = TableQuery[TalksTable]
}
