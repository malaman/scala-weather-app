package weatherApp.components

import diode.react.ModelProxy
import org.scalajs.dom

import scala.concurrent.ExecutionContext.Implicits.global
import japgolly.scalajs.react._
import japgolly.scalajs.react.extra.router.{Resolution, RouterCtl}
import japgolly.scalajs.react.vdom.html_<^._
import weatherApp.config.Config
import weatherApp.diode.AppCircuit.connect
import weatherApp.diode._
import weatherApp.models.{GithubUser, OpenWeatherBaseCity, UserResponse, WeatherResponse}
import weatherApp.router.AppRouter.Page
import io.circe.parser.decode
import io.circe.generic.auto._

import scala.concurrent.Future

object Layout {
  val connection = connect(_.state)

  case class Props(
                    proxy: ModelProxy[AppState],
                    ctl: RouterCtl[Page],
                    resolution: Resolution[Page]
                  )

  class Backend(bs: BackendScope[Props, Unit]) {
    val host: String = Config.AppConfig.apiHost

    def getUserResponse = CallbackTo[Future[UserResponse]] {
      AppCircuit.dispatch(SetLoadingState())
      dom.ext.Ajax
        .get(url=s"$host/user-info", withCredentials=true)
        .map {xhr =>
          val option = decode[UserResponse](xhr.responseText)
          option match {
            case Left(failure) => UserResponse(GithubUser(), List.empty[OpenWeatherBaseCity])
            case Right(data) => data
          }
        }
    }

    def dispatchUserInfo(userInfoFuture: Future[UserResponse]) = CallbackTo[Future[UserResponse]] {
      userInfoFuture.map {userInfo =>
        val userInfoOption = if (userInfo.user.id != -1) Some(userInfo) else None
        AppCircuit.dispatch(ClearLoadingState())
        AppCircuit.dispatch(GetUserInfo(userInfoOption))
        userInfo
      }
    }

    def loadAndDispatchCitiesWeather(userInfoFuture: Future[UserResponse]) = Callback {
      userInfoFuture.map { userInfo =>
        userInfo.cities.map {city =>
          dom.ext.Ajax.get(url=s"$host/weather-city?id=${city.id}").map {xhr =>
            val option = decode[WeatherResponse](xhr.responseText)
            option match {
              case Left(_) => None
              case Right(data) => AppCircuit.dispatch(GetWeatherForFavCity(data))
            }
          }
        }
      }
    }

    def mounted: Callback = getUserResponse >>= dispatchUserInfo >>= loadAndDispatchCitiesWeather

    def render(props: Props): VdomElement = {
      <.div(
        <.div(
          ^.cls := "container",
          connection(proxy => Header(Header.Props(proxy, props.ctl, props.resolution)))
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
