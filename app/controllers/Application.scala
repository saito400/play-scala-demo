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

    val feeds = TableQuery[Feed]
    Ok(views.html.index(feeds.list))
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
      error => BadRequest(views.html.index(Nil)),
      form  => {
        val user = FeedRow(0, form.name, form.url)
        Feed.insert(user)
        Redirect(routes.Application.index)
      }
    )
  }


}