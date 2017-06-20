package utils

import models.{WeatherForecastResponse, ForecastWeather, DailyForecast}
import com.github.nscala_time.time.Imports.{DateTime}
import scala.collection.mutable.Map

case class ForecastWeatherWithDay(forecastWeather: ForecastWeather, day: Int)

object WeatherUtils {
	private def getForecastWithDays(list: List[ForecastWeather]) = {
  	var days = Set.empty[Int]
  	val forecastListWithDay = list.map(item => {
  		val day = new DateTime(item.dt * 1000L).dayOfWeek.get  		  		
  		days = days + day
  		ForecastWeatherWithDay(item, day)
		})
		(days.toList, forecastListWithDay)		
	}

	private def minMax(a: List[Float]) : (Float, Float) = {
  	if (a.isEmpty) {
  		return (0, 0)
  	}
  	a.foldLeft((a(0), a(0))) { 
  		case ((min, max), e) => (math.min(min, e), math.max(max, e))
  	}
	}

	private def getWeatherForDay(list: List[ForecastWeatherWithDay]) = {
		val dayWeather = Map[Int, Int]()
		list.foreach(item => {
			val id = item.forecastWeather.weather(0).id
			if (dayWeather.getOrElse(id, -1) != -1)
				dayWeather(id) = dayWeather(id) + 1
			else
				dayWeather(id) = 1
		})
		val (id, value) = dayWeather.maxBy { case (key, value) => value }
		list.filter(_.forecastWeather.weather(0).id == id)(0).forecastWeather.weather(0)
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
		}).sortBy(_.day)
  }
}
