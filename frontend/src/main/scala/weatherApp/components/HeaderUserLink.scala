package weatherApp.components

import japgolly.scalajs.react.ScalaFnComponent
import japgolly.scalajs.react.vdom.html_<^._
import weatherApp.models.{GithubUser, UserResponse}

object HeaderUserLink {
  case class Props(userInfo: UserResponse)

  val Component = ScalaFnComponent[Props](props => {
    val user = props.userInfo.user
    <.div(
      ^.display := "flex",
      ^.justifyContent := "flex-end",
      <.a(
        ^.display := "flex",
        ^.justifyContent := "center",
        ^.alignItems := "center",
        ^.borderRadius := 3.px,
        ^.padding := 5.px,
        ^.target := "blank",
        ^.href := user.html_url,
        <.img(
          ^.width := 30.px,
          ^.src := user.avatar_url,
          ^.marginRight := 10.px
        ),
        user.name.map(name => name).whenDefined
      )
    )
  })

  def apply(props: Props) = Component(props)
}
