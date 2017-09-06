package weatherApp.components

import diode.react.ModelProxy
import japgolly.scalajs.react._
import japgolly.scalajs.react.extra.router.{Resolution, RouterCtl}
import japgolly.scalajs.react.vdom.html_<^._
import weatherApp.config.Config
import weatherApp.diode.AppState
import weatherApp.router.AppRouter.Page

object Header {
  case class Props(
                    proxy: ModelProxy[AppState],
                    ctl: RouterCtl[Page],
                    resolution: Resolution[Page]
                  )

  val Component = ScalaFnComponent[Props](props => {
    val proxy = props.proxy()
    val userInfo = proxy.userInfo
    <.div(
      ^.display := "flex",
      ^.justifyContent := "space-between",
      HeaderNav(HeaderNav.Props(props.proxy, props.ctl)),
      proxy.userInfo.map(userInfo =>
        <.div(
          ^.display := "flex",
          ^.justifyContent := "flex-end",
          HeaderUserLink(
            HeaderUserLink.Props(userInfo)
          ),
          HeaderBtn(
            HeaderBtn.Props(text = "Logout", url = s"${Config.AppConfig.apiHost}/logout")
          )
        )
      ).whenDefined,
      HeaderBtn(
        HeaderBtn.Props(text = "Login with Github", url = s"${Config.AppConfig.apiHost}/authenticate", isLogin = true)
      ).when(!proxy.isLoading && proxy.userInfo.isEmpty)
    )
  })

  def apply(props: Props) = Component(props)
}
