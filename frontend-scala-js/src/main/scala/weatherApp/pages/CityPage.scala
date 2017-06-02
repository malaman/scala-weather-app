package weatherApp.pages

import scala.scalajs.js

import scala.concurrent.ExecutionContext.Implicits.global
import scala.scalajs.js.Dynamic.{global => g}
import scala.util.{Failure, Success}

import japgolly.scalajs.react._
import japgolly.scalajs.react.vdom.html_<^._
import weatherApp.router.{AppRouter}
import japgolly.scalajs.react.extra.router.{RouterCtl}


object CityPage {
  case class Props(name: String, ctl: RouterCtl[AppRouter.Page])
  val Component = ScalaComponent.builder[Props]("City Page")
    .render(p => <.div(s"Info for item #${p.props.name}"))
    .build
}
