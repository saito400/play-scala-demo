package controllers

import models._
import models.Tables._
import play.api._
import play.api.Play.current
import play.api.data.Forms._
import play.api.db.slick._
import play.api.db.slick.DBAction
import play.api.mvc._
import play.api.data._
import play.api.libs.json._
import play.api.libs.json.Reads._ // Custom validation helpers
import play.api.libs.functional.syntax._ // Combinator syntax
import play.api.data.validation.ValidationError

//import play.api.db.slick.Config.driver.simple._  following Driver needed to be used instead of this to Delete records
import scala.slick.driver.PostgresDriver.simple._

object JsonController extends Controller {

  case class TestData(id: Int, name: String)
  implicit val testDataFormat = Json.format[TestData]

  case class Comment(author: String, text: String)
  implicit val commentFormat = Json.format[Comment]

  def index = DBAction { implicit rs =>
    val list = Seq(Comment("author1", "test1"), Comment("author2", "text2"))
    val json = Json.toJson(list)
    Ok(json)
  }

  def index2 = DBAction { implicit rs =>
    val data = """{
    "id": 2,
    "version": 2,
    "headers": [
        {
            "id": 2,
            "value": "value2",
            "versionId": 2,
            "values": [
                {
                    "id": 2,
                    "value": "param value2",
                    "headerId": 2
                },
                {
                    "id": 3,
                    "value": "param value2b",
                    "headerId": 2
                }
            ]
        },
        {
            "id": 3,
            "value": "value3",
            "versionId": 2,
            "values": [
                {
                    "id": 4,
                    "value": "param value3",
                    "headerId": 3
                },
                {
                    "id": 5,
                    "value": "param value3b",
                    "headerId": 3
                }
            ]
        }
    ]
    }"""

    val json = Json.parse(data)
    Ok(json)
  }

  def insert = DBAction { implicit rs =>
    //TODO insert and get data
    val list = Seq(Comment("author1", "test1"), Comment("author2", "text2"))
    val json = Json.toJson(list)
    Ok(json)

  }

  def index3 = DBAction { implicit rs =>

    val v = Seq((1, 1, 1), (1, 1, 2), (1, 1, 3), (1, 2, 1), (1, 2, 3), (2, 1, 1), (2, 1, 2))
    val re = v.groupBy(_._1)
      .map { x =>
        (x._1, x._2.map {
          y =>
            (
              y._2, y._3
            )
        }.groupBy(_._1).map { z => (z._1, z._2.map(zz => zz._2)) })
      }
    //    val json = Json.toJson(re)
    //    val json = Json.parse(re)
    val list = Seq(Comment("author1", "test1"), Comment("author2", "text2"))
    val json = Json.toJson(list)
    Ok(json)
  }

  implicit def tuple3Reads[A, B, C](implicit aReads: Reads[A], bReads: Reads[B], cReads: Reads[C]): Reads[Tuple3[A, B, C]] = Reads[Tuple3[A, B, C]] {
    case JsArray(arr) if arr.size == 3 => for {
      a <- aReads.reads(arr(0))
      b <- bReads.reads(arr(1))
      c <- cReads.reads(arr(2))
    } yield (a, b, c)
    case _ => JsError(Seq(JsPath() -> Seq(ValidationError("Expected array of three elements"))))
  }

  implicit def tuple3Writes[A, B, C](implicit aWrites: Writes[A], bWrites: Writes[B], cWrites: Writes[C]): Writes[Tuple3[A, B, C]] = new Writes[Tuple3[A, B, C]] {
    def writes(tuple: Tuple3[A, B, C]) = JsArray(Seq(aWrites.writes(tuple._1), bWrites.writes(tuple._2), cWrites.writes(tuple._3)))
  }

  def index4 = DBAction { implicit rs =>

    val v = Seq((1, 1, 1), (1, 1, 2), (1, 1, 3), (1, 2, 1), (1, 2, 3), (2, 1, 1), (2, 1, 2))

    val json = Json.toJson(v)
    Ok(json)
  }


}
