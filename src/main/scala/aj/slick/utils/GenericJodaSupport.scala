package aj.slick.utils

import aj.slick.Profile
import com.github.tototoshi.slick.GenericJodaSupport
import scala.slick.driver.JdbcDriver

trait JodaSupport { self: Profile =>
  object jodamappers extends GenericJodaSupport(profile.asInstanceOf[JdbcDriver])
}
