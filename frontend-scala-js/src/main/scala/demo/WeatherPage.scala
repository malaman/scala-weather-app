package demo

import scala.concurrent.ExecutionContext.Implicits.global
import scala.scalajs.js.Dynamic.{global => g}
import scala.util.{Failure, Success}
import scalajs.js.annotation._

import japgolly.scalajs.react._
import japgolly.scalajs.react.vdom.html_<^._
import scala.scalajs.js
import org.scalajs.dom

object WeatherPage {
  @js.native
  @JSImport("lodash.throttle", JSImport.Default)
  private object _throttle extends js.Any
  def throttle = _throttle.asInstanceOf[js.Dynamic]

  case class State(
    var isLoaded: Boolean,
    var inputValue: String
  )

  class Backend($: BackendScope[Unit, State]) {
    def start = Callback {
      // loadWeatherInfo("Berlin")
    }

    def loadWeatherInfo(city: String): Unit = {
      dom.ext.Ajax.get(url=s"http://localhost:9000/weather?city=${city}")
          .onComplete {
              case Success(xhr) => {
                  $.modState(s => {
                    s.isLoaded = true
                    s
                  }).runNow()
              }
              case Failure(xhr) => $.modState(s => {
                s.isLoaded = false
                s
              }).runNow()
          }
    }

    val options = js.Array(
      new Select.Options {
        override val value = "one"
        override val label = "Label one"
      },
      new Select.Options {
        override val value = "two"
        override val label = "Label two"
      }
    )

    def throttleInputValueChange(): js.Dynamic = {
      throttle(() => {
        val city = $.state.runNow().inputValue
        loadWeatherInfo(city)
      }, 400)
    }

    val throttleInput = throttleInputValueChange()

    def onInputValueChange(value: String): Unit = {
      $.modState(s => {
        s.inputValue = value
        s
      }).runNow()
      Callback {
        throttleInput()
      }.runNow()
    }

    def onSelectChange(option: Select.Options) {
      g.console.log(option)
    }

    val select = Select.Component(
      Select.props(
        "form-field-name",
        options,
        "one",
        onInputValueChange,
        onSelectChange
      )
    )()

    def render(s: State) =
      <.div(
        <.div("is XHR loaded: ", s.isLoaded.toString),
        <.div(select)
      )
    }

    val Component = ScalaComponent.builder[Unit]("WeatherPage")
      .initialState(State(isLoaded = false, inputValue = ""))
      .renderBackend[Backend]
      .componentDidMount(_.backend.start)
      .build
}
