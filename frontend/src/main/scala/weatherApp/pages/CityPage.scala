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

object CityPage {
  case class Props(
    proxy: ModelProxy[AppState],
    id: Int,
    name: String,
    ctl: RouterCtl[AppRouter.Page]
  )

  class Backend($: BackendScope[Props, Unit]) {

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
      val host = Config.AppConfig.apiHost
      Callback {
        AppCircuit.dispatch(ClearForecast())
        AppCircuit.dispatch(SetLoadingState())
      } >>
      $.props.flatMap {props =>
        Callback {
          dom.ext.Ajax.get(url=s"$host/forecast?id=${props.id}").map(xhr => {
            val option = decode[WeatherForecastResponse](xhr.responseText)
            option match {
              case Right(data) => AppCircuit.dispatch(GetWeatherForecast(Some(data)))
              case Left(_) => None
            }
            AppCircuit.dispatch(ClearLoadingState())
          })
        }
      }
    }

    def render(props: Props): VdomElement = {
      val proxy = props.proxy()
      val forecastOption = proxy.forecast
      val weatherOption = proxy.selectedWeather
      if (forecastOption.isDefined) {
        val forecast = forecastOption.get
        val Box = WeatherForecastBox.Component(
          WeatherForecastBox.Props(forecast.list.head)
        )

        return <.div(
          <.div(
            CityPageHeader(
              CityPageHeader.Props(s"${forecast.city.name}, ${forecast.city.country}", weatherOption)
            )
          ),
          <.div(
            ^.display := "flex",
            ^.overflowY := "scroll",
            getForecastBoxes(forecast).toVdomArray
          ),
          <.div(
            ^.border := "1px solid black",
            getDailyForecastBoxes(forecast).toVdomArray
          ),
          <.div(
            ^.height := 400.px,
            ^.width := "100%",
            ^.marginTop := 10.px,
            GoogleMaps(forecast.city.coord.lat, forecast.city.coord.lon, 10,
              MapMarker.Component
                .withRawProp("lat", forecast.city.coord.lat)
                .withRawProp("lng", forecast.city.coord.lon)
                (
                  js.Dynamic.literal(lat = forecast.city.coord.lat, lng = forecast.city.coord.lon)
                )
            )
          )
        )
      }
      return <.div()
    }
  }

  val Component = ScalaComponent.builder[Props]("CityPage")
      .renderBackend[Backend]
      .componentDidMount(scope => scope.backend.mounted)
      .build
}
