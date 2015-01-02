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

object Application extends Controller {

  def index = DBAction { implicit rs =>

    val feeds = TableQuery[Feeds]
    Ok(views.html.index(feeds.list))
  }

  case class FeedForm(name: Option[String], url: Option[String])

  val Feeds = TableQuery[Feeds]

  val feedForm = Form(
    mapping(
      "name" -> optional(text),
      "url" -> optional(text)
    )(FeedsRow.apply)(FeedsRow.unapply)
  )

  def insert = DBAction { implicit rs =>
    val feed = feedForm.bindFromRequest.get
    Feeds.insert(feed)

    Redirect(routes.Application.index)
  }


}