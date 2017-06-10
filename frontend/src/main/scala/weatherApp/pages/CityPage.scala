package weatherApp.pages

import scala.scalajs.js
import org.scalajs.dom

import scala.concurrent.ExecutionContext.Implicits.global
import scala.scalajs.js.Dynamic.{global => g}
import scala.util.{Failure, Success}

import japgolly.scalajs.react._
import japgolly.scalajs.react.vdom.html_<^._
import japgolly.scalajs.react.extra.router.{RouterCtl}

import io.circe.generic.auto._
import io.circe.parser.decode

import weatherApp.router.{AppRouter}
import weatherApp.models.{WeatherForecastResponse}
import weatherApp.config.{Config}

object CityPage {
  case class State(
    var isLoading: Boolean,
    var forecast: Option[WeatherForecastResponse]
  )

  case class Props(
    id: Int,
    name: String,
    ctl: RouterCtl[AppRouter.Page]
  )

  class Backend($: BackendScope[Props, State]) {

    private val props = $.props.runNow()

    def start= Callback {
      val host = Config.AppConfig.apiHost
      dom.ext.Ajax.get(url=s"${host}/forecast?id=${props.id}")
        .onComplete {
          case Success(xhr) => {
            val option = decode[WeatherForecastResponse](xhr.responseText)
            val weatherForecast = option match {
              case Left(failure) => {
                g.console.log(failure.toString())
                None : Option[WeatherForecastResponse]
              }
              case Right(data) => Some(data)
            }
            $.modState(s => {
              s.isLoading = false
              s.forecast = weatherForecast
              s
            }).runNow()
          }
          case Failure(xhr) => {
          }
      }
    }

    def render(props: Props, state: State): VdomElement = {
      if (state.forecast.isDefined) {
        val forecast = state.forecast.get
        return <.div(
          <.div(
            ^.fontWeight := "bold",
            ^.marginBottom := 15.px,
            s"${forecast.city.name}, ${forecast.city.country}",
          ),
          <.div(
            s"isLoading: ${state.isLoading.toString}"
          )
        )
      }
      return <.div()
    }
  }

  val Component = ScalaComponent.builder[Props]("CityPage")
    .initialState(State(
      isLoading = false,
      forecast = None : Option[WeatherForecastResponse]
    ))
    .renderBackend[Backend]
    .componentDidMount(_.backend.start)
    .build
}
