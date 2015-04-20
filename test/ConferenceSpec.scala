import db.RoomsDao
import org.specs2.mutable._
import play.api.libs.json.{JsNumber, Json}
import play.api.libs.ws.WS
import play.api.test.Helpers._
import play.api.test._

class ConferenceSpec extends Specification {

  val fakeApplication =
    FakeApplication(additionalConfiguration = inMemoryDatabase())

  val roomsDao = new RoomsDao

  "Conference" should {

    "allow to add a room" in new WithServer(app = fakeApplication) {
      // given
      val endpoint = WS.url(s"http://localhost:$testServerPort/rooms")
      val data = Json.obj("name" -> "testRoom")

      // when
      val response = Helpers.await(endpoint.post(data))
      val rooms = roomsDao.findAll

      // then
      response.status must equalTo(CREATED)
      Json.parse(response.body) \ "id" must beAnInstanceOf[JsNumber]

      rooms must haveSize(1)
      rooms.head.name must equalTo("testRoom")
    }
  }
}
