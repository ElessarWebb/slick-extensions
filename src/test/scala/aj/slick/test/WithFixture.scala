package aj.slick.test

trait WithFixture { self: Profile =>
  import profile.simple._

  def withFixture(u: Fixture)(implicit s: Session): Session = u.install(); s
}
