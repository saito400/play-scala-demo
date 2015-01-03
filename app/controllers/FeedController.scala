package controllers

import models._
import models.Tables._
import play.api._
import play.api.Play.current
import play.api.data.Forms._
import play.api.db.slick._
import play.api.db.slick.Config.driver.simple._
import play.api.db.slick.DBAction
import play.api.mvc._
import play.api.data._

object FeedController extends Controller {

  def index = DBAction { implicit rs =>

    val feeds = Feeds.sortBy(t => t.id).list
    Ok(views.html.feed.list(feeds))
  }

  case class FeedForm(id: Option[Int], name: Option[String], url: Option[String])

  val Feeds = TableQuery[Feed]

  val feedForm = Form(
    mapping(
      "id" -> optional(number),
      "name" -> optional(text),
      "url" -> optional(text)
    )(FeedForm.apply)(FeedForm.unapply)
  )

  def insert = DBAction { implicit rs =>

    feedForm.bindFromRequest.fold(
      error => BadRequest(views.html.feed.list(Nil)),
      form  => {
        val user = FeedRow(0, form.name, form.url)
        Feed.insert(user)
        Redirect(routes.FeedController.index)
      }
    )
  }

  def edit(id: Option[Int]) = DBAction { implicit rs =>
    val form = if(id.isDefined) {
      val feed = Feeds.filter(t => t.id is id.get.bind).first
      feedForm.fill(FeedForm(Some(feed.id), feed.name, feed.url))
    } else {
      feedForm
    }
    Ok(views.html.feed.edit(form))
  }

  def update = DBAction.transaction { implicit rs =>
    feedForm.bindFromRequest.fold(
      error => BadRequest(views.html.feed.edit(error)),
      form  => {
        val feed = FeedRow(form.id.get, form.name, form.url)
        Feed.filter(t => t.id is feed.id.bind).update(feed)
        Redirect(routes.FeedController.index)
      }
    )
  }



}