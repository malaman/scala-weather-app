package weatherApp.pages

import scala.scalajs.js
import scala.concurrent.ExecutionContext.Implicits.global
import org.scalajs.dom

import japgolly.scalajs.react._
import japgolly.scalajs.react.vdom.html_<^._
import japgolly.scalajs.react.extra.router.RouterCtl
import diode.react.ModelProxy
import io.circe.parser.decode
import io.circe.generic.auto._

import weatherApp.router.AppRouter
import weatherApp.models.WeatherForecastResponse
import weatherApp.diode._
import weatherApp.components.{CityPageHeader, DailyForecastBox, GoogleMaps, MapMarker, WeatherForecastBox}
import weatherApp.config.Config

object FavoritesPage {
  case class Props(
                    proxy: ModelProxy[AppState],
                    ctl: RouterCtl[AppRouter.Page]
                  )

  class Backend($: BackendScope[Props, Unit]) {

    private val props = $.props.runNow()

    def getForecastBoxes(forecast: WeatherForecastResponse) = {
      forecast.list.map(item =>
        WeatherForecastBox.Component.withKey(item.dt)(
          WeatherForecastBox.Props(item)
        )
      )
    }

    def getDailyForecastBoxes(forecast: WeatherForecastResponse) = {
      forecast.daily.map(item =>
        DailyForecastBox.Component.withKey(item.day)(DailyForecastBox.Props(item))
      )
    }

    def mounted: Callback = {
      Callback.log("mounted")
    }

    def render(props: Props): VdomElement = {
      val proxy = props.proxy()
      return <.div(
        "Favorites page"
      )
    }
  }

  val Component = ScalaComponent.builder[Props]("FavoritesPage")
    .renderBackend[Backend]
    .componentDidMount(scope => scope.backend.mounted)
    .build

  def apply(props: Props) = Component(props)
}
