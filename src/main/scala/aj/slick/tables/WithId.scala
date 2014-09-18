package aj.slick.tables

import aj.slick.Profile

trait TableWithId { self: Profile =>

  import profile.simple._

  object implicits extends HasIdImplicits

  trait HasId[Id] { self: Table[_] =>
    def id: Column[Id]
  }

  trait HasIdImplicits {
    implicit class HasIdQueryExt[Id: BaseColumnType, U]
    (query: TableQuery[_ <: Table[U] with HasId[Id]]) {
      def filterById(id: Id)(implicit s: Session) = query.filter(_.id === id)
      def insertReturnId(m: U)(implicit s: Session) = query.returning(query.map(_.id)) += m
    }
  }
}
