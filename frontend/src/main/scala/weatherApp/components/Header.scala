package weatherApp.components

import japgolly.scalajs.react._
import japgolly.scalajs.react.vdom.html_<^._

import weatherApp.config.Config

object Header {
  val Component = ScalaFnComponent[Unit](props => {
    <.div(
      ^.display := "flex",
      ^.justifyContent := "flex-end",
      <.a(
        ^.marginRight := 5.px,
        ^.href := s"${Config.AppConfig.apiHost}/authenticate",
        "Authenticate"
      ),
      <.a(
        ^.href := s"${Config.AppConfig.apiHost}/logout",
        "Logout"
      )
    )
  })

  def apply() = Component()
}
