package aj.slick.utils

import aj.slick.Profile
import com.github.tototoshi.slick.GenericJodaSupport

trait JodaSupport { self: Profile =>
  object jodamappers extends GenericJodaSupport(profile.asInstanceOf[JdbcDriver])
}
