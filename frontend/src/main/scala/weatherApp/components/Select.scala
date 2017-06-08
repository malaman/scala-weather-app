package weatherApp.components

import scalajs.js
import scalajs.js.annotation._
import japgolly.scalajs.react._

object Select {
  @JSImport("react-select", JSImport.Default)
  @js.native
  object JsComp extends js.Any

  @JSImport("react-select/dist/react-select.css", JSImport.Namespace)
  @js.native
  object CSS extends js.Any
  CSS

  @ScalaJSDefined
  trait Options extends js.Object {
    val value: String
    val label: String
  }

  @ScalaJSDefined
  trait Props extends js.Object {
    val name: String
    val options: js.Array[Options]
    val value: String
    val onInputChange: js.Function1[String, Unit]
    val onChange: js.Function1[Options, Unit]
    val isLoading: Boolean
    val backspaceRemoves: Boolean
  }

  val Component = JsFnComponent[Props, Children.Varargs](JsComp)

  def props(
    pName: String,
    pOptions: js.Array[Options],
    pValue: String,
    pOnInputChange: js.Function1[String, Unit],
    pOnChange: js.Function1[Options, Unit],
    pIsLoading: Boolean = false
  ): Props = {
    new Props {
      override val name = pName
      override val options = pOptions
      override val value = pValue
      override val onInputChange = pOnInputChange
      override val onChange = pOnChange
      override val isLoading = pIsLoading
      override val backspaceRemoves = false
    }
  }
}
