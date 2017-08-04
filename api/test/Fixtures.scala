import play.api.mvc.Results
import play.api.test.Helpers.stubControllerComponents

object ActionMocks {

  def getWeather() = stubControllerComponents().actionBuilder {
    Results.Ok(Fixtures.weatherResponse)
  }
  def getWeatherList() = stubControllerComponents().actionBuilder {
    Results.Ok(Fixtures.findWeatherResponse)
  }
  def getWeatherForecast() = stubControllerComponents().actionBuilder {
    Results.Ok(Fixtures.forecastResponse)
  }
  def getGithubTokenResponse() = stubControllerComponents().actionBuilder {
    Results.Ok(Fixtures.githubTokenResponse)
  }
  def getGithubUserResponse() = stubControllerComponents().actionBuilder {
    Results.Ok(Fixtures.githubUserResponse)
  }
}

object Fixtures {
  val Eps = 1e-3
  val findWeatherResponse: String =
    """
      |{
      |  "message": "like",
      |  "cod": "200",
      |  "count": 5,
      |  "list": [
      |    {
      |      "id": 2950159,
      |      "name": "Berlin",
      |      "coord": {
      |        "lat": 52.5244,
      |        "lon": 13.4105
      |      },
      |      "main": {
      |        "temp": 14,
      |        "pressure": 1018,
      |        "humidity": 71,
      |        "temp_min": 14,
      |        "temp_max": 14
      |      },
      |      "dt": 1499928600,
      |      "wind": {
      |        "speed": 6.2,
      |        "deg": 280
      |      },
      |      "sys": {
      |        "country": "DE"
      |      },
      |      "rain": null,
      |      "snow": null,
      |      "clouds": {
      |        "all": 75
      |      },
      |      "weather": [
      |        {
      |          "id": 803,
      |          "main": "Clouds",
      |          "description": "broken clouds",
      |          "icon": "04d"
      |        }
      |      ]
      |    },
      |    {
      |      "id": 2950158,
      |      "name": "Berlin",
      |      "coord": {
      |        "lat": 54.0333,
      |        "lon": 10.45
      |      },
      |      "main": {
      |        "temp": 14.51,
      |        "pressure": 1019,
      |        "humidity": 71,
      |        "temp_min": 14,
      |        "temp_max": 15
      |      },
      |      "dt": 1499928600,
      |      "wind": {
      |        "speed": 3.1,
      |        "deg": 270
      |      },
      |      "sys": {
      |        "country": "DE"
      |      },
      |      "rain": null,
      |      "snow": null,
      |      "clouds": {
      |        "all": 75
      |      },
      |      "weather": [
      |        {
      |          "id": 803,
      |          "main": "Clouds",
      |          "description": "broken clouds",
      |          "icon": "04d"
      |        }
      |      ]
      |    },
      |    {
      |      "id": 3587266,
      |      "name": "Berlin",
      |      "coord": {
      |        "lat": 13.5,
      |        "lon": -88.5334
      |      },
      |      "main": {
      |        "temp": 24,
      |        "pressure": 1012,
      |        "humidity": 94,
      |        "temp_min": 24,
      |        "temp_max": 24
      |      },
      |      "dt": 1499928600,
      |      "wind": {
      |        "speed": 1
      |      },
      |      "sys": {
      |        "country": "SV"
      |      },
      |      "rain": null,
      |      "snow": null,
      |      "clouds": {
      |        "all": 20
      |      },
      |      "weather": [
      |        {
      |          "id": 801,
      |          "main": "Clouds",
      |          "description": "few clouds",
      |          "icon": "02n"
      |        }
      |      ]
      |    },
      |    {
      |      "id": 3614789,
      |      "name": "Berlin",
      |      "coord": {
      |        "lat": 14.8333,
      |        "lon": -88.5
      |      },
      |      "main": {
      |        "temp": 18.67,
      |        "pressure": 949.55,
      |        "humidity": 96,
      |        "temp_min": 18.67,
      |        "temp_max": 18.67,
      |        "sea_level": 1027.37,
      |        "grnd_level": 949.55
      |      },
      |      "dt": 1499931001,
      |      "wind": {
      |        "speed": 0.88,
      |        "deg": 229.502
      |      },
      |      "sys": {
      |        "country": "HN"
      |      },
      |      "rain": null,
      |      "snow": null,
      |      "clouds": {
      |        "all": 32
      |      },
      |      "weather": [
      |        {
      |          "id": 802,
      |          "main": "Clouds",
      |          "description": "scattered clouds",
      |          "icon": "03n"
      |        }
      |      ]
      |    },
      |    {
      |      "id": 4348460,
      |      "name": "Berlin",
      |      "coord": {
      |        "lat": 38.3226,
      |        "lon": -75.2177
      |      },
      |      "main": {
      |        "temp": 26.75,
      |        "pressure": 1018,
      |        "humidity": 88,
      |        "temp_min": 26,
      |        "temp_max": 28
      |      },
      |      "dt": 1499928840,
      |      "wind": {
      |        "speed": 2.6,
      |        "deg": 230
      |      },
      |      "sys": {
      |        "country": "US"
      |      },
      |      "rain": null,
      |      "snow": null,
      |      "clouds": {
      |        "all": 1
      |      },
      |      "weather": [
      |        {
      |          "id": 800,
      |          "main": "Clear",
      |          "description": "sky is clear",
      |          "icon": "01n"
      |        }
      |      ]
      |    }
      |  ]
      |}
    """.stripMargin
  val weatherResponse: String =
    """
      |  {
      |  "coord": {
      |    "lon": 13.41,
      |    "lat": 52.52
      |  },
      |  "weather": [
      |    {
      |      "id": 803,
      |      "main": "Clouds",
      |      "description": "broken clouds",
      |      "icon": "04d"
      |    }
      |  ],
      |  "base": "stations",
      |  "main": {
      |    "temp": 17.49,
      |    "pressure": 1020,
      |    "humidity": 68,
      |    "temp_min": 17,
      |    "temp_max": 18
      |  },
      |  "visibility": 10000,
      |  "wind": {
      |    "speed": 0.5
      |  },
      |  "clouds": {
      |    "all": 75
      |  },
      |  "dt": 1499322000,
      |  "sys": {
      |    "type": 1,
      |    "id": 4892,
      |    "message": 0.0053,
      |    "country": "DE",
      |    "sunrise": 1499309530,
      |    "sunset": 1499369382
      |  },
      |  "id": 2950159,
      |  "name": "Berlin",
      |  "cod": 200
      |  }
      |
    """.stripMargin
  val forecastResponse: String =
    """
      |{
      |  "cod": "200",
      |  "message": 0.0047,
      |  "cnt": 37,
      |  "list": [
      |    {
      |      "dt": 1499763600,
      |      "main": {
      |        "temp": 19.08,
      |        "temp_min": 16.5,
      |        "temp_max": 19.08,
      |        "pressure": 1017.9,
      |        "sea_level": 1023.59,
      |        "grnd_level": 1017.9,
      |        "humidity": 100,
      |        "temp_kf": 2.58
      |      },
      |      "weather": [
      |        {
      |          "id": 500,
      |          "main": "Rain",
      |          "description": "light rain",
      |          "icon": "10d"
      |        }
      |      ],
      |      "clouds": {
      |        "all": 88
      |      },
      |      "wind": {
      |        "speed": 2.61,
      |        "deg": 199.001
      |      },
      |      "rain": {
      |        "3h": 1.59
      |      },
      |      "sys": {
      |        "pod": "d"
      |      },
      |      "dt_txt": "2017-07-11 09:00:00"
      |    },
      |    {
      |      "dt": 1499774400,
      |      "main": {
      |        "temp": 20.46,
      |        "temp_min": 18.53,
      |        "temp_max": 20.46,
      |        "pressure": 1017.65,
      |        "sea_level": 1023.35,
      |        "grnd_level": 1017.65,
      |        "humidity": 99,
      |        "temp_kf": 1.94
      |      },
      |      "weather": [
      |        {
      |          "id": 500,
      |          "main": "Rain",
      |          "description": "light rain",
      |          "icon": "10d"
      |        }
      |      ],
      |      "clouds": {
      |        "all": 92
      |      },
      |      "wind": {
      |        "speed": 4.56,
      |        "deg": 226.51
      |      },
      |      "rain": {
      |        "3h": 0.515
      |      },
      |      "sys": {
      |        "pod": "d"
      |      },
      |      "dt_txt": "2017-07-11 12:00:00"
      |    },
      |    {
      |      "dt": 1499785200,
      |      "main": {
      |        "temp": 21.51,
      |        "temp_min": 20.21,
      |        "temp_max": 21.51,
      |        "pressure": 1017.6,
      |        "sea_level": 1023.35,
      |        "grnd_level": 1017.6,
      |        "humidity": 95,
      |        "temp_kf": 1.29
      |      },
      |      "weather": [
      |        {
      |          "id": 500,
      |          "main": "Rain",
      |          "description": "light rain",
      |          "icon": "10d"
      |        }
      |      ],
      |      "clouds": {
      |        "all": 24
      |      },
      |      "wind": {
      |        "speed": 4.82,
      |        "deg": 244.001
      |      },
      |      "rain": {
      |        "3h": 0.135
      |      },
      |      "sys": {
      |        "pod": "d"
      |      },
      |      "dt_txt": "2017-07-11 15:00:00"
      |    },
      |    {
      |      "dt": 1499796000,
      |      "main": {
      |        "temp": 20.8,
      |        "temp_min": 20.15,
      |        "temp_max": 20.8,
      |        "pressure": 1017.7,
      |        "sea_level": 1023.37,
      |        "grnd_level": 1017.7,
      |        "humidity": 86,
      |        "temp_kf": 0.65
      |      },
      |      "weather": [
      |        {
      |          "id": 500,
      |          "main": "Rain",
      |          "description": "light rain",
      |          "icon": "10d"
      |        }
      |      ],
      |      "clouds": {
      |        "all": 48
      |      },
      |      "wind": {
      |        "speed": 4.67,
      |        "deg": 256.503
      |      },
      |      "rain": {
      |        "3h": 0.0050000000000026
      |      },
      |      "sys": {
      |        "pod": "d"
      |      },
      |      "dt_txt": "2017-07-11 18:00:00"
      |    },
      |    {
      |      "dt": 1499806800,
      |      "main": {
      |        "temp": 18.16,
      |        "temp_min": 18.16,
      |        "temp_max": 18.16,
      |        "pressure": 1018.44,
      |        "sea_level": 1024.1,
      |        "grnd_level": 1018.44,
      |        "humidity": 86,
      |        "temp_kf": 0
      |      },
      |      "weather": [
      |        {
      |          "id": 500,
      |          "main": "Rain",
      |          "description": "light rain",
      |          "icon": "10n"
      |        }
      |      ],
      |      "clouds": {
      |        "all": 36
      |      },
      |      "wind": {
      |        "speed": 4.33,
      |        "deg": 258.005
      |      },
      |      "rain": {
      |        "3h": 0.009999999999998
      |      },
      |      "sys": {
      |        "pod": "n"
      |      },
      |      "dt_txt": "2017-07-11 21:00:00"
      |    },
      |    {
      |      "dt": 1499817600,
      |      "main": {
      |        "temp": 15.3,
      |        "temp_min": 15.3,
      |        "temp_max": 15.3,
      |        "pressure": 1018.18,
      |        "sea_level": 1023.87,
      |        "grnd_level": 1018.18,
      |        "humidity": 93,
      |        "temp_kf": 0
      |      },
      |      "weather": [
      |        {
      |          "id": 801,
      |          "main": "Clouds",
      |          "description": "few clouds",
      |          "icon": "02n"
      |        }
      |      ],
      |      "clouds": {
      |        "all": 24
      |      },
      |      "wind": {
      |        "speed": 2.66,
      |        "deg": 246.501
      |      },
      |      "rain": {
      |
      |      },
      |      "sys": {
      |        "pod": "n"
      |      },
      |      "dt_txt": "2017-07-12 00:00:00"
      |    },
      |    {
      |      "dt": 1499828400,
      |      "main": {
      |        "temp": 14.14,
      |        "temp_min": 14.14,
      |        "temp_max": 14.14,
      |        "pressure": 1017.22,
      |        "sea_level": 1022.91,
      |        "grnd_level": 1017.22,
      |        "humidity": 95,
      |        "temp_kf": 0
      |      },
      |      "weather": [
      |        {
      |          "id": 500,
      |          "main": "Rain",
      |          "description": "light rain",
      |          "icon": "10d"
      |        }
      |      ],
      |      "clouds": {
      |        "all": 64
      |      },
      |      "wind": {
      |        "speed": 1.81,
      |        "deg": 192.002
      |      },
      |      "rain": {
      |        "3h": 0.060000000000002
      |      },
      |      "sys": {
      |        "pod": "d"
      |      },
      |      "dt_txt": "2017-07-12 03:00:00"
      |    },
      |    {
      |      "dt": 1499839200,
      |      "main": {
      |        "temp": 15.99,
      |        "temp_min": 15.99,
      |        "temp_max": 15.99,
      |        "pressure": 1016.7,
      |        "sea_level": 1022.33,
      |        "grnd_level": 1016.7,
      |        "humidity": 100,
      |        "temp_kf": 0
      |      },
      |      "weather": [
      |        {
      |          "id": 500,
      |          "main": "Rain",
      |          "description": "light rain",
      |          "icon": "10d"
      |        }
      |      ],
      |      "clouds": {
      |        "all": 92
      |      },
      |      "wind": {
      |        "speed": 2.21,
      |        "deg": 228.502
      |      },
      |      "rain": {
      |        "3h": 1.405
      |      },
      |      "sys": {
      |        "pod": "d"
      |      },
      |      "dt_txt": "2017-07-12 06:00:00"
      |    },
      |    {
      |      "dt": 1499850000,
      |      "main": {
      |        "temp": 16.55,
      |        "temp_min": 16.55,
      |        "temp_max": 16.55,
      |        "pressure": 1015.81,
      |        "sea_level": 1021.46,
      |        "grnd_level": 1015.81,
      |        "humidity": 100,
      |        "temp_kf": 0
      |      },
      |      "weather": [
      |        {
      |          "id": 500,
      |          "main": "Rain",
      |          "description": "light rain",
      |          "icon": "10d"
      |        }
      |      ],
      |      "clouds": {
      |        "all": 100
      |      },
      |      "wind": {
      |        "speed": 2.26,
      |        "deg": 221.5
      |      },
      |      "rain": {
      |        "3h": 0.88
      |      },
      |      "sys": {
      |        "pod": "d"
      |      },
      |      "dt_txt": "2017-07-12 09:00:00"
      |    },
      |    {
      |      "dt": 1499860800,
      |      "main": {
      |        "temp": 17.36,
      |        "temp_min": 17.36,
      |        "temp_max": 17.36,
      |        "pressure": 1014.97,
      |        "sea_level": 1020.66,
      |        "grnd_level": 1014.97,
      |        "humidity": 99,
      |        "temp_kf": 0
      |      },
      |      "weather": [
      |        {
      |          "id": 500,
      |          "main": "Rain",
      |          "description": "light rain",
      |          "icon": "10d"
      |        }
      |      ],
      |      "clouds": {
      |        "all": 92
      |      },
      |      "wind": {
      |        "speed": 5.26,
      |        "deg": 213.003
      |      },
      |      "rain": {
      |        "3h": 0.35
      |      },
      |      "sys": {
      |        "pod": "d"
      |      },
      |      "dt_txt": "2017-07-12 12:00:00"
      |    },
      |    {
      |      "dt": 1499871600,
      |      "main": {
      |        "temp": 16.56,
      |        "temp_min": 16.56,
      |        "temp_max": 16.56,
      |        "pressure": 1013.83,
      |        "sea_level": 1019.54,
      |        "grnd_level": 1013.83,
      |        "humidity": 99,
      |        "temp_kf": 0
      |      },
      |      "weather": [
      |        {
      |          "id": 500,
      |          "main": "Rain",
      |          "description": "light rain",
      |          "icon": "10d"
      |        }
      |      ],
      |      "clouds": {
      |        "all": 100
      |      },
      |      "wind": {
      |        "speed": 4.91,
      |        "deg": 218.501
      |      },
      |      "rain": {
      |        "3h": 1.62
      |      },
      |      "sys": {
      |        "pod": "d"
      |      },
      |      "dt_txt": "2017-07-12 15:00:00"
      |    },
      |    {
      |      "dt": 1499882400,
      |      "main": {
      |        "temp": 16.66,
      |        "temp_min": 16.66,
      |        "temp_max": 16.66,
      |        "pressure": 1012.87,
      |        "sea_level": 1018.63,
      |        "grnd_level": 1012.87,
      |        "humidity": 99,
      |        "temp_kf": 0
      |      },
      |      "weather": [
      |        {
      |          "id": 500,
      |          "main": "Rain",
      |          "description": "light rain",
      |          "icon": "10d"
      |        }
      |      ],
      |      "clouds": {
      |        "all": 100
      |      },
      |      "wind": {
      |        "speed": 3.45,
      |        "deg": 233.006
      |      },
      |      "rain": {
      |        "3h": 1.58
      |      },
      |      "sys": {
      |        "pod": "d"
      |      },
      |      "dt_txt": "2017-07-12 18:00:00"
      |    },
      |    {
      |      "dt": 1499893200,
      |      "main": {
      |        "temp": 15.83,
      |        "temp_min": 15.83,
      |        "temp_max": 15.83,
      |        "pressure": 1013.57,
      |        "sea_level": 1019.23,
      |        "grnd_level": 1013.57,
      |        "humidity": 99,
      |        "temp_kf": 0
      |      },
      |      "weather": [
      |        {
      |          "id": 501,
      |          "main": "Rain",
      |          "description": "moderate rain",
      |          "icon": "10n"
      |        }
      |      ],
      |      "clouds": {
      |        "all": 92
      |      },
      |      "wind": {
      |        "speed": 4.47,
      |        "deg": 303.501
      |      },
      |      "rain": {
      |        "3h": 3.25
      |      },
      |      "sys": {
      |        "pod": "n"
      |      },
      |      "dt_txt": "2017-07-12 21:00:00"
      |    },
      |    {
      |      "dt": 1499904000,
      |      "main": {
      |        "temp": 13.85,
      |        "temp_min": 13.85,
      |        "temp_max": 13.85,
      |        "pressure": 1016.28,
      |        "sea_level": 1022.11,
      |        "grnd_level": 1016.28,
      |        "humidity": 97,
      |        "temp_kf": 0
      |      },
      |      "weather": [
      |        {
      |          "id": 500,
      |          "main": "Rain",
      |          "description": "light rain",
      |          "icon": "10n"
      |        }
      |      ],
      |      "clouds": {
      |        "all": 92
      |      },
      |      "wind": {
      |        "speed": 10.06,
      |        "deg": 309.007
      |      },
      |      "rain": {
      |        "3h": 1.83
      |      },
      |      "sys": {
      |        "pod": "n"
      |      },
      |      "dt_txt": "2017-07-13 00:00:00"
      |    },
      |    {
      |      "dt": 1499914800,
      |      "main": {
      |        "temp": 11.97,
      |        "temp_min": 11.97,
      |        "temp_max": 11.97,
      |        "pressure": 1019.88,
      |        "sea_level": 1025.68,
      |        "grnd_level": 1019.88,
      |        "humidity": 91,
      |        "temp_kf": 0
      |      },
      |      "weather": [
      |        {
      |          "id": 500,
      |          "main": "Rain",
      |          "description": "light rain",
      |          "icon": "10n"
      |        }
      |      ],
      |      "clouds": {
      |        "all": 76
      |      },
      |      "wind": {
      |        "speed": 8.52,
      |        "deg": 298.001
      |      },
      |      "rain": {
      |        "3h": 0.29
      |      },
      |      "sys": {
      |        "pod": "n"
      |      },
      |      "dt_txt": "2017-07-13 03:00:00"
      |    },
      |    {
      |      "dt": 1499925600,
      |      "main": {
      |        "temp": 13.11,
      |        "temp_min": 13.11,
      |        "temp_max": 13.11,
      |        "pressure": 1022.08,
      |        "sea_level": 1027.91,
      |        "grnd_level": 1022.08,
      |        "humidity": 93,
      |        "temp_kf": 0
      |      },
      |      "weather": [
      |        {
      |          "id": 500,
      |          "main": "Rain",
      |          "description": "light rain",
      |          "icon": "10d"
      |        }
      |      ],
      |      "clouds": {
      |        "all": 12
      |      },
      |      "wind": {
      |        "speed": 7.67,
      |        "deg": 298
      |      },
      |      "rain": {
      |        "3h": 0.3
      |      },
      |      "sys": {
      |        "pod": "d"
      |      },
      |      "dt_txt": "2017-07-13 06:00:00"
      |    },
      |    {
      |      "dt": 1499936400,
      |      "main": {
      |        "temp": 16.57,
      |        "temp_min": 16.57,
      |        "temp_max": 16.57,
      |        "pressure": 1023.66,
      |        "sea_level": 1029.38,
      |        "grnd_level": 1023.66,
      |        "humidity": 91,
      |        "temp_kf": 0
      |      },
      |      "weather": [
      |        {
      |          "id": 801,
      |          "main": "Clouds",
      |          "description": "few clouds",
      |          "icon": "02d"
      |        }
      |      ],
      |      "clouds": {
      |        "all": 20
      |      },
      |      "wind": {
      |        "speed": 7.52,
      |        "deg": 300
      |      },
      |      "rain": {
      |
      |      },
      |      "sys": {
      |        "pod": "d"
      |      },
      |      "dt_txt": "2017-07-13 09:00:00"
      |    },
      |    {
      |      "dt": 1499947200,
      |      "main": {
      |        "temp": 19.3,
      |        "temp_min": 19.3,
      |        "temp_max": 19.3,
      |        "pressure": 1024.57,
      |        "sea_level": 1030.29,
      |        "grnd_level": 1024.57,
      |        "humidity": 87,
      |        "temp_kf": 0
      |      },
      |      "weather": [
      |        {
      |          "id": 801,
      |          "main": "Clouds",
      |          "description": "few clouds",
      |          "icon": "02d"
      |        }
      |      ],
      |      "clouds": {
      |        "all": 12
      |      },
      |      "wind": {
      |        "speed": 7.01,
      |        "deg": 309.003
      |      },
      |      "rain": {
      |
      |      },
      |      "sys": {
      |        "pod": "d"
      |      },
      |      "dt_txt": "2017-07-13 12:00:00"
      |    },
      |    {
      |      "dt": 1499958000,
      |      "main": {
      |        "temp": 20.01,
      |        "temp_min": 20.01,
      |        "temp_max": 20.01,
      |        "pressure": 1024.92,
      |        "sea_level": 1030.61,
      |        "grnd_level": 1024.92,
      |        "humidity": 81,
      |        "temp_kf": 0
      |      },
      |      "weather": [
      |        {
      |          "id": 801,
      |          "main": "Clouds",
      |          "description": "few clouds",
      |          "icon": "02d"
      |        }
      |      ],
      |      "clouds": {
      |        "all": 12
      |      },
      |      "wind": {
      |        "speed": 5.82,
      |        "deg": 319.001
      |      },
      |      "rain": {
      |
      |      },
      |      "sys": {
      |        "pod": "d"
      |      },
      |      "dt_txt": "2017-07-13 15:00:00"
      |    },
      |    {
      |      "dt": 1499968800,
      |      "main": {
      |        "temp": 18.47,
      |        "temp_min": 18.47,
      |        "temp_max": 18.47,
      |        "pressure": 1025.2,
      |        "sea_level": 1030.94,
      |        "grnd_level": 1025.2,
      |        "humidity": 80,
      |        "temp_kf": 0
      |      },
      |      "weather": [
      |        {
      |          "id": 801,
      |          "main": "Clouds",
      |          "description": "few clouds",
      |          "icon": "02d"
      |        }
      |      ],
      |      "clouds": {
      |        "all": 20
      |      },
      |      "wind": {
      |        "speed": 3.87,
      |        "deg": 326.001
      |      },
      |      "rain": {
      |
      |      },
      |      "sys": {
      |        "pod": "d"
      |      },
      |      "dt_txt": "2017-07-13 18:00:00"
      |    },
      |    {
      |      "dt": 1499979600,
      |      "main": {
      |        "temp": 14.27,
      |        "temp_min": 14.27,
      |        "temp_max": 14.27,
      |        "pressure": 1025.49,
      |        "sea_level": 1031.29,
      |        "grnd_level": 1025.49,
      |        "humidity": 88,
      |        "temp_kf": 0
      |      },
      |      "weather": [
      |        {
      |          "id": 801,
      |          "main": "Clouds",
      |          "description": "few clouds",
      |          "icon": "02n"
      |        }
      |      ],
      |      "clouds": {
      |        "all": 20
      |      },
      |      "wind": {
      |        "speed": 2.57,
      |        "deg": 317.502
      |      },
      |      "rain": {
      |
      |      },
      |      "sys": {
      |        "pod": "n"
      |      },
      |      "dt_txt": "2017-07-13 21:00:00"
      |    },
      |    {
      |      "dt": 1499990400,
      |      "main": {
      |        "temp": 12.73,
      |        "temp_min": 12.73,
      |        "temp_max": 12.73,
      |        "pressure": 1025.26,
      |        "sea_level": 1031.06,
      |        "grnd_level": 1025.26,
      |        "humidity": 85,
      |        "temp_kf": 0
      |      },
      |      "weather": [
      |        {
      |          "id": 803,
      |          "main": "Clouds",
      |          "description": "broken clouds",
      |          "icon": "04n"
      |        }
      |      ],
      |      "clouds": {
      |        "all": 76
      |      },
      |      "wind": {
      |        "speed": 1.42,
      |        "deg": 295.501
      |      },
      |      "rain": {
      |
      |      },
      |      "sys": {
      |        "pod": "n"
      |      },
      |      "dt_txt": "2017-07-14 00:00:00"
      |    },
      |    {
      |      "dt": 1500001200,
      |      "main": {
      |        "temp": 13.32,
      |        "temp_min": 13.32,
      |        "temp_max": 13.32,
      |        "pressure": 1024.65,
      |        "sea_level": 1030.49,
      |        "grnd_level": 1024.65,
      |        "humidity": 83,
      |        "temp_kf": 0
      |      },
      |      "weather": [
      |        {
      |          "id": 804,
      |          "main": "Clouds",
      |          "description": "overcast clouds",
      |          "icon": "04n"
      |        }
      |      ],
      |      "clouds": {
      |        "all": 88
      |      },
      |      "wind": {
      |        "speed": 1.56,
      |        "deg": 276.003
      |      },
      |      "rain": {
      |
      |      },
      |      "sys": {
      |        "pod": "n"
      |      },
      |      "dt_txt": "2017-07-14 03:00:00"
      |    },
      |    {
      |      "dt": 1500012000,
      |      "main": {
      |        "temp": 15.4,
      |        "temp_min": 15.4,
      |        "temp_max": 15.4,
      |        "pressure": 1024.36,
      |        "sea_level": 1030.1,
      |        "grnd_level": 1024.36,
      |        "humidity": 93,
      |        "temp_kf": 0
      |      },
      |      "weather": [
      |        {
      |          "id": 500,
      |          "main": "Rain",
      |          "description": "light rain",
      |          "icon": "10d"
      |        }
      |      ],
      |      "clouds": {
      |        "all": 64
      |      },
      |      "wind": {
      |        "speed": 2.01,
      |        "deg": 317.003
      |      },
      |      "rain": {
      |        "3h": 0.010000000000002
      |      },
      |      "sys": {
      |        "pod": "d"
      |      },
      |      "dt_txt": "2017-07-14 06:00:00"
      |    },
      |    {
      |      "dt": 1500022800,
      |      "main": {
      |        "temp": 17.29,
      |        "temp_min": 17.29,
      |        "temp_max": 17.29,
      |        "pressure": 1024.05,
      |        "sea_level": 1029.68,
      |        "grnd_level": 1024.05,
      |        "humidity": 93,
      |        "temp_kf": 0
      |      },
      |      "weather": [
      |        {
      |          "id": 500,
      |          "main": "Rain",
      |          "description": "light rain",
      |          "icon": "10d"
      |        }
      |      ],
      |      "clouds": {
      |        "all": 56
      |      },
      |      "wind": {
      |        "speed": 2.07,
      |        "deg": 304.001
      |      },
      |      "rain": {
      |        "3h": 0.009999999999998
      |      },
      |      "sys": {
      |        "pod": "d"
      |      },
      |      "dt_txt": "2017-07-14 09:00:00"
      |    },
      |    {
      |      "dt": 1500033600,
      |      "main": {
      |        "temp": 18.93,
      |        "temp_min": 18.93,
      |        "temp_max": 18.93,
      |        "pressure": 1023.06,
      |        "sea_level": 1028.69,
      |        "grnd_level": 1023.06,
      |        "humidity": 92,
      |        "temp_kf": 0
      |      },
      |      "weather": [
      |        {
      |          "id": 802,
      |          "main": "Clouds",
      |          "description": "scattered clouds",
      |          "icon": "03d"
      |        }
      |      ],
      |      "clouds": {
      |        "all": 36
      |      },
      |      "wind": {
      |        "speed": 2.11,
      |        "deg": 303
      |      },
      |      "rain": {
      |
      |      },
      |      "sys": {
      |        "pod": "d"
      |      },
      |      "dt_txt": "2017-07-14 12:00:00"
      |    },
      |    {
      |      "dt": 1500044400,
      |      "main": {
      |        "temp": 19.77,
      |        "temp_min": 19.77,
      |        "temp_max": 19.77,
      |        "pressure": 1022.34,
      |        "sea_level": 1027.98,
      |        "grnd_level": 1022.34,
      |        "humidity": 77,
      |        "temp_kf": 0
      |      },
      |      "weather": [
      |        {
      |          "id": 803,
      |          "main": "Clouds",
      |          "description": "broken clouds",
      |          "icon": "04d"
      |        }
      |      ],
      |      "clouds": {
      |        "all": 64
      |      },
      |      "wind": {
      |        "speed": 2.32,
      |        "deg": 329.001
      |      },
      |      "rain": {
      |
      |      },
      |      "sys": {
      |        "pod": "d"
      |      },
      |      "dt_txt": "2017-07-14 15:00:00"
      |    },
      |    {
      |      "dt": 1500055200,
      |      "main": {
      |        "temp": 18.59,
      |        "temp_min": 18.59,
      |        "temp_max": 18.59,
      |        "pressure": 1022.73,
      |        "sea_level": 1028.47,
      |        "grnd_level": 1022.73,
      |        "humidity": 71,
      |        "temp_kf": 0
      |      },
      |      "weather": [
      |        {
      |          "id": 804,
      |          "main": "Clouds",
      |          "description": "overcast clouds",
      |          "icon": "04d"
      |        }
      |      ],
      |      "clouds": {
      |        "all": 88
      |      },
      |      "wind": {
      |        "speed": 2.42,
      |        "deg": 346.504
      |      },
      |      "rain": {
      |
      |      },
      |      "sys": {
      |        "pod": "d"
      |      },
      |      "dt_txt": "2017-07-14 18:00:00"
      |    },
      |    {
      |      "dt": 1500066000,
      |      "main": {
      |        "temp": 16.11,
      |        "temp_min": 16.11,
      |        "temp_max": 16.11,
      |        "pressure": 1024,
      |        "sea_level": 1029.73,
      |        "grnd_level": 1024,
      |        "humidity": 72,
      |        "temp_kf": 0
      |      },
      |      "weather": [
      |        {
      |          "id": 500,
      |          "main": "Rain",
      |          "description": "light rain",
      |          "icon": "10n"
      |        }
      |      ],
      |      "clouds": {
      |        "all": 64
      |      },
      |      "wind": {
      |        "speed": 3.43,
      |        "deg": 11.0028
      |      },
      |      "rain": {
      |        "3h": 0.09
      |      },
      |      "sys": {
      |        "pod": "n"
      |      },
      |      "dt_txt": "2017-07-14 21:00:00"
      |    },
      |    {
      |      "dt": 1500076800,
      |      "main": {
      |        "temp": 12.7,
      |        "temp_min": 12.7,
      |        "temp_max": 12.7,
      |        "pressure": 1024.62,
      |        "sea_level": 1030.39,
      |        "grnd_level": 1024.62,
      |        "humidity": 91,
      |        "temp_kf": 0
      |      },
      |      "weather": [
      |        {
      |          "id": 500,
      |          "main": "Rain",
      |          "description": "light rain",
      |          "icon": "10n"
      |        }
      |      ],
      |      "clouds": {
      |        "all": 20
      |      },
      |      "wind": {
      |        "speed": 2.51,
      |        "deg": 334.502
      |      },
      |      "rain": {
      |        "3h": 0.07
      |      },
      |      "sys": {
      |        "pod": "n"
      |      },
      |      "dt_txt": "2017-07-15 00:00:00"
      |    },
      |    {
      |      "dt": 1500087600,
      |      "main": {
      |        "temp": 11.4,
      |        "temp_min": 11.4,
      |        "temp_max": 11.4,
      |        "pressure": 1025.13,
      |        "sea_level": 1030.97,
      |        "grnd_level": 1025.13,
      |        "humidity": 88,
      |        "temp_kf": 0
      |      },
      |      "weather": [
      |        {
      |          "id": 803,
      |          "main": "Clouds",
      |          "description": "broken clouds",
      |          "icon": "04n"
      |        }
      |      ],
      |      "clouds": {
      |        "all": 56
      |      },
      |      "wind": {
      |        "speed": 1.75,
      |        "deg": 303.506
      |      },
      |      "rain": {
      |
      |      },
      |      "sys": {
      |        "pod": "n"
      |      },
      |      "dt_txt": "2017-07-15 03:00:00"
      |    },
      |    {
      |      "dt": 1500098400,
      |      "main": {
      |        "temp": 14.55,
      |        "temp_min": 14.55,
      |        "temp_max": 14.55,
      |        "pressure": 1025.81,
      |        "sea_level": 1031.63,
      |        "grnd_level": 1025.81,
      |        "humidity": 85,
      |        "temp_kf": 0
      |      },
      |      "weather": [
      |        {
      |          "id": 801,
      |          "main": "Clouds",
      |          "description": "few clouds",
      |          "icon": "02d"
      |        }
      |      ],
      |      "clouds": {
      |        "all": 12
      |      },
      |      "wind": {
      |        "speed": 3.32,
      |        "deg": 299.503
      |      },
      |      "rain": {
      |
      |      },
      |      "sys": {
      |        "pod": "d"
      |      },
      |      "dt_txt": "2017-07-15 06:00:00"
      |    },
      |    {
      |      "dt": 1500109200,
      |      "main": {
      |        "temp": 18.45,
      |        "temp_min": 18.45,
      |        "temp_max": 18.45,
      |        "pressure": 1026.3,
      |        "sea_level": 1031.98,
      |        "grnd_level": 1026.3,
      |        "humidity": 81,
      |        "temp_kf": 0
      |      },
      |      "weather": [
      |        {
      |          "id": 800,
      |          "main": "Clear",
      |          "description": "clear sky",
      |          "icon": "01d"
      |        }
      |      ],
      |      "clouds": {
      |        "all": 0
      |      },
      |      "wind": {
      |        "speed": 4.16,
      |        "deg": 297.502
      |      },
      |      "rain": {
      |
      |      },
      |      "sys": {
      |        "pod": "d"
      |      },
      |      "dt_txt": "2017-07-15 09:00:00"
      |    },
      |    {
      |      "dt": 1500120000,
      |      "main": {
      |        "temp": 20.25,
      |        "temp_min": 20.25,
      |        "temp_max": 20.25,
      |        "pressure": 1026.49,
      |        "sea_level": 1032.22,
      |        "grnd_level": 1026.49,
      |        "humidity": 76,
      |        "temp_kf": 0
      |      },
      |      "weather": [
      |        {
      |          "id": 800,
      |          "main": "Clear",
      |          "description": "clear sky",
      |          "icon": "02d"
      |        }
      |      ],
      |      "clouds": {
      |        "all": 8
      |      },
      |      "wind": {
      |        "speed": 4.16,
      |        "deg": 301.003
      |      },
      |      "rain": {
      |
      |      },
      |      "sys": {
      |        "pod": "d"
      |      },
      |      "dt_txt": "2017-07-15 12:00:00"
      |    },
      |    {
      |      "dt": 1500130800,
      |      "main": {
      |        "temp": 20.61,
      |        "temp_min": 20.61,
      |        "temp_max": 20.61,
      |        "pressure": 1026.18,
      |        "sea_level": 1031.92,
      |        "grnd_level": 1026.18,
      |        "humidity": 69,
      |        "temp_kf": 0
      |      },
      |      "weather": [
      |        {
      |          "id": 800,
      |          "main": "Clear",
      |          "description": "clear sky",
      |          "icon": "02d"
      |        }
      |      ],
      |      "clouds": {
      |        "all": 8
      |      },
      |      "wind": {
      |        "speed": 4.21,
      |        "deg": 293.002
      |      },
      |      "rain": {
      |
      |      },
      |      "sys": {
      |        "pod": "d"
      |      },
      |      "dt_txt": "2017-07-15 15:00:00"
      |    },
      |    {
      |      "dt": 1500141600,
      |      "main": {
      |        "temp": 19.07,
      |        "temp_min": 19.07,
      |        "temp_max": 19.07,
      |        "pressure": 1026.17,
      |        "sea_level": 1031.88,
      |        "grnd_level": 1026.17,
      |        "humidity": 68,
      |        "temp_kf": 0
      |      },
      |      "weather": [
      |        {
      |          "id": 800,
      |          "main": "Clear",
      |          "description": "clear sky",
      |          "icon": "01d"
      |        }
      |      ],
      |      "clouds": {
      |        "all": 0
      |      },
      |      "wind": {
      |        "speed": 3.62,
      |        "deg": 273.506
      |      },
      |      "rain": {
      |
      |      },
      |      "sys": {
      |        "pod": "d"
      |      },
      |      "dt_txt": "2017-07-15 18:00:00"
      |    },
      |    {
      |      "dt": 1500152400,
      |      "main": {
      |        "temp": 15.7,
      |        "temp_min": 15.7,
      |        "temp_max": 15.7,
      |        "pressure": 1026.64,
      |        "sea_level": 1032.3,
      |        "grnd_level": 1026.64,
      |        "humidity": 70,
      |        "temp_kf": 0
      |      },
      |      "weather": [
      |        {
      |          "id": 804,
      |          "main": "Clouds",
      |          "description": "overcast clouds",
      |          "icon": "04n"
      |        }
      |      ],
      |      "clouds": {
      |        "all": 88
      |      },
      |      "wind": {
      |        "speed": 3.56,
      |        "deg": 260.501
      |      },
      |      "rain": {
      |
      |      },
      |      "sys": {
      |        "pod": "n"
      |      },
      |      "dt_txt": "2017-07-15 21:00:00"
      |    }
      |  ],
      |  "city": {
      |    "id": 2950159,
      |    "name": "Berlin",
      |    "coord": {
      |      "lat": 52.5244,
      |      "lon": 13.4105
      |    },
      |    "country": "DE"
      |  }
      |}
    """.stripMargin
  val githubTokenResponse: String =
    """
      |{
      | "access_token": "12345551233123a",
      | "scope": "user%3Aemail",
      | "token_type": "bearer"
      |}
    """.stripMargin
  val githubUserResponse: String =
    """
      |{
      |  "login": "poweruser",
      |  "id": 12313,
      |  "avatar_url": "https://avatars0.githubusercontent.com/u/116554653",
      |  "gravatar_id": "",
      |  "url": "https://api.github.com/users/poweruser",
      |  "html_url": "https://github.com/poweruser",
      |  "followers_url": "https://api.github.com/users/poseruser/followers",
      |  "following_url": "https://api.github.com/users/poseruser/following{/other_user}",
      |  "gists_url": "https://api.github.com/users/poseruser/gists{/gist_id}",
      |  "starred_url": "https://api.github.com/users/poseruser/starred{/owner}{/repo}",
      |  "subscriptions_url": "https://api.github.com/users/poseruser/subscriptions",
      |  "organizations_url": "https://api.github.com/users/poseruser/orgs",
      |  "repos_url": "https://api.github.com/users/poweruser/repos",
      |  "events_url": "https://api.github.com/users/poweruser/events{/privacy}",
      |  "received_events_url": "https://api.github.com/users/poseruser/received_events",
      |  "type": "User",
      |  "site_admin": false,
      |  "name": "Power User",
      |  "company": null,
      |  "blog": "",
      |  "location": "Berlin, Germany",
      |  "email": null,
      |  "hireable": true,
      |  "bio": null,
      |  "public_repos": 36,
      |  "public_gists": 0,
      |  "followers": 11,
      |  "following": 29,
      |  "created_at": "2014-01-31T15:05:01Z",
      |  "updated_at": "2017-07-21T19:17:41Z"
      |}
    """.stripMargin
}
