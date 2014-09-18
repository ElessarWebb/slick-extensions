package aj.slick

import scala.slick.driver.JdbcProfile

trait Profile {
  val profile: JdbcProfile
}
