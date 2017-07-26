package utils

import models.{WeatherForecastResponse, ForecastWeather, DailyForecast, Weather}
import com.github.nscala_time.time.Imports.DateTime
import scala.collection.mutable.Map

case class ForecastWeatherWithDay(forecastWeather: ForecastWeather, day: Int)

object WeatherUtils {
  private def getForecastWithDays(list: List[ForecastWeather]) = {
    var days = List.empty[Int]
    val forecastListWithDay = list.map(item => {
      val day: Int = new DateTime(item.dt * 1000L).dayOfWeek.get
      days =  if (days.contains(day)) days else days :+ day
      ForecastWeatherWithDay(item, day)
    })
    (days, forecastListWithDay)
  }

  private def minMax(a: List[Float]) : (Float, Float) = {
    if (a.isEmpty) {
      return (0, 0)
    }
    a.foldLeft((a.head, a.head)) {
      case ((min, max), e) => (math.min(min, e), math.max(max, e))
    }
  }

  private def getWeatherForDay(list: List[ForecastWeatherWithDay]) = {
    val dayWeather = Map[Int, Int]()
    list.foreach(item => {
      val id = item.forecastWeather.weather.head.id
      if (dayWeather.getOrElse(id, -1) != -1)
        dayWeather(id) = dayWeather(id) + 1
      else
        dayWeather(id) = 1
    })
    val (id, _) = dayWeather.maxBy { case (_, value) => value }
    val option = list.find(_.forecastWeather.weather.head.id == id)
    if (option.isDefined)
      option.get.forecastWeather.weather.head
    else
      Weather(id = -1, main = "", description = "") 
  }
  /**
   * Calculates daily weather based on openweather forecast response
   */
  def getDailyWeather(forecast: WeatherForecastResponse): List[DailyForecast] = {
    val (days, listWithDays) = getForecastWithDays(forecast.list)
    days.map(day => {
      val listForDay = listWithDays.filter(_.day == day)
      val (temp_min, temp_max) = minMax(listForDay.map(_.forecastWeather.main.temp))
      val weather = getWeatherForDay(listForDay)
      DailyForecast(day, weather, temp_min, temp_max)
    })
  }
}
