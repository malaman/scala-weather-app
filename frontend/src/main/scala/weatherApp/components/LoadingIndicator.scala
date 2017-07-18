package weatherApp.components

import diode.react.ModelProxy
import japgolly.scalajs.react._
import japgolly.scalajs.react.vdom.html_<^._
import weatherApp.diode.AppState

object LoadingIndicator {
  case class Props(proxy: ModelProxy[AppState])

  val Component = ScalaFnComponent[Props](props => {
    val proxy = props.proxy()
    if (proxy.isLoading) {
      <.div(
        ^.position := "absolute",
        ^.top := 100.px,
        ^.left := "50%",
        <.div(^.cls :="loading-indicator")
      )
    } else {
      <.div()
    }
  })

  def apply(props: Props) = Component(props)
}
