package weatherApp.components

import scala.concurrent.ExecutionContext.Implicits.global
import japgolly.scalajs.react._
import japgolly.scalajs.react.vdom.html_<^._
import japgolly.scalajs.react.extra.router.RouterCtl
import org.scalajs.dom
import weatherApp.config.Config
import weatherApp.models.{CityForUser, OpenWeatherBaseCity, UserResponse, WeatherResponse}
import weatherApp.router.AppRouter
import io.circe.syntax._
import io.circe.generic.auto._
import weatherApp.diode.{AddCityToFavs, AppCircuit, RemoveCityFromFavs}

object WeatherBox {

  case class Props (
    weather: Option[WeatherResponse],
    ctl: RouterCtl[AppRouter.Page],
    userInfo: Option[UserResponse],
    isRemoveBtn: Boolean = false,
    isSaveBtn: Boolean = false
  )

  class Backend(bs: BackendScope[Props, Unit]) {
    val host: String = Config.AppConfig.apiHost

    def addCityForUser(city: OpenWeatherBaseCity, userId: Int, weather: WeatherResponse): Callback = {
      val cityForUser = CityForUser(city, userId).asJson.asInstanceOf[dom.ext.Ajax.InputData]
      Callback {
        dom.ext.Ajax.post(
          url = s"$host/city",
          data = cityForUser,
          headers = Map("Content-Type" -> "application/json")
        ).map(_ => AppCircuit.dispatch(AddCityToFavs(city, weather)))
      }
    }

    def removeCityForUser(city: OpenWeatherBaseCity, userId: Int, weather: WeatherResponse): Callback = {
      val cityForUser = CityForUser(city, userId).asJson.asInstanceOf[dom.ext.Ajax.InputData]
      Callback {
        dom.ext.Ajax.delete(
          url = s"$host/city",
          data = cityForUser,
          headers = Map("Content-Type" -> "application/json")
        ).map(_ => AppCircuit.dispatch(RemoveCityFromFavs(city, weather)))
      }
    }

    def navigateToCityPage(page: AppRouter.CityRoute): Callback = bs.props.flatMap { props =>
      props.ctl.set(page)
    }

    def render(props: Props): VdomElement = {
      <.div(
        props.weather.map(weather => {
          val userInfoOption = props.userInfo
          val iconStr = s"wi-owm-${weather.weather.head.id}"
          val city = OpenWeatherBaseCity(
            id = weather.id,
            name = weather.name,
            lat = weather.coord.lat,
            lon = weather.coord.lon
          )
          <.div(
            ^.cls := "weather-box",
            ^.maxWidth := "400px",
            ^.display := "flex",
            ^.border := "1px solid",
            ^.color := "black",
            <.div(
              ^.margin := "5px",
              ^.display := "flex",
              ^.flexDirection := "row",
              ^.justifyContent := "space-between",
              ^.width := "100%",
              ^.fontSize := 15.px,
              <.div(
                <.div(weather.name),
                <.div(
                  s"${(math rint weather.main.temp * 10) / 10} °C"
                )
              ),
              <.div(
                ^.display := "flex",
                ^.justifyContent := "center",
                ^.alignItems := "center",
                <.div(
                  ^.className := s"wi $iconStr",
                  ^.marginRight := "10px",
                  ^.fontSize := 25.px,
                  ^.fontWeight := "100"
                ),
                <.div(
                  weather.weather.head.main
                )
              )
            ),
            <.div(
              ^.display := "flex",
              ^.flexDirection := "row",
              ^.justifyContent := "center",
              ^.alignItems := "center",
              ^.padding := 5.px,
              WeatherBoxBtn(
                WeatherBoxBtn.Props(
                  "more",
                  navigateToCityPage(AppRouter.CityRoute(weather.name.toLowerCase(), weather.id))
                )
              ),
              userInfoOption.map(userInfo =>
                <.div(
                  ^.display := "flex",
                  WeatherBoxBtn(
                    WeatherBoxBtn.Props("save", addCityForUser(city, userInfo.user.id, props.weather.get))
                  ).when(props.isSaveBtn),
                  WeatherBoxBtn(
                    WeatherBoxBtn.Props("remove", removeCityForUser(city, userInfo.user.id, props.weather.get))
                  ).when(props.isRemoveBtn)
                )
              ).whenDefined
            )
          )
        }).whenDefined
      )
    }
  }

  val Component = ScalaComponent.builder[Props]("WeatherBox")
    .renderBackend[Backend].build

  def apply(props: Props) = Component(props)
}
