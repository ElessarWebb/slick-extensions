package aj.slick.test

import aj.slick._
import scala.slick.driver.H2Driver

trait TestProfile extends Profile with Db {
  val profile = H2Driver
  import profile.simple._

  lazy val db = Database.forURL("jdbc:h2:mem:test", "", "", null, "org.h2.Driver")
}
