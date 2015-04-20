package models

case class Room(id: Option[Int], name: String)

case class Talk(id: Option[Int], roomId: Int, title: String)

case class TalkWithRoom(talkTitle: String, roomName: String)