package aj.persist

/**
 * Abstract class for things that can be persisted.
 *
 * It assumes some kind of mutable session is required for every operation on the datastore.
 * Which needs to be injected on every operation.
 */
abstract class Persist[I,M,S](implicit ident: Identify[M, I]) {

  /**
   * Save a new instance of m, returning the new identifier
   */
  def create(m: M)(s: S): I

  /**
   * Abstract write that differentiates between create and update
   * based on whether or not the instance `m` has an identifier
   */
  def write(m: M)(s: S): I = ident.identity(m) match {
    case Some(id) => write(id, m)(s)
    case None     => create(m)(s)
  }

  /**
   * Update the instance with identifier `id` with instance `m`
   */
  def write(id: I, m: M)(s: S): I

  /**
   * Delete the instance `m`
   */
  def delete(m: M)(s: S): Option[Int] = ident.identity(m).map(delete(_)(s))

  /**
   * Delete the instance with identifier `i`
   */
  def delete(i: I)(s: S): Int

  /**
   * read the instance with identifier `i`
   */
  def read(i: I)(s: S): Option[M] = readAll(s).filter(identity(_) == i).headOption

  /**
   * Read a list of all instances from the datastore
   */
  def readAll(s: S): List[M]
}
