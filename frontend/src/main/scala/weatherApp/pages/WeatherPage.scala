package weatherApp.pages

import scala.scalajs.js
import js.JSConverters._
import scalajs.js.annotation._
import org.scalajs.dom

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future
import japgolly.scalajs.react._
import japgolly.scalajs.react.vdom.html_<^._
import japgolly.scalajs.react.extra.router.RouterCtl

import io.circe.generic.auto._
import io.circe.parser.decode

import diode.react.ModelProxy
import diode.Action

import weatherApp.models.WeatherResponse
import weatherApp.components.{Select, WeatherBox}
import weatherApp.config.Config
import weatherApp.router.AppRouter
import weatherApp.diode.{AppState, GetWeatherSuggestions, SelectWeather}

object WeatherPage {
  @js.native
  @JSImport("lodash.throttle", JSImport.Default)
  private object _throttle extends js.Any
  def throttle: js.Dynamic = _throttle.asInstanceOf[js.Dynamic]

  case class Props (
    proxy: ModelProxy[AppState],
    ctl: RouterCtl[AppRouter.Page]
  )

  case class State(
    var isLoading: Boolean,
    var inputValue: String,
    var weatherData: List[WeatherResponse],
    var selectOptions: List[Select.Options],
    var selectedWeather: Option[WeatherResponse]
  )

  class Backend($: BackendScope[Props, State]) {

    private val props = $.props.runNow()

    private val dispatch: Action => Callback = props.proxy.dispatchCB

    def getSelectOptions(data: List[WeatherResponse], intputValue: String) = {
      data.zipWithIndex.map { case (item, index) => Select.Options(
        value = s"$intputValue::$index",
        label = s"${item.name}, ${item.sys.country} ${item.weather.head.main} ${(math rint item.main.temp * 10) / 10} °C"
      )}
    }

    def loadWeatherInfo(city: String): Callback = {
      val host = Config.AppConfig.apiHost
      val setLoading = $.modState(s => {
        s.isLoading = true
        s
      })

      val getData = CallbackTo[Future[List[WeatherResponse]]] {
        dom.ext.Ajax.get(url=s"$host/weather?city=$city").map(xhr => {
          val option = decode[List[WeatherResponse]](xhr.responseText)
          option match {
            case Left(failure) => {
              Callback.log(failure.toString())
              List.empty[WeatherResponse]
            }
            case Right(data) => data
          }
        })
      }


      val updateState = (weatherData: Future[List[WeatherResponse]]) => Callback {
        weatherData.map(weather => {
            dispatch(GetWeatherSuggestions(weather)).runNow()
              $.modState(s => {
                s.isLoading = false
                s.weatherData = weather
                s.selectOptions = getSelectOptions(weather, s.inputValue)
                s
              }).runNow()
          })
      }

      setLoading >> getData >>= updateState
    }

    def throttleInputValueChange(): js.Dynamic = {
      throttle(() => {
        val city = $.state.runNow().inputValue
        if (city.nonEmpty) {
          loadWeatherInfo(city).runNow()
        }
      }, 400)
    }

    val throttleInput = throttleInputValueChange()

    def onInputValueChange(value: String): Unit = {
      val selectedValue = try {
        Some(value)
      } catch {
        case e: Exception => None : Option[String]
      }
      $.modState(s => {
        s.inputValue = selectedValue.getOrElse("")
        s
      }).runNow()
      Callback {
        throttleInput()
      }.runNow()
    }

    def onSelectChange(option: Select.Options) = {
      val selectedValue = try {
        Some(option.value)
      } catch {
        case e: Exception => None : Option[String]
      }
      $.modState(s => {
        s.inputValue = selectedValue.getOrElse("")
        if (s.inputValue == "") {
          s.selectOptions = List.empty[Select.Options]
          s.selectedWeather = None: Option[WeatherResponse]

        } else {
          val arr = option.value.split("::")
          val index = if (arr.length == 2) arr(1).toInt else -1
          s.selectedWeather = if (index == -1) None else Some(s.weatherData(index))
        }
        dispatch(SelectWeather(s.selectedWeather)).runNow()
        s
      }).runNow()
    }

    def render(p: Props, s: State) = {
      val proxy = p.proxy()
      val weatherData = proxy.weatherSuggestions
      val select = Select(
        "form-field-name",
        s.selectOptions.toJSArray,
        s.inputValue,
        onInputValueChange,
        onSelectChange,
        pIsLoading = s.isLoading
      )
      <.div(
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
        <.div(
          WeatherBox.Component(WeatherBox.Props(s.selectedWeather, p.ctl))
        )
      )
    }
  }

    val Component = ScalaComponent.builder[Props]("WeatherPage")
      .initialState(State(
        isLoading = false,
        inputValue = "",
        weatherData = List.empty[WeatherResponse],
        selectOptions = List.empty[Select.Options],
        selectedWeather = None : Option[WeatherResponse]
      ))
      .renderBackend[Backend]
      .build
}
