package weatherApp.components

import scala.scalajs.js
import js.JSConverters._

import scala.concurrent.ExecutionContext.Implicits.global
import scala.scalajs.js.Dynamic.{global => g}
import scala.util.{Failure, Success}
import scalajs.js.annotation._

import japgolly.scalajs.react._
import japgolly.scalajs.react.vdom.html_<^._
import org.scalajs.dom
import weatherApp.models.{WeatherResponse}
import io.circe.generic.auto._
import io.circe.parser.decode

object WeatherPage {
  @js.native
  @JSImport("lodash.throttle", JSImport.Default)
  private object _throttle extends js.Any
  def throttle = _throttle.asInstanceOf[js.Dynamic]

  case class State(
    var isLoading: Boolean,
    var inputValue: String,
    var weatherData: Array[WeatherResponse],
    var selectOptions: Array[Select.Options]
  )

  class Backend($: BackendScope[Unit, State]) {
    def getSelectOptions(data: Array[WeatherResponse], intputValue: String) = {
      data.zipWithIndex.map { case (item, index) => new Select.Options {
        override val value = s"${intputValue}::${index}"
        override val label = s"${item.name}, ${item.sys.country} ${item.weather(0).main} ${(math rint item.main.temp * 10) / 10} °C"
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
                  s.selectOptions = getSelectOptions(weatherData, s.inputValue)
                  s
                }).runNow()
              }
              case Failure(xhr) => {
              }
          }
    }

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

    def onSelectChange(option: Select.Options) = {
      var selectedValue = try {
        Some(option.value);
      } catch {
        case e: Exception => None : Option[String]
      }
      $.modState(s => {
        s.inputValue = selectedValue.getOrElse("")
        if (s.inputValue == "") {
          s.selectOptions = Array.empty[Select.Options]
        }
        s
      }).runNow()
    }

    def render(s: State) = {
      val select = Select.Component(
        Select.props(
          "form-field-name",
          s.selectOptions.toJSArray,
          s.inputValue,
          onInputValueChange,
          onSelectChange,
          pIsLoading = s.isLoading
        )
      )()
      val tableProps = try {
        val arr = s.inputValue.split("::")
        val index = if (arr.length == 2) arr(1).toInt else -1
        if (index == -1) None else Some(s.weatherData(index))
      } catch {
        case e: Exception => None
      }
      <.div(
        ^.width := 1000.px,
        ^.margin := "0 auto",
        ^.className := "weather-page",
        <.div(
          ^.className := "weather-page__label",
          "Enter city to get weather: "
        ),
        <.div(
            ^.marginBottom := 10.px,
            select
        ),
        <.div(WeatherBox.Component(tableProps))
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
      .build
}
