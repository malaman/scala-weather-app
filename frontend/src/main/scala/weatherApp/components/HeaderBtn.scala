package weatherApp.components

import japgolly.scalajs.react.ScalaFnComponent
import japgolly.scalajs.react.vdom.html_<^._

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport

object HeaderBtn {
  @JSImport("../../assets/images/github.png", JSImport.Namespace)
  @js.native
  object GithubLogo extends js.Any

  case class Props(text: String, url: String, isLogin: Boolean = false)

  val Component = ScalaFnComponent[Props](props => {
    val icon = if (props.isLogin) <.img(^.width := 25.px, ^.marginRight := 5.px, ^.src := GithubLogo.asInstanceOf[String])
    else <.div()

    <.div(
      ^.display := "flex",
      ^.justifyContent := "flex-end",
      <.a(
        ^.cls := "secondary-hover",
        ^.display := "flex",
        ^.justifyContent := "center",
        ^.alignItems := "center",
        ^.border := "1px solid black",
        ^.borderRadius := 3.px,
        ^.padding := 5.px,
        ^.textDecoration := "none",
        ^.href := props.url,
        icon,
        props.text
      )
    )
  })

  def apply(props: Props) = {
    GithubLogo
    Component(props)
  }
}
