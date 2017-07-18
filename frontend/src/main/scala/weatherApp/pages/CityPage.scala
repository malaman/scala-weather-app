package weatherApp.pages

import diode.react.ModelProxy


import japgolly.scalajs.react._
import japgolly.scalajs.react.vdom.html_<^._
import japgolly.scalajs.react.extra.router.RouterCtl

import weatherApp.router.AppRouter
import weatherApp.models.WeatherForecastResponse
import weatherApp.diode.AppState
import weatherApp.components.{WeatherForecastBox, DailyForecastBox, CityPageHeader}

object CityPage {
  case class State(
    var isLoading: Boolean
  )

  case class Props(
    proxy: ModelProxy[AppState],
    id: Int,
    name: String,
    ctl: RouterCtl[AppRouter.Page]
  )

  class Backend($: BackendScope[Props, State]) {

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

    def render(props: Props, state: State): VdomElement = {
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
          )
        )
      }
      return <.div()
    }
  }

  val Component = ScalaComponent.builder[Props]("CityPage")
    .initialState(State(
      isLoading = false
    ))
    .renderBackend[Backend]
    .build
}
