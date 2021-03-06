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
  lazy val ddl = Feed.ddl ++ Page.ddl ++ PlayEvolutions.ddl
  
  /** Entity class storing rows of table Feed
   *  @param id Database column id DBType(serial), AutoInc
   *  @param name Database column name DBType(varchar), Length(100,true), Default(None)
   *  @param url Database column url DBType(varchar), Length(500,true), Default(None) */
  case class FeedRow(id: Int, name: Option[String] = None, url: Option[String] = None)
  /** GetResult implicit for fetching FeedRow objects using plain SQL queries */
  implicit def GetResultFeedRow(implicit e0: GR[Int], e1: GR[Option[String]]): GR[FeedRow] = GR{
    prs => import prs._
    FeedRow.tupled((<<[Int], <<?[String], <<?[String]))
  }
  /** Table description of table feed. Objects of this class serve as prototypes for rows in queries. */
  class Feed(_tableTag: Tag) extends Table[FeedRow](_tableTag, "feed") {
    def * = (id, name, url) <> (FeedRow.tupled, FeedRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = (id.?, name, url).shaped.<>({r=>import r._; _1.map(_=> FeedRow.tupled((_1.get, _2, _3)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))
    
    /** Database column id DBType(serial), AutoInc */
    val id: Column[Int] = column[Int]("id", O.AutoInc)
    /** Database column name DBType(varchar), Length(100,true), Default(None) */
    val name: Column[Option[String]] = column[Option[String]]("name", O.Length(100,varying=true), O.Default(None))
    /** Database column url DBType(varchar), Length(500,true), Default(None) */
    val url: Column[Option[String]] = column[Option[String]]("url", O.Length(500,varying=true), O.Default(None))
  }
  /** Collection-like TableQuery object for table Feed */
  lazy val Feed = new TableQuery(tag => new Feed(tag))
  
  /** Entity class storing rows of table Page
   *  @param id Database column id DBType(serial), AutoInc
   *  @param url Database column url DBType(varchar), Length(500,true), Default(None)
   *  @param feedId Database column feed_id DBType(serial), AutoInc
   *  @param read Database column read DBType(bool), Default(None)
   *  @param save Database column save DBType(bool), Default(None) */
  case class PageRow(id: Int, url: Option[String] = None, feedId: Int, read: Option[Boolean] = None, save: Option[Boolean] = None)
  /** GetResult implicit for fetching PageRow objects using plain SQL queries */
  implicit def GetResultPageRow(implicit e0: GR[Int], e1: GR[Option[String]], e2: GR[Option[Boolean]]): GR[PageRow] = GR{
    prs => import prs._
    PageRow.tupled((<<[Int], <<?[String], <<[Int], <<?[Boolean], <<?[Boolean]))
  }
  /** Table description of table page. Objects of this class serve as prototypes for rows in queries. */
  class Page(_tableTag: Tag) extends Table[PageRow](_tableTag, "page") {
    def * = (id, url, feedId, read, save) <> (PageRow.tupled, PageRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = (id.?, url, feedId.?, read, save).shaped.<>({r=>import r._; _1.map(_=> PageRow.tupled((_1.get, _2, _3.get, _4, _5)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))
    
    /** Database column id DBType(serial), AutoInc */
    val id: Column[Int] = column[Int]("id", O.AutoInc)
    /** Database column url DBType(varchar), Length(500,true), Default(None) */
    val url: Column[Option[String]] = column[Option[String]]("url", O.Length(500,varying=true), O.Default(None))
    /** Database column feed_id DBType(serial), AutoInc */
    val feedId: Column[Int] = column[Int]("feed_id", O.AutoInc)
    /** Database column read DBType(bool), Default(None) */
    val read: Column[Option[Boolean]] = column[Option[Boolean]]("read", O.Default(None))
    /** Database column save DBType(bool), Default(None) */
    val save: Column[Option[Boolean]] = column[Option[Boolean]]("save", O.Default(None))
  }
  /** Collection-like TableQuery object for table Page */
  lazy val Page = new TableQuery(tag => new Page(tag))
  
  /** Entity class storing rows of table PlayEvolutions
   *  @param id Database column id DBType(int4), PrimaryKey
   *  @param hash Database column hash DBType(varchar), Length(255,true)
   *  @param appliedAt Database column applied_at DBType(timestamp)
   *  @param applyScript Database column apply_script DBType(text), Length(2147483647,true), Default(None)
   *  @param revertScript Database column revert_script DBType(text), Length(2147483647,true), Default(None)
   *  @param state Database column state DBType(varchar), Length(255,true), Default(None)
   *  @param lastProblem Database column last_problem DBType(text), Length(2147483647,true), Default(None) */
  case class PlayEvolutionsRow(id: Int, hash: String, appliedAt: java.sql.Timestamp, applyScript: Option[String] = None, revertScript: Option[String] = None, state: Option[String] = None, lastProblem: Option[String] = None)
  /** GetResult implicit for fetching PlayEvolutionsRow objects using plain SQL queries */
  implicit def GetResultPlayEvolutionsRow(implicit e0: GR[Int], e1: GR[String], e2: GR[java.sql.Timestamp], e3: GR[Option[String]]): GR[PlayEvolutionsRow] = GR{
    prs => import prs._
    PlayEvolutionsRow.tupled((<<[Int], <<[String], <<[java.sql.Timestamp], <<?[String], <<?[String], <<?[String], <<?[String]))
  }
  /** Table description of table play_evolutions. Objects of this class serve as prototypes for rows in queries. */
  class PlayEvolutions(_tableTag: Tag) extends Table[PlayEvolutionsRow](_tableTag, "play_evolutions") {
    def * = (id, hash, appliedAt, applyScript, revertScript, state, lastProblem) <> (PlayEvolutionsRow.tupled, PlayEvolutionsRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = (id.?, hash.?, appliedAt.?, applyScript, revertScript, state, lastProblem).shaped.<>({r=>import r._; _1.map(_=> PlayEvolutionsRow.tupled((_1.get, _2.get, _3.get, _4, _5, _6, _7)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))
    
    /** Database column id DBType(int4), PrimaryKey */
    val id: Column[Int] = column[Int]("id", O.PrimaryKey)
    /** Database column hash DBType(varchar), Length(255,true) */
    val hash: Column[String] = column[String]("hash", O.Length(255,varying=true))
    /** Database column applied_at DBType(timestamp) */
    val appliedAt: Column[java.sql.Timestamp] = column[java.sql.Timestamp]("applied_at")
    /** Database column apply_script DBType(text), Length(2147483647,true), Default(None) */
    val applyScript: Column[Option[String]] = column[Option[String]]("apply_script", O.Length(2147483647,varying=true), O.Default(None))
    /** Database column revert_script DBType(text), Length(2147483647,true), Default(None) */
    val revertScript: Column[Option[String]] = column[Option[String]]("revert_script", O.Length(2147483647,varying=true), O.Default(None))
    /** Database column state DBType(varchar), Length(255,true), Default(None) */
    val state: Column[Option[String]] = column[Option[String]]("state", O.Length(255,varying=true), O.Default(None))
    /** Database column last_problem DBType(text), Length(2147483647,true), Default(None) */
    val lastProblem: Column[Option[String]] = column[Option[String]]("last_problem", O.Length(2147483647,varying=true), O.Default(None))
  }
  /** Collection-like TableQuery object for table PlayEvolutions */
  lazy val PlayEvolutions = new TableQuery(tag => new PlayEvolutions(tag))
}