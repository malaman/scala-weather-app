package weatherApp.router

import japgolly.scalajs.react.extra._
import japgolly.scalajs.react.extra.router._
import japgolly.scalajs.react.vdom.html_<^._

import weatherApp.pages.{WeatherPage, CityPage}

object AppRouter {
  sealed trait Page
  case object HomeRoute extends Page
  case class CityRoute(name: String, id: Int) extends Page

  val routerConfig = RouterConfigDsl[Page].buildConfig { dsl =>
    import dsl._
    (trimSlashes
      | staticRoute(root, HomeRoute) ~> renderR(renderWeatherPage)
      | dynamicRouteCT(("city" / string(".*") / int).caseClass[CityRoute]) ~> dynRenderR(renderCityPage)
    )
    .notFound(redirectToPage(HomeRoute)(Redirect.Replace))
    .renderWith(layout)
  }

  def renderWeatherPage(c: RouterCtl[Page]) = {
    WeatherPage.Component(WeatherPage.Props(c))
  }

  def renderCityPage(p: CityRoute, c: RouterCtl[Page]) = {
    CityPage.Component(CityPage.Props(p.id, p.name, c))
  }

  def layout (c: RouterCtl[Page], r: Resolution[Page]) =
    <.div(
      <.div(^.cls := "container", r.render())
    )

  val baseUrl = BaseUrl.fromWindowOrigin_/

  val router = Router(baseUrl, routerConfig.logToConsole)
}
