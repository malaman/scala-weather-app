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

  object Options {
    def apply(value: String, label: String) =  js.Dynamic.literal(value = value, label = label).asInstanceOf[Options]
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
      val name = pName
      val options = pOptions
      val value = pValue
      val onInputChange = pOnInputChange
      val onChange = pOnChange
      val isLoading = pIsLoading
      val backspaceRemoves = false
    }
  }

  def apply(    pName: String,
                pOptions: js.Array[Options],
                pValue: String,
                pOnInputChange: js.Function1[String, Unit],
                pOnChange: js.Function1[Options, Unit],
                pIsLoading: Boolean = false
           ) = Component(props(pName, pOptions, pValue, pOnInputChange, pOnChange, pIsLoading))()
}
