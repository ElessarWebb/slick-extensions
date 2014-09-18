package aj.slick.test

trait TestProfile extends Profile with Db {
  val profile = H2Driver
  import profile.simple._

  lazy val db = Database.forURL("jdbc:h2:mem:test", "", "", null, "org.h2.Driver")
}
