package be.frol.game.tables
// AUTO-GENERATED Slick data model
/** Stand-alone Slick data model for immediate use */
object Tables extends {
  val profile = slick.jdbc.MySQLProfile
} with Tables

/** Slick data model trait for extension, choice of backend or usage in the cake pattern. (Make sure to initialize this late.) */
trait Tables {
  val profile: slick.jdbc.JdbcProfile
  import profile.api._
  import slick.model.ForeignKeyAction
  // NOTE: GetResult mappers for plain SQL are only generated for tables where Slick knows how to map the types of all columns.
  import slick.jdbc.{GetResult => GR}

  /** DDL for all tables. Call .create to execute. */
  lazy val schema: profile.SchemaDescription = Array(DilChoice.schema, DilResponse.schema, DilRound.schema, File.schema, Game.schema, LitRound.schema, PlayEvolutions.schema, User.schema, UserInGame.schema, UserReference.schema).reduceLeft(_ ++ _)
  @deprecated("Use .schema instead of .ddl", "3.0")
  def ddl = schema

  /** Entity class storing rows of table DilChoice
   *  @param id Database column id SqlType(BIGINT), AutoInc, PrimaryKey
   *  @param uuid Database column uuid SqlType(VARCHAR), Length(255,true)
   *  @param fkGameId Database column fk_game_id SqlType(BIGINT)
   *  @param fkUserId Database column fk_user_id SqlType(BIGINT)
   *  @param fkRoundId Database column fk_round_id SqlType(BIGINT)
   *  @param fkResponseId Database column fk_response_id SqlType(BIGINT)
   *  @param timestamp Database column timestamp SqlType(TIMESTAMP) */
  case class DilChoiceRow(id: Long, uuid: String, fkGameId: Long, fkUserId: Long, fkRoundId: Long, fkResponseId: Long, timestamp: Option[java.sql.Timestamp])
  /** GetResult implicit for fetching DilChoiceRow objects using plain SQL queries */
  implicit def GetResultDilChoiceRow(implicit e0: GR[Long], e1: GR[String], e2: GR[Option[java.sql.Timestamp]]): GR[DilChoiceRow] = GR{
    prs => import prs._
    DilChoiceRow.tupled((<<[Long], <<[String], <<[Long], <<[Long], <<[Long], <<[Long], <<?[java.sql.Timestamp]))
  }
  /** Table description of table dil_choice. Objects of this class serve as prototypes for rows in queries. */
  class DilChoice(_tableTag: Tag) extends profile.api.Table[DilChoiceRow](_tableTag, Some("db"), "dil_choice") {
    def * = (id, uuid, fkGameId, fkUserId, fkRoundId, fkResponseId, timestamp) <> (DilChoiceRow.tupled, DilChoiceRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = ((Rep.Some(id), Rep.Some(uuid), Rep.Some(fkGameId), Rep.Some(fkUserId), Rep.Some(fkRoundId), Rep.Some(fkResponseId), timestamp)).shaped.<>({r=>import r._; _1.map(_=> DilChoiceRow.tupled((_1.get, _2.get, _3.get, _4.get, _5.get, _6.get, _7)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))

    /** Database column id SqlType(BIGINT), AutoInc, PrimaryKey */
    val id: Rep[Long] = column[Long]("id", O.AutoInc, O.PrimaryKey)
    /** Database column uuid SqlType(VARCHAR), Length(255,true) */
    val uuid: Rep[String] = column[String]("uuid", O.Length(255,varying=true))
    /** Database column fk_game_id SqlType(BIGINT) */
    val fkGameId: Rep[Long] = column[Long]("fk_game_id")
    /** Database column fk_user_id SqlType(BIGINT) */
    val fkUserId: Rep[Long] = column[Long]("fk_user_id")
    /** Database column fk_round_id SqlType(BIGINT) */
    val fkRoundId: Rep[Long] = column[Long]("fk_round_id")
    /** Database column fk_response_id SqlType(BIGINT) */
    val fkResponseId: Rep[Long] = column[Long]("fk_response_id")
    /** Database column timestamp SqlType(TIMESTAMP) */
    val timestamp: Rep[Option[java.sql.Timestamp]] = column[Option[java.sql.Timestamp]]("timestamp")

    /** Foreign key referencing DilResponse (database name dil_choice_ibfk_4) */
    lazy val dilResponseFk = foreignKey("dil_choice_ibfk_4", fkResponseId, DilResponse)(r => r.id, onUpdate=ForeignKeyAction.NoAction, onDelete=ForeignKeyAction.NoAction)
    /** Foreign key referencing DilRound (database name dil_choice_ibfk_1) */
    lazy val dilRoundFk = foreignKey("dil_choice_ibfk_1", fkRoundId, DilRound)(r => r.id, onUpdate=ForeignKeyAction.NoAction, onDelete=ForeignKeyAction.NoAction)
    /** Foreign key referencing Game (database name dil_choice_ibfk_3) */
    lazy val gameFk = foreignKey("dil_choice_ibfk_3", fkGameId, Game)(r => r.id, onUpdate=ForeignKeyAction.NoAction, onDelete=ForeignKeyAction.NoAction)
    /** Foreign key referencing User (database name dil_choice_ibfk_2) */
    lazy val userFk = foreignKey("dil_choice_ibfk_2", fkUserId, User)(r => r.id, onUpdate=ForeignKeyAction.NoAction, onDelete=ForeignKeyAction.NoAction)

    /** Uniqueness Index over (fkUserId,fkRoundId) (database name dil_one_choice_by_user) */
    val index1 = index("dil_one_choice_by_user", (fkUserId, fkRoundId), unique=true)
  }
  /** Collection-like TableQuery object for table DilChoice */
  lazy val DilChoice = new TableQuery(tag => new DilChoice(tag))

  /** Entity class storing rows of table DilResponse
   *  @param id Database column id SqlType(BIGINT), AutoInc, PrimaryKey
   *  @param uuid Database column uuid SqlType(VARCHAR), Length(255,true)
   *  @param fkGameId Database column fk_game_id SqlType(BIGINT)
   *  @param fkUserId Database column fk_user_id SqlType(BIGINT)
   *  @param fkRoundId Database column fk_round_id SqlType(BIGINT)
   *  @param response Database column response SqlType(VARCHAR), Length(255,true)
   *  @param timestamp Database column timestamp SqlType(TIMESTAMP) */
  case class DilResponseRow(id: Long, uuid: String, fkGameId: Long, fkUserId: Long, fkRoundId: Long, response: String, timestamp: Option[java.sql.Timestamp])
  /** GetResult implicit for fetching DilResponseRow objects using plain SQL queries */
  implicit def GetResultDilResponseRow(implicit e0: GR[Long], e1: GR[String], e2: GR[Option[java.sql.Timestamp]]): GR[DilResponseRow] = GR{
    prs => import prs._
    DilResponseRow.tupled((<<[Long], <<[String], <<[Long], <<[Long], <<[Long], <<[String], <<?[java.sql.Timestamp]))
  }
  /** Table description of table dil_response. Objects of this class serve as prototypes for rows in queries. */
  class DilResponse(_tableTag: Tag) extends profile.api.Table[DilResponseRow](_tableTag, Some("db"), "dil_response") {
    def * = (id, uuid, fkGameId, fkUserId, fkRoundId, response, timestamp) <> (DilResponseRow.tupled, DilResponseRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = ((Rep.Some(id), Rep.Some(uuid), Rep.Some(fkGameId), Rep.Some(fkUserId), Rep.Some(fkRoundId), Rep.Some(response), timestamp)).shaped.<>({r=>import r._; _1.map(_=> DilResponseRow.tupled((_1.get, _2.get, _3.get, _4.get, _5.get, _6.get, _7)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))

    /** Database column id SqlType(BIGINT), AutoInc, PrimaryKey */
    val id: Rep[Long] = column[Long]("id", O.AutoInc, O.PrimaryKey)
    /** Database column uuid SqlType(VARCHAR), Length(255,true) */
    val uuid: Rep[String] = column[String]("uuid", O.Length(255,varying=true))
    /** Database column fk_game_id SqlType(BIGINT) */
    val fkGameId: Rep[Long] = column[Long]("fk_game_id")
    /** Database column fk_user_id SqlType(BIGINT) */
    val fkUserId: Rep[Long] = column[Long]("fk_user_id")
    /** Database column fk_round_id SqlType(BIGINT) */
    val fkRoundId: Rep[Long] = column[Long]("fk_round_id")
    /** Database column response SqlType(VARCHAR), Length(255,true) */
    val response: Rep[String] = column[String]("response", O.Length(255,varying=true))
    /** Database column timestamp SqlType(TIMESTAMP) */
    val timestamp: Rep[Option[java.sql.Timestamp]] = column[Option[java.sql.Timestamp]]("timestamp")

    /** Foreign key referencing DilRound (database name dil_response_ibfk_3) */
    lazy val dilRoundFk = foreignKey("dil_response_ibfk_3", fkRoundId, DilRound)(r => r.id, onUpdate=ForeignKeyAction.NoAction, onDelete=ForeignKeyAction.NoAction)
    /** Foreign key referencing Game (database name dil_response_ibfk_2) */
    lazy val gameFk = foreignKey("dil_response_ibfk_2", fkGameId, Game)(r => r.id, onUpdate=ForeignKeyAction.NoAction, onDelete=ForeignKeyAction.NoAction)
    /** Foreign key referencing User (database name dil_response_ibfk_1) */
    lazy val userFk = foreignKey("dil_response_ibfk_1", fkUserId, User)(r => r.id, onUpdate=ForeignKeyAction.NoAction, onDelete=ForeignKeyAction.NoAction)

    /** Uniqueness Index over (fkUserId,fkRoundId) (database name dil_one_response_by_user) */
    val index1 = index("dil_one_response_by_user", (fkUserId, fkRoundId), unique=true)
  }
  /** Collection-like TableQuery object for table DilResponse */
  lazy val DilResponse = new TableQuery(tag => new DilResponse(tag))

  /** Entity class storing rows of table DilRound
   *  @param id Database column id SqlType(BIGINT), AutoInc, PrimaryKey
   *  @param uuid Database column uuid SqlType(VARCHAR), Length(255,true)
   *  @param fkGameId Database column fk_game_id SqlType(BIGINT)
   *  @param fkUserId Database column fk_user_id SqlType(BIGINT)
   *  @param question Database column question SqlType(VARCHAR), Length(255,true)
   *  @param timestamp Database column timestamp SqlType(TIMESTAMP) */
  case class DilRoundRow(id: Long, uuid: String, fkGameId: Long, fkUserId: Long, question: String, timestamp: Option[java.sql.Timestamp])
  /** GetResult implicit for fetching DilRoundRow objects using plain SQL queries */
  implicit def GetResultDilRoundRow(implicit e0: GR[Long], e1: GR[String], e2: GR[Option[java.sql.Timestamp]]): GR[DilRoundRow] = GR{
    prs => import prs._
    DilRoundRow.tupled((<<[Long], <<[String], <<[Long], <<[Long], <<[String], <<?[java.sql.Timestamp]))
  }
  /** Table description of table dil_round. Objects of this class serve as prototypes for rows in queries. */
  class DilRound(_tableTag: Tag) extends profile.api.Table[DilRoundRow](_tableTag, Some("db"), "dil_round") {
    def * = (id, uuid, fkGameId, fkUserId, question, timestamp) <> (DilRoundRow.tupled, DilRoundRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = ((Rep.Some(id), Rep.Some(uuid), Rep.Some(fkGameId), Rep.Some(fkUserId), Rep.Some(question), timestamp)).shaped.<>({r=>import r._; _1.map(_=> DilRoundRow.tupled((_1.get, _2.get, _3.get, _4.get, _5.get, _6)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))

    /** Database column id SqlType(BIGINT), AutoInc, PrimaryKey */
    val id: Rep[Long] = column[Long]("id", O.AutoInc, O.PrimaryKey)
    /** Database column uuid SqlType(VARCHAR), Length(255,true) */
    val uuid: Rep[String] = column[String]("uuid", O.Length(255,varying=true))
    /** Database column fk_game_id SqlType(BIGINT) */
    val fkGameId: Rep[Long] = column[Long]("fk_game_id")
    /** Database column fk_user_id SqlType(BIGINT) */
    val fkUserId: Rep[Long] = column[Long]("fk_user_id")
    /** Database column question SqlType(VARCHAR), Length(255,true) */
    val question: Rep[String] = column[String]("question", O.Length(255,varying=true))
    /** Database column timestamp SqlType(TIMESTAMP) */
    val timestamp: Rep[Option[java.sql.Timestamp]] = column[Option[java.sql.Timestamp]]("timestamp")

    /** Foreign key referencing Game (database name dil_round_ibfk_2) */
    lazy val gameFk = foreignKey("dil_round_ibfk_2", fkGameId, Game)(r => r.id, onUpdate=ForeignKeyAction.NoAction, onDelete=ForeignKeyAction.NoAction)
    /** Foreign key referencing User (database name dil_round_ibfk_1) */
    lazy val userFk = foreignKey("dil_round_ibfk_1", fkUserId, User)(r => r.id, onUpdate=ForeignKeyAction.NoAction, onDelete=ForeignKeyAction.NoAction)
  }
  /** Collection-like TableQuery object for table DilRound */
  lazy val DilRound = new TableQuery(tag => new DilRound(tag))

  /** Entity class storing rows of table File
   *  @param id Database column id SqlType(VARCHAR), PrimaryKey, Length(36,true)
   *  @param value Database column value SqlType(LONGBLOB)
   *  @param name Database column name SqlType(VARCHAR), Length(255,true)
   *  @param mime Database column mime SqlType(VARCHAR), Length(255,true)
   *  @param fkUserId Database column fk_user_id SqlType(BIGINT)
   *  @param timestamp Database column timestamp SqlType(TIMESTAMP) */
  case class FileRow(id: String, value: java.sql.Blob, name: String, mime: String, fkUserId: Long, timestamp: java.sql.Timestamp)
  /** GetResult implicit for fetching FileRow objects using plain SQL queries */
  implicit def GetResultFileRow(implicit e0: GR[String], e1: GR[java.sql.Blob], e2: GR[Long], e3: GR[java.sql.Timestamp]): GR[FileRow] = GR{
    prs => import prs._
    FileRow.tupled((<<[String], <<[java.sql.Blob], <<[String], <<[String], <<[Long], <<[java.sql.Timestamp]))
  }
  /** Table description of table file. Objects of this class serve as prototypes for rows in queries. */
  class File(_tableTag: Tag) extends profile.api.Table[FileRow](_tableTag, Some("db"), "file") {
    def * = (id, value, name, mime, fkUserId, timestamp) <> (FileRow.tupled, FileRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = ((Rep.Some(id), Rep.Some(value), Rep.Some(name), Rep.Some(mime), Rep.Some(fkUserId), Rep.Some(timestamp))).shaped.<>({r=>import r._; _1.map(_=> FileRow.tupled((_1.get, _2.get, _3.get, _4.get, _5.get, _6.get)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))

    /** Database column id SqlType(VARCHAR), PrimaryKey, Length(36,true) */
    val id: Rep[String] = column[String]("id", O.PrimaryKey, O.Length(36,varying=true))
    /** Database column value SqlType(LONGBLOB) */
    val value: Rep[java.sql.Blob] = column[java.sql.Blob]("value")
    /** Database column name SqlType(VARCHAR), Length(255,true) */
    val name: Rep[String] = column[String]("name", O.Length(255,varying=true))
    /** Database column mime SqlType(VARCHAR), Length(255,true) */
    val mime: Rep[String] = column[String]("mime", O.Length(255,varying=true))
    /** Database column fk_user_id SqlType(BIGINT) */
    val fkUserId: Rep[Long] = column[Long]("fk_user_id")
    /** Database column timestamp SqlType(TIMESTAMP) */
    val timestamp: Rep[java.sql.Timestamp] = column[java.sql.Timestamp]("timestamp")

    /** Foreign key referencing User (database name file_ibfk_1) */
    lazy val userFk = foreignKey("file_ibfk_1", fkUserId, User)(r => r.id, onUpdate=ForeignKeyAction.NoAction, onDelete=ForeignKeyAction.NoAction)
  }
  /** Collection-like TableQuery object for table File */
  lazy val File = new TableQuery(tag => new File(tag))

  /** Entity class storing rows of table Game
   *  @param id Database column id SqlType(BIGINT), AutoInc, PrimaryKey
   *  @param uuid Database column uuid SqlType(VARCHAR), Length(36,true)
   *  @param gameType Database column game_type SqlType(VARCHAR), Length(255,true)
   *  @param timestamp Database column timestamp SqlType(TIMESTAMP)
   *  @param state Database column state SqlType(VARCHAR), Length(255,true) */
  case class GameRow(id: Long, uuid: String, gameType: String, timestamp: java.sql.Timestamp, state: String)
  /** GetResult implicit for fetching GameRow objects using plain SQL queries */
  implicit def GetResultGameRow(implicit e0: GR[Long], e1: GR[String], e2: GR[java.sql.Timestamp]): GR[GameRow] = GR{
    prs => import prs._
    GameRow.tupled((<<[Long], <<[String], <<[String], <<[java.sql.Timestamp], <<[String]))
  }
  /** Table description of table game. Objects of this class serve as prototypes for rows in queries. */
  class Game(_tableTag: Tag) extends profile.api.Table[GameRow](_tableTag, Some("db"), "game") {
    def * = (id, uuid, gameType, timestamp, state) <> (GameRow.tupled, GameRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = ((Rep.Some(id), Rep.Some(uuid), Rep.Some(gameType), Rep.Some(timestamp), Rep.Some(state))).shaped.<>({r=>import r._; _1.map(_=> GameRow.tupled((_1.get, _2.get, _3.get, _4.get, _5.get)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))

    /** Database column id SqlType(BIGINT), AutoInc, PrimaryKey */
    val id: Rep[Long] = column[Long]("id", O.AutoInc, O.PrimaryKey)
    /** Database column uuid SqlType(VARCHAR), Length(36,true) */
    val uuid: Rep[String] = column[String]("uuid", O.Length(36,varying=true))
    /** Database column game_type SqlType(VARCHAR), Length(255,true) */
    val gameType: Rep[String] = column[String]("game_type", O.Length(255,varying=true))
    /** Database column timestamp SqlType(TIMESTAMP) */
    val timestamp: Rep[java.sql.Timestamp] = column[java.sql.Timestamp]("timestamp")
    /** Database column state SqlType(VARCHAR), Length(255,true) */
    val state: Rep[String] = column[String]("state", O.Length(255,varying=true))
  }
  /** Collection-like TableQuery object for table Game */
  lazy val Game = new TableQuery(tag => new Game(tag))

  /** Entity class storing rows of table LitRound
   *  @param id Database column id SqlType(BIGINT), AutoInc, PrimaryKey
   *  @param fkGameId Database column fk_game_id SqlType(BIGINT)
   *  @param fkOriginalUserId Database column fk_original_user_id SqlType(BIGINT)
   *  @param fkUserId Database column fk_user_id SqlType(BIGINT)
   *  @param text Database column text SqlType(VARCHAR), Length(255,true), Default(None)
   *  @param fkFileId Database column fk_file_id SqlType(VARCHAR), Length(36,true), Default(None)
   *  @param timestamp Database column timestamp SqlType(TIMESTAMP)
   *  @param storyId Database column story_id SqlType(VARCHAR), Length(255,true), Default(old) */
  case class LitRoundRow(id: Long, fkGameId: Long, fkOriginalUserId: Long, fkUserId: Long, text: Option[String] = None, fkFileId: Option[String] = None, timestamp: Option[java.sql.Timestamp], storyId: String = "old")
  /** GetResult implicit for fetching LitRoundRow objects using plain SQL queries */
  implicit def GetResultLitRoundRow(implicit e0: GR[Long], e1: GR[Option[String]], e2: GR[Option[java.sql.Timestamp]], e3: GR[String]): GR[LitRoundRow] = GR{
    prs => import prs._
    LitRoundRow.tupled((<<[Long], <<[Long], <<[Long], <<[Long], <<?[String], <<?[String], <<?[java.sql.Timestamp], <<[String]))
  }
  /** Table description of table lit_round. Objects of this class serve as prototypes for rows in queries. */
  class LitRound(_tableTag: Tag) extends profile.api.Table[LitRoundRow](_tableTag, Some("db"), "lit_round") {
    def * = (id, fkGameId, fkOriginalUserId, fkUserId, text, fkFileId, timestamp, storyId) <> (LitRoundRow.tupled, LitRoundRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = ((Rep.Some(id), Rep.Some(fkGameId), Rep.Some(fkOriginalUserId), Rep.Some(fkUserId), text, fkFileId, timestamp, Rep.Some(storyId))).shaped.<>({r=>import r._; _1.map(_=> LitRoundRow.tupled((_1.get, _2.get, _3.get, _4.get, _5, _6, _7, _8.get)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))

    /** Database column id SqlType(BIGINT), AutoInc, PrimaryKey */
    val id: Rep[Long] = column[Long]("id", O.AutoInc, O.PrimaryKey)
    /** Database column fk_game_id SqlType(BIGINT) */
    val fkGameId: Rep[Long] = column[Long]("fk_game_id")
    /** Database column fk_original_user_id SqlType(BIGINT) */
    val fkOriginalUserId: Rep[Long] = column[Long]("fk_original_user_id")
    /** Database column fk_user_id SqlType(BIGINT) */
    val fkUserId: Rep[Long] = column[Long]("fk_user_id")
    /** Database column text SqlType(VARCHAR), Length(255,true), Default(None) */
    val text: Rep[Option[String]] = column[Option[String]]("text", O.Length(255,varying=true), O.Default(None))
    /** Database column fk_file_id SqlType(VARCHAR), Length(36,true), Default(None) */
    val fkFileId: Rep[Option[String]] = column[Option[String]]("fk_file_id", O.Length(36,varying=true), O.Default(None))
    /** Database column timestamp SqlType(TIMESTAMP) */
    val timestamp: Rep[Option[java.sql.Timestamp]] = column[Option[java.sql.Timestamp]]("timestamp")
    /** Database column story_id SqlType(VARCHAR), Length(255,true), Default(old) */
    val storyId: Rep[String] = column[String]("story_id", O.Length(255,varying=true), O.Default("old"))

    /** Foreign key referencing Game (database name lit_round_ibfk_3) */
    lazy val gameFk = foreignKey("lit_round_ibfk_3", fkGameId, Game)(r => r.id, onUpdate=ForeignKeyAction.NoAction, onDelete=ForeignKeyAction.NoAction)
    /** Foreign key referencing User (database name lit_round_ibfk_1) */
    lazy val userFk2 = foreignKey("lit_round_ibfk_1", fkUserId, User)(r => r.id, onUpdate=ForeignKeyAction.NoAction, onDelete=ForeignKeyAction.NoAction)
    /** Foreign key referencing User (database name lit_round_ibfk_2) */
    lazy val userFk3 = foreignKey("lit_round_ibfk_2", fkOriginalUserId, User)(r => r.id, onUpdate=ForeignKeyAction.NoAction, onDelete=ForeignKeyAction.NoAction)
  }
  /** Collection-like TableQuery object for table LitRound */
  lazy val LitRound = new TableQuery(tag => new LitRound(tag))

  /** Entity class storing rows of table PlayEvolutions
   *  @param id Database column id SqlType(INT), PrimaryKey
   *  @param hash Database column hash SqlType(VARCHAR), Length(255,true)
   *  @param appliedAt Database column applied_at SqlType(TIMESTAMP)
   *  @param applyScript Database column apply_script SqlType(MEDIUMTEXT), Length(16777215,true), Default(None)
   *  @param revertScript Database column revert_script SqlType(MEDIUMTEXT), Length(16777215,true), Default(None)
   *  @param state Database column state SqlType(VARCHAR), Length(255,true), Default(None)
   *  @param lastProblem Database column last_problem SqlType(MEDIUMTEXT), Length(16777215,true), Default(None) */
  case class PlayEvolutionsRow(id: Int, hash: String, appliedAt: java.sql.Timestamp, applyScript: Option[String] = None, revertScript: Option[String] = None, state: Option[String] = None, lastProblem: Option[String] = None)
  /** GetResult implicit for fetching PlayEvolutionsRow objects using plain SQL queries */
  implicit def GetResultPlayEvolutionsRow(implicit e0: GR[Int], e1: GR[String], e2: GR[java.sql.Timestamp], e3: GR[Option[String]]): GR[PlayEvolutionsRow] = GR{
    prs => import prs._
    PlayEvolutionsRow.tupled((<<[Int], <<[String], <<[java.sql.Timestamp], <<?[String], <<?[String], <<?[String], <<?[String]))
  }
  /** Table description of table play_evolutions. Objects of this class serve as prototypes for rows in queries. */
  class PlayEvolutions(_tableTag: Tag) extends profile.api.Table[PlayEvolutionsRow](_tableTag, Some("db"), "play_evolutions") {
    def * = (id, hash, appliedAt, applyScript, revertScript, state, lastProblem) <> (PlayEvolutionsRow.tupled, PlayEvolutionsRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = ((Rep.Some(id), Rep.Some(hash), Rep.Some(appliedAt), applyScript, revertScript, state, lastProblem)).shaped.<>({r=>import r._; _1.map(_=> PlayEvolutionsRow.tupled((_1.get, _2.get, _3.get, _4, _5, _6, _7)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))

    /** Database column id SqlType(INT), PrimaryKey */
    val id: Rep[Int] = column[Int]("id", O.PrimaryKey)
    /** Database column hash SqlType(VARCHAR), Length(255,true) */
    val hash: Rep[String] = column[String]("hash", O.Length(255,varying=true))
    /** Database column applied_at SqlType(TIMESTAMP) */
    val appliedAt: Rep[java.sql.Timestamp] = column[java.sql.Timestamp]("applied_at")
    /** Database column apply_script SqlType(MEDIUMTEXT), Length(16777215,true), Default(None) */
    val applyScript: Rep[Option[String]] = column[Option[String]]("apply_script", O.Length(16777215,varying=true), O.Default(None))
    /** Database column revert_script SqlType(MEDIUMTEXT), Length(16777215,true), Default(None) */
    val revertScript: Rep[Option[String]] = column[Option[String]]("revert_script", O.Length(16777215,varying=true), O.Default(None))
    /** Database column state SqlType(VARCHAR), Length(255,true), Default(None) */
    val state: Rep[Option[String]] = column[Option[String]]("state", O.Length(255,varying=true), O.Default(None))
    /** Database column last_problem SqlType(MEDIUMTEXT), Length(16777215,true), Default(None) */
    val lastProblem: Rep[Option[String]] = column[Option[String]]("last_problem", O.Length(16777215,varying=true), O.Default(None))
  }
  /** Collection-like TableQuery object for table PlayEvolutions */
  lazy val PlayEvolutions = new TableQuery(tag => new PlayEvolutions(tag))

  /** Entity class storing rows of table User
   *  @param id Database column id SqlType(BIGINT), AutoInc, PrimaryKey
   *  @param name Database column name SqlType(VARCHAR), Length(255,true)
   *  @param uuid Database column uuid SqlType(VARCHAR), Length(36,true)
   *  @param timestamp Database column timestamp SqlType(TIMESTAMP) */
  case class UserRow(id: Long, name: String, uuid: String, timestamp: java.sql.Timestamp)
  /** GetResult implicit for fetching UserRow objects using plain SQL queries */
  implicit def GetResultUserRow(implicit e0: GR[Long], e1: GR[String], e2: GR[java.sql.Timestamp]): GR[UserRow] = GR{
    prs => import prs._
    UserRow.tupled((<<[Long], <<[String], <<[String], <<[java.sql.Timestamp]))
  }
  /** Table description of table user. Objects of this class serve as prototypes for rows in queries. */
  class User(_tableTag: Tag) extends profile.api.Table[UserRow](_tableTag, Some("db"), "user") {
    def * = (id, name, uuid, timestamp) <> (UserRow.tupled, UserRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = ((Rep.Some(id), Rep.Some(name), Rep.Some(uuid), Rep.Some(timestamp))).shaped.<>({r=>import r._; _1.map(_=> UserRow.tupled((_1.get, _2.get, _3.get, _4.get)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))

    /** Database column id SqlType(BIGINT), AutoInc, PrimaryKey */
    val id: Rep[Long] = column[Long]("id", O.AutoInc, O.PrimaryKey)
    /** Database column name SqlType(VARCHAR), Length(255,true) */
    val name: Rep[String] = column[String]("name", O.Length(255,varying=true))
    /** Database column uuid SqlType(VARCHAR), Length(36,true) */
    val uuid: Rep[String] = column[String]("uuid", O.Length(36,varying=true))
    /** Database column timestamp SqlType(TIMESTAMP) */
    val timestamp: Rep[java.sql.Timestamp] = column[java.sql.Timestamp]("timestamp")

    /** Uniqueness Index over (uuid) (database name user_uuid) */
    val index1 = index("user_uuid", uuid, unique=true)
  }
  /** Collection-like TableQuery object for table User */
  lazy val User = new TableQuery(tag => new User(tag))

  /** Entity class storing rows of table UserInGame
   *  @param id Database column id SqlType(BIGINT), AutoInc, PrimaryKey
   *  @param fkGameId Database column fk_game_id SqlType(BIGINT)
   *  @param fkUserId Database column fk_user_id SqlType(BIGINT)
   *  @param role Database column role SqlType(VARCHAR), Length(255,true), Default(None) */
  case class UserInGameRow(id: Long, fkGameId: Long, fkUserId: Long, role: Option[String] = None)
  /** GetResult implicit for fetching UserInGameRow objects using plain SQL queries */
  implicit def GetResultUserInGameRow(implicit e0: GR[Long], e1: GR[Option[String]]): GR[UserInGameRow] = GR{
    prs => import prs._
    UserInGameRow.tupled((<<[Long], <<[Long], <<[Long], <<?[String]))
  }
  /** Table description of table user_in_game. Objects of this class serve as prototypes for rows in queries. */
  class UserInGame(_tableTag: Tag) extends profile.api.Table[UserInGameRow](_tableTag, Some("db"), "user_in_game") {
    def * = (id, fkGameId, fkUserId, role) <> (UserInGameRow.tupled, UserInGameRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = ((Rep.Some(id), Rep.Some(fkGameId), Rep.Some(fkUserId), role)).shaped.<>({r=>import r._; _1.map(_=> UserInGameRow.tupled((_1.get, _2.get, _3.get, _4)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))

    /** Database column id SqlType(BIGINT), AutoInc, PrimaryKey */
    val id: Rep[Long] = column[Long]("id", O.AutoInc, O.PrimaryKey)
    /** Database column fk_game_id SqlType(BIGINT) */
    val fkGameId: Rep[Long] = column[Long]("fk_game_id")
    /** Database column fk_user_id SqlType(BIGINT) */
    val fkUserId: Rep[Long] = column[Long]("fk_user_id")
    /** Database column role SqlType(VARCHAR), Length(255,true), Default(None) */
    val role: Rep[Option[String]] = column[Option[String]]("role", O.Length(255,varying=true), O.Default(None))

    /** Foreign key referencing Game (database name user_in_game_ibfk_2) */
    lazy val gameFk = foreignKey("user_in_game_ibfk_2", fkGameId, Game)(r => r.id, onUpdate=ForeignKeyAction.NoAction, onDelete=ForeignKeyAction.NoAction)
    /** Foreign key referencing User (database name user_in_game_ibfk_1) */
    lazy val userFk = foreignKey("user_in_game_ibfk_1", fkUserId, User)(r => r.id, onUpdate=ForeignKeyAction.NoAction, onDelete=ForeignKeyAction.NoAction)

    /** Uniqueness Index over (fkUserId,fkGameId) (database name user_in_game_unicity) */
    val index1 = index("user_in_game_unicity", (fkUserId, fkGameId), unique=true)
  }
  /** Collection-like TableQuery object for table UserInGame */
  lazy val UserInGame = new TableQuery(tag => new UserInGame(tag))

  /** Entity class storing rows of table UserReference
   *  @param id Database column id SqlType(BIGINT), AutoInc, PrimaryKey
   *  @param fkUserId Database column fk_user_id SqlType(BIGINT)
   *  @param reference Database column reference SqlType(VARCHAR), Length(36,true)
   *  @param timestamp Database column timestamp SqlType(TIMESTAMP)
   *  @param service Database column service SqlType(VARCHAR), Length(255,true) */
  case class UserReferenceRow(id: Long, fkUserId: Long, reference: String, timestamp: java.sql.Timestamp, service: String)
  /** GetResult implicit for fetching UserReferenceRow objects using plain SQL queries */
  implicit def GetResultUserReferenceRow(implicit e0: GR[Long], e1: GR[String], e2: GR[java.sql.Timestamp]): GR[UserReferenceRow] = GR{
    prs => import prs._
    UserReferenceRow.tupled((<<[Long], <<[Long], <<[String], <<[java.sql.Timestamp], <<[String]))
  }
  /** Table description of table user_reference. Objects of this class serve as prototypes for rows in queries. */
  class UserReference(_tableTag: Tag) extends profile.api.Table[UserReferenceRow](_tableTag, Some("db"), "user_reference") {
    def * = (id, fkUserId, reference, timestamp, service) <> (UserReferenceRow.tupled, UserReferenceRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = ((Rep.Some(id), Rep.Some(fkUserId), Rep.Some(reference), Rep.Some(timestamp), Rep.Some(service))).shaped.<>({r=>import r._; _1.map(_=> UserReferenceRow.tupled((_1.get, _2.get, _3.get, _4.get, _5.get)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))

    /** Database column id SqlType(BIGINT), AutoInc, PrimaryKey */
    val id: Rep[Long] = column[Long]("id", O.AutoInc, O.PrimaryKey)
    /** Database column fk_user_id SqlType(BIGINT) */
    val fkUserId: Rep[Long] = column[Long]("fk_user_id")
    /** Database column reference SqlType(VARCHAR), Length(36,true) */
    val reference: Rep[String] = column[String]("reference", O.Length(36,varying=true))
    /** Database column timestamp SqlType(TIMESTAMP) */
    val timestamp: Rep[java.sql.Timestamp] = column[java.sql.Timestamp]("timestamp")
    /** Database column service SqlType(VARCHAR), Length(255,true) */
    val service: Rep[String] = column[String]("service", O.Length(255,varying=true))

    /** Foreign key referencing User (database name user_reference_ibfk_1) */
    lazy val userFk = foreignKey("user_reference_ibfk_1", fkUserId, User)(r => r.id, onUpdate=ForeignKeyAction.NoAction, onDelete=ForeignKeyAction.NoAction)

    /** Uniqueness Index over (reference,service) (database name user_reference) */
    val index1 = index("user_reference", (reference, service), unique=true)
  }
  /** Collection-like TableQuery object for table UserReference */
  lazy val UserReference = new TableQuery(tag => new UserReference(tag))
}
