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
  case class State(
    var isLoading: Boolean
  )

  case class Props(
    name: String,
    ctl: RouterCtl[AppRouter.Page]
  )

  class Backend($: BackendScope[Props, State]) {

    def render(props: Props, state: State) = {
      <.div(
        s"Info for item #${props.name}",
        <.div(
          s"isLoading: ${state.isLoading.toString}"
        )
      )
    }
  }

  val Component = ScalaComponent.builder[Props]("CityPage")
    .initialState(State(
      isLoading = false,
    ))
    .renderBackend[Backend]
    .build
}
