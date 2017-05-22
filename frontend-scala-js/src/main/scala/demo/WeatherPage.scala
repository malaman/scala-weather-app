package demo

import scala.scalajs.js
import js.JSConverters._

import scala.concurrent.ExecutionContext.Implicits.global
import scala.scalajs.js.Dynamic.{global => g}
import scala.util.{Failure, Success}
import scalajs.js.annotation._

import japgolly.scalajs.react._
import japgolly.scalajs.react.vdom.html_<^._
import org.scalajs.dom
import demo.models.{WeatherResponse}
import io.circe.generic.auto._
import io.circe.parser.decode


object WeatherPage {
  @js.native
  @JSImport("lodash.throttle", JSImport.Default)
  private object _throttle extends js.Any
  def throttle = _throttle.asInstanceOf[js.Dynamic]

  // this.state = {
  //   inputValue: '',
  //   weatherData: [],
  //   selectOptions: [],
  //   isLoading: false
  // };

  case class State(
    var isLoading: Boolean,
    var inputValue: String,
    var weatherData: Array[WeatherResponse],
    var selectOptions: Array[Select.Options]
  )

  class Backend($: BackendScope[Unit, State]) {
    def start = Callback {
      // loadWeatherInfo("Berlin")
    }

    def getSelectOptions(data: Array[WeatherResponse]) = {
      data.zipWithIndex.map { case (item, index) => new Select.Options {
        override val value = s"${item.name}: ${index}"
        override val label = s"${item.name} ${item.weather(0).main} ${item.main.temp} °C"
      }
    }
    }

    def loadWeatherInfo(city: String): Unit = {
      $.modState(s => {
        s.isLoading = true
        s
      }).runNow()
      dom.ext.Ajax.get(url=s"http://localhost:9000/weather?city=${city}")
          .onComplete {
              case Success(xhr) => {
                val option = decode[Array[WeatherResponse]](xhr.responseText)
                val weatherData = option match {
                  case Left(failure) => Array.empty[WeatherResponse]
                  case Right(data) => data
                }
                $.modState(s => {
                  s.isLoading = false
                  s.weatherData = weatherData
                  s.selectOptions = getSelectOptions(weatherData)
                  s
                }).runNow()
              }
              case Failure(xhr) => {
              }
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

    def render(s: State) = {
      val select = Select.Component(
        Select.props(
          "form-field-name",
          s.selectOptions.toJSArray,
          "",
          onInputValueChange,
          onSelectChange,
          pIsLoading = s.isLoading
        )
      )()

      <.div(
        <.div(select)
      )
    }
  }

    val Component = ScalaComponent.builder[Unit]("WeatherPage")
      .initialState(State(
        isLoading = false,
        inputValue = "",
        weatherData = Array.empty[WeatherResponse],
        selectOptions = Array.empty[Select.Options]
      ))
      .renderBackend[Backend]
      .componentDidMount(_.backend.start)
      .build
}
