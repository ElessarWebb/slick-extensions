package aj.slick

trait Db { self: Profile =>
  import profile.simple._

  val db: Database
}
