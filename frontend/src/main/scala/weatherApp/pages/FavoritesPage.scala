package weatherApp.pages

import japgolly.scalajs.react._
import japgolly.scalajs.react.vdom.html_<^._
import japgolly.scalajs.react.extra.router.RouterCtl
import diode.react.ModelProxy
import weatherApp.router.AppRouter
import weatherApp.diode._
import weatherApp.components._

object FavoritesPage {
  case class Props(
                    proxy: ModelProxy[AppState],
                    ctl: RouterCtl[AppRouter.Page]
                  )

  class Backend(bs: BackendScope[Props, Unit]) {

    def getCities(props: Props) = {
      val proxy = props.proxy()
      proxy.favCitiesWeather.map {city =>
        WeatherBox.Component.withKey(city.id)(WeatherBox.Props(Some(city), props.ctl, proxy.userInfo))
      }
    }

    def mounted: Callback = Callback.log("Mounted!")

    def render(props: Props): VdomElement = {
      return <.div(
        getCities(props).toVdomArray
      )
    }
  }

  val Component = ScalaComponent.builder[Props]("FavoritesPage")
    .renderBackend[Backend]
    .componentDidMount(scope => scope.backend.mounted)
    .build

  def apply(props: Props) = Component(props)
}
