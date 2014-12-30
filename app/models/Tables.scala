package models
// AUTO-GENERATED Slick data model
/** Stand-alone Slick data model for immediate use */
object Tables extends {
  val profile = scala.slick.driver.PostgresDriver
} with Tables

/** Slick data model trait for extension, choice of backend or usage in the cake pattern. (Make sure to initialize this late.) */
trait Tables {
  val profile: scala.slick.driver.JdbcProfile
  import profile.simple._
  import scala.slick.model.ForeignKeyAction
  // NOTE: GetResult mappers for plain SQL are only generated for tables where Slick knows how to map the types of all columns.
  import scala.slick.jdbc.{GetResult => GR}
  
  /** DDL for all tables. Call .create to execute. */
  lazy val ddl = Feeds.ddl
  
  /** Entity class storing rows of table Feeds
   *  @param id Database column id DBType(int4), PrimaryKey
   *  @param name Database column name DBType(varchar), Length(100,true), Default(None)
   *  @param url Database column url DBType(varchar), Length(500,true), Default(None) */
  case class FeedsRow(id: Int, name: Option[String] = None, url: Option[String] = None)
  /** GetResult implicit for fetching FeedsRow objects using plain SQL queries */
  implicit def GetResultFeedsRow(implicit e0: GR[Int], e1: GR[Option[String]]): GR[FeedsRow] = GR{
    prs => import prs._
    FeedsRow.tupled((<<[Int], <<?[String], <<?[String]))
  }
  /** Table description of table feeds. Objects of this class serve as prototypes for rows in queries. */
  class Feeds(_tableTag: Tag) extends Table[FeedsRow](_tableTag, "feeds") {
    def * = (id, name, url) <> (FeedsRow.tupled, FeedsRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = (id.?, name, url).shaped.<>({r=>import r._; _1.map(_=> FeedsRow.tupled((_1.get, _2, _3)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))
    
    /** Database column id DBType(int4), PrimaryKey */
    val id: Column[Int] = column[Int]("id", O.PrimaryKey)
    /** Database column name DBType(varchar), Length(100,true), Default(None) */
    val name: Column[Option[String]] = column[Option[String]]("name", O.Length(100,varying=true), O.Default(None))
    /** Database column url DBType(varchar), Length(500,true), Default(None) */
    val url: Column[Option[String]] = column[Option[String]]("url", O.Length(500,varying=true), O.Default(None))
  }
  /** Collection-like TableQuery object for table Feeds */
  lazy val Feeds = new TableQuery(tag => new Feeds(tag))
}