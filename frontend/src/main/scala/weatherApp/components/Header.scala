package weatherApp.components

import diode.react.ModelProxy
import japgolly.scalajs.react._
import japgolly.scalajs.react.vdom.html_<^._
import weatherApp.config.Config
import weatherApp.diode.AppState

object Header {
  case class Props(proxy: ModelProxy[AppState])

  val Component = ScalaFnComponent[Props](props => {
    val proxy = props.proxy()
    if (proxy.user.isDefined) {
      <.div(
        ^.display := "flex",
        ^.justifyContent := "flex-end",
        HeaderUserLink(
          HeaderUserLink.Props(proxy.user.get)
        ),
        HeaderBtn(
          HeaderBtn.Props(text = "Logout", url = s"${Config.AppConfig.apiHost}/logout")
        )
      )
    } else if (!proxy.isLoading) {
      HeaderBtn(
        HeaderBtn.Props(text = "Login with Github", url = s"${Config.AppConfig.apiHost}/authenticate", isLogin = true)
      )
    } else {
      <.div()
    }
  })

  def apply(props: Props) = Component(props)
}
