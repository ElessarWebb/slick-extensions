package aj.slick.fixtures

import aj.slick._

trait Fixtures { this: Profile =>

  import profile.simple._

  abstract class Fixture[U](val query: TableQuery[_ <: Table[U]], val objects: U*) {
    def install()(implicit session: Session) = query.insertAll(objects : _*)
  }
}
