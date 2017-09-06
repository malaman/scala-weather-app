package weatherApp.components

import diode.react.ModelProxy
import japgolly.scalajs.react._
import japgolly.scalajs.react.extra.router.RouterCtl
import japgolly.scalajs.react.vdom.html_<^._
import weatherApp.diode.AppState
import weatherApp.router.AppRouter
import weatherApp.router.AppRouter.Page

object HeaderNav {
  case class Props(proxy: ModelProxy[AppState], ctl: RouterCtl[Page])

  val NavLink = ScalaFnComponent[TagMod] { el =>
    <.div(el(
      ^.cls := "secondary-hover",
      ^.fontWeight := "bold",
      ^.padding := 10.px
    )
  )
  }

  val Component = ScalaFnComponent[Props](props => {
    val proxy = props.proxy()
    val userInfoOpt = proxy.userInfo
    val homeLink = props.ctl.link(AppRouter.HomeRoute)
    val favLink = props.ctl.link(AppRouter.FavoritesRoute)

    <.div(
      ^.display := "flex",
      NavLink(homeLink("Home")),
      NavLink(favLink("Favorites").when(userInfoOpt.isDefined))
    )
  })

  def apply(props: Props) = Component(props)
}
