package controllers

import play.api.mvc.Controller
import play.api.db.slick.DBAction

object SiteController  extends Controller {

  def index = DBAction { implicit rs =>


    Ok(views.html.site.list())
  }



}
