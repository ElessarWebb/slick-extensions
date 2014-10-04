package aj.persist

/**
 * Abstract type class for things that are uniquely identifable by some member
 */
abstract class Identify[M, I] {
  type Model = M
  type Id = I

  def identity(m: M): Option[I]
}

object structuralIdentities {
  type WithIdField[T] = {val id: Option[T]}

  /**
   * View for anything that has an `id` field of type Option[_]
   */
  class IdentifyStructural[I] extends Identify[WithIdField[I], I] {
    def identity(m: Model) = m.id
  }

  implicit object hasLongIdField extends IdentifyStructural[Long]
  implicit object hasIntIdField extends IdentifyStructural[Int]
}
