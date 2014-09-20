package aj.slick.tables

import aj.slick.Profile

trait TableWithId { self: Profile =>

  import profile.simple._

  trait HasId[Id] { self: Table[_] =>
    def id: Column[Id]
  }

  implicit class HasIdQueryExt[Id: BaseColumnType, U]
  (query: Query[Table[U] with HasId[Id], U]) {
    def filterById(id: Id)(implicit s: Session) = query.filter(_.id === id)
    def insertReturnId(m: U)(implicit s: Session): Id = query.returning(query.map(_.id)) += m
  }
}
