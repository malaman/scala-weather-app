package weatherApp.router

import org.scalajs.dom
import scala.util.{Failure, Success}
import scala.scalajs.js.Dynamic.{global => g}

import scala.concurrent.ExecutionContext.Implicits.global
import io.circe.generic.auto._
import io.circe.parser.decode

import japgolly.scalajs.react.extra._
import japgolly.scalajs.react.extra.router._
import japgolly.scalajs.react.vdom.html_<^._
import japgolly.scalajs.react._

import diode.Action
import weatherApp.diode.{AppCircuit, AppModel, AppState, GetWeatherForecast}
import weatherApp.pages.{WeatherPage, CityPage}
import weatherApp.config.{Config}
import weatherApp.models.{WeatherForecastResponse}

object AppRouter {
  sealed trait Page
  case object HomeRoute extends Page
  case class CityRoute(name: String, id: Int) extends Page

  val connection = AppCircuit.connect(_.state)

  val routerConfig = RouterConfigDsl[Page].buildConfig { dsl =>
    import dsl._
    (trimSlashes
      | staticRoute(root, HomeRoute) ~> renderR(renderWeatherPage)
      | dynamicRouteCT(("city" / string(".*") / int).caseClass[CityRoute]) ~> dynRenderR(renderCityPage)
    )
    .notFound(redirectToPage(HomeRoute)(Redirect.Replace))
    .renderWith(layout)
  }

  def renderWeatherPage(ctl: RouterCtl[Page]) = {
    connection(proxy =>
      WeatherPage.Component(WeatherPage.Props(proxy, ctl))
    )
  }

  def renderCityPage(p: CityRoute, ctl: RouterCtl[Page]) = {
    val host = Config.AppConfig.apiHost
    dom.ext.Ajax.get(url=s"${host}/forecast?id=${p.id}")
      .onComplete {
        case Success(xhr) => {
          val option = decode[WeatherForecastResponse](xhr.responseText)
          option match {
            case Left(failure) => {
              g.console.log(failure.toString())
            }
            case Right(data) => {
              AppCircuit.dispatch(GetWeatherForecast(Some(data)))
            }
          }
        }
        case Failure(xhr) => {
        }
    }
    connection(proxy =>
      CityPage.Component(CityPage.Props(proxy, p.id, p.name, ctl))
    )


  }

  def layout (c: RouterCtl[Page], r: Resolution[Page]) =
    <.div(
      <.div(^.cls := "container", r.render())
    )

  val baseUrl = BaseUrl.fromWindowOrigin_/

  val router = Router(baseUrl, routerConfig.logToConsole)
}
