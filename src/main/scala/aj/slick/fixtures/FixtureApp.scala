package aj.slick.fixtures

import aj.slick._

/**
 * App boilerplate for installing fixtures.
 * Installs all per default; what to be installed can be selected using cmd line arguments.
 *
 * Passed arguments are matched against fixture class names ('contains').
 */
trait FixtureApp { self: App with Profile with Db with Fixtures =>

  import profile.simple._

  val fixtures: Seq[Fixture[_]]

  def main() = {
    println("# Available fixtures:")
    fixtures.foreach(f => println(s"+ ${f.getClass.getName}"))
    println()

    if (args.length > 0) {
      if(args.head == "--all") {
        installAll()
      } else {
        args.foreach {
          install
        }
      }
    }
  }

  def install(x: String): Unit = fixtures.filter(_.getClass.getName.contains(x)).foreach(f => {
    println(s"Installing fixture: $f")
    install(f)
  })

  def install(f: Fixture[_]): Unit = db.withSession { implicit session => f.install() }

  def installAll() = fixtures.foreach(install)
}
