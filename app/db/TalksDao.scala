package db

import models.{TalkWithRoom, Talk}
import play.api.Play.current
import play.api.db.slick.Config.driver.simple._
import play.api.db.slick._

class TalksDao extends DatabaseSchema {

  def create(talk: Talk) = DB.withTransaction { implicit session =>
    (talks returning talks.map(_.id)) += talk
  }

  def findAllWithRooms: Seq[TalkWithRoom] = DB.withSession { implicit session =>
    val result: Seq[(String, String)] = usingForComprehension.run
    result.map(TalkWithRoom.tupled)
  }

  def usingForComprehension = for {
    t <- talks
    r <- rooms if r.id === t.roomId
  } yield (t.title, r.name)

  def usingJoin = (talks join rooms on (_.roomId === _.id)).map {
    case (t, r) => (t.title, r.name)
  }

  def usingPlainSql = {
    import scala.slick.jdbc.StaticQuery.interpolation

    sql"""
         select "title", "name"
         from "talks"
         join "rooms" on "room_id" = "rooms"."id"
       """.as[(String, String)]
  }
}

