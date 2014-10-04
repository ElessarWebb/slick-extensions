package aj.slick.persist

import aj.slick.Profile
import aj.slick.tables.TableWithId
import aj.persist.{Persist, Identify}

trait SlickPersistance extends TableWithId { self: Profile =>

  import profile.simple._

  abstract class SlickPersist[I : BaseColumnType, M]
    (query: TableQuery[_ <: Table[M] with HasId[I]])
    (implicit ident: Identify[M, I])
    extends Persist[I,M,Session] {

    type S = Session

    def create(m: M)(s: Session) = {
      query
        .returning(query.map(_.id))
        .insert(m)(s)
    }

    def write(id: I, m: M)(s: Session) = {
      query
        .filter(_.id === id)
        .update(m)(s)

      // return the id
      id
    }

    def delete(id: I)(s: Session): Int = query.filter(_.id === id).delete(s)

    override def read(id: I)(s: Session): Option[M] = query.filter(_.id === id).firstOption(s)

    def readAll(s: Session): List[M] = query.list()(s)
  }

}
