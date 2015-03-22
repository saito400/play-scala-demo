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

//import play.api.db.slick.Config.driver.simple._  following Driver needed to be used instead of this to Delete records
import scala.slick.driver.PostgresDriver.simple._


object JsonController extends Controller {

  case class TestData(id: Int, name: String)
  implicit val testDataFormat = Json.format[TestData]

  case class Comment(author: String, text: String)
  implicit val commentFormat = Json.format[Comment]

  def index = DBAction { implicit rs =>
    val list = Seq(Comment("author1","test1"), Comment("author2","text2"))
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
    val list = Seq(Comment("author1","test1"), Comment("author2","text2"))
    val json = Json.toJson(list)
    Ok(json)

  }


}
