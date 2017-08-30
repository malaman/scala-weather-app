package weatherApp.components

import org.scalajs.dom

import scala.concurrent.ExecutionContext.Implicits.global
import japgolly.scalajs.react._
import japgolly.scalajs.react.extra.router.{Resolution, RouterCtl}
import japgolly.scalajs.react.vdom.html_<^._
import weatherApp.config.Config
import weatherApp.diode.AppCircuit.connect
import weatherApp.diode.{AppCircuit, ClearLoadingState, GetUserInfo, SetLoadingState}
import weatherApp.models.UserResponse
import weatherApp.router.AppRouter.Page
import io.circe.parser.decode
import io.circe.generic.auto._

object Layout {
  val connection = connect(_.state)

  case class Props(
                    ctl: RouterCtl[Page],
                    resolution: Resolution[Page]
                  )

  class Backend($: BackendScope[Props, Unit]) {

    def mounted: Callback = {
      val host: String = Config.AppConfig.apiHost
      AppCircuit.dispatch(SetLoadingState())
      Callback {
        dom.ext.Ajax
          .get(url=s"$host/user-info", withCredentials=true)
          .map {xhr =>
            val option = decode[UserResponse](xhr.responseText)
            option match {
              case Left(failure) => None
              case Right(data) => AppCircuit.dispatch(GetUserInfo(Some(data.user)))
            }
            AppCircuit.dispatch(ClearLoadingState())
          }
      }
    }

    def render(props: Props): VdomElement = {
      <.div(
        <.div(
          ^.cls := "container",
          connection(proxy => Header(Header.Props(proxy)))
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
