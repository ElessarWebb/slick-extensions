package aj.slick.test

import org.scalatest._
import scala.slick.driver._
import aj.slick.Profile
import aj.slick.tables._

class WithIdSpec extends FlatSpec with Matchers with Profile with TableWithId {

  val profile = H2Driver
  import profile.simple._

  lazy val db = Database.forURL("jdbc:h2:mem:test", "", "", null, "org.h2.Driver")

  import implicits._

  class M(tag: Tag) extends Table[(Int, String)](tag, "m") with HasId[Int] {
    def id = column[Int]("id", O.AutoInc, O.PrimaryKey)
    def name = column[String]("name")

    def * = (id, name)
  }

  val query = TableQuery[M]

  def withSession(test: Session => Unit) = {
    db.withSession { implicit s =>
      // create fresh db
      query.ddl.create

      test(s)
    }
  }

  "table with id" should "support filterById" in withSession { implicit s =>
    val f = query.filterById(1).firstOption
    f should be (None)
  }
}
