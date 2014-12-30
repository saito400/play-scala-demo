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

object Application extends Controller {

  def index = DBAction { implicit rs =>

    val feeds = TableQuery[Feeds]
    Ok(views.html.index(feeds.list))
  }

}