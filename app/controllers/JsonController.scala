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

  def index = DBAction { implicit rs =>
    val list = Seq(TestData(0,"data0"),TestData(1,"data1"))
    val json = Json.toJson(list)
    Ok(json)

  }


//  def index = DBAction { implicit rs =>
//
//    val feeds = Feeds.sortBy(t => t.id).list
//    Ok(views.html.feed.list(feeds))
//  }
//
//  case class FeedForm(id: Option[Int], name: Option[String], url: Option[String])
//
//  val Feeds = TableQuery[Feed]
//
//  val feedForm = Form(
//    mapping(
//      "id" -> optional(number),
//      "name" -> optional(text),
//      "url" -> optional(text)
//    )(FeedForm.apply)(FeedForm.unapply)
//  )
//
//  def insert = DBAction { implicit rs =>
//
//    feedForm.bindFromRequest.fold(
//      error => BadRequest(views.html.feed.list(Nil)),
//      form  => {
//        val user = FeedRow(0, form.name, form.url)
//        Feed.insert(user)
//        Redirect(routes.FeedController.index)
//      }
//    )
//  }
//
//  def edit(id: Option[Int]) = DBAction { implicit rs =>
//    val form = if(id.isDefined) {
//      val feed = Feeds.filter(t => t.id === id.get.bind).first
//      feedForm.fill(FeedForm(Some(feed.id), feed.name, feed.url))
//    } else {
//      feedForm
//    }
//    Ok(views.html.feed.edit(form))
//  }
//
//  def update = DBAction { implicit rs =>
//    feedForm.bindFromRequest.fold(
//      error => BadRequest(views.html.feed.edit(error)),
//      form  => {
//        val feed = FeedRow(form.id.get, form.name, form.url)
//        Feed.filter(t => t.id === feed.id.bind).update(feed)
//        Redirect(routes.FeedController.index)
//      }
//    )
//  }
//
//  def delete(id: Int) = DBAction { implicit rs =>
//    //Feed.delete(id) it does not work
//    Feed.filter(t => t.id === id.bind).delete
//    Redirect(routes.FeedController.index)
//  }
//

}
