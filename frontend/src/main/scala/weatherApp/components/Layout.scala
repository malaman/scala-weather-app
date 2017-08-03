package weatherApp.components

import japgolly.scalajs.react._
import japgolly.scalajs.react.extra.router.{Resolution, RouterCtl}
import japgolly.scalajs.react.vdom.html_<^._
import weatherApp.diode.AppCircuit.connect
import weatherApp.router.AppRouter.{Page}

object Layout {
  val connection = connect(_.state)

  case class Props(
                    ctl: RouterCtl[Page],
                    resolution: Resolution[Page]
                  )

  class Backend($: BackendScope[Props, Unit]) {

    def mounted: Callback = {
      Callback.log("mounted")
    }

    def render(props: Props): VdomElement = {
      <.div(
        <.div(
          ^.cls := "container",
          Header()
        ),
        <.div(^.cls := "container", props.resolution.render()),
        connection(proxy => LoadingIndicator(LoadingIndicator.Props(proxy)))
      )
    }
  }

  val Component = ScalaComponent.builder[Props]("Layout")
    .renderBackend[Backend]
    .componentDidMount(scope => scope.backend.mounted)
    .build

  def apply(props: Props) = Component(props)
}
