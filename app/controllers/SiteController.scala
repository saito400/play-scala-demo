package controllers

import models.Tables._
import play.api.mvc.Controller
import play.api.db.slick._

//import play.api.db.slick.Config.driver.simple._  following Driver needed to be used instead of this to Delete records
import scala.slick.driver.PostgresDriver.simple._

object SiteController  extends Controller {

  val Feeds = TableQuery[Feed]

  def index = DBAction { implicit rs =>




    val feeds = Feeds.sortBy(t => t.id).list



    Ok(views.html.site.list(feeds))





  }



}
