import play.api.mvc.Results
import play.api.test.Helpers.stubControllerComponents

object ActionMocks {
  def getCities() = stubControllerComponents().actionBuilder {
    Results.Ok(Fixtures.cities)
  }
  def getWeather() = stubControllerComponents().actionBuilder {
    Results.Ok(Fixtures.weatherResponse)
  }
}

object Fixtures {
  val cities = """
  {
  "predictions": [
    {
      "description": "Berlin, Germany",
      "id": "6b1afbd7fcf2ec16ff8e2f95514e2badb8c2451d",
      "matched_substrings": [
        {
          "length": 6,
          "offset": 0
        }
      ],
      "place_id": "ChIJAVkDPzdOqEcRcDteW0YgIQQ",
      "reference": "CjQnAAAAaThJzP1zUd4lgdJhAoK2-XHIcRZIR80lF7NUxiIEiP5z8YYp6mC_Vmz3eQ-IqP-LEhAXLP0gevUuBhev1nvQSbhRGhTi6mL7K1ilaRTV7NFjbyVqq6oM6Q",
      "structured_formatting": {
        "main_text": "Berlin",
        "main_text_matched_substrings": [
          {
            "length": 6,
            "offset": 0
          }
        ],
        "secondary_text": "Germany"
      },
      "terms": [
        {
          "offset": 0,
          "value": "Berlin"
        },
        {
          "offset": 8,
          "value": "Germany"
        }
      ],
      "types": [
        "locality",
        "political",
        "geocode"
      ]
    },
    {
      "description": "Berlin Central Station, Europaplatz, Berlin, Germany",
      "id": "3f3350651fb1e3cc1a50e6cd5ed15adb106feb96",
      "matched_substrings": [
        {
          "length": 6,
          "offset": 0
        }
      ],
      "place_id": "ChIJe-ff-71RqEcRqvy8lRR4PHo",
      "reference": "ClRCAAAA3ybC5N-OxIn-DO-HqC2EhwfbLrAotMmnrn8OPF6gIGff_WdvD_jLgJxz-6iFwAQM0YwBaExEdGXvmFhMZkcWmqb2zrAHv18XAG-hUapMnNwSELNZ6XfnPB37SZA-ChoHCAgaFJE9iXzd1CBPnXBJARm7qigqaFEB",
      "structured_formatting": {
        "main_text": "Berlin Central Station",
        "main_text_matched_substrings": [
          {
            "length": 6,
            "offset": 0
          }
        ],
        "secondary_text": "Europaplatz, Berlin, Germany"
      },
      "terms": [
        {
          "offset": 0,
          "value": "Berlin Central Station"
        },
        {
          "offset": 24,
          "value": "Europaplatz"
        },
        {
          "offset": 37,
          "value": "Berlin"
        },
        {
          "offset": 45,
          "value": "Germany"
        }
      ],
      "types": [
        "transit_station",
        "point_of_interest",
        "establishment",
        "geocode"
      ]
    },
    {
      "description": "Berlin, CT, United States",
      "id": "d0d699323eb7794c8ffcac9564c22df4dd863c11",
      "matched_substrings": [
        {
          "length": 6,
          "offset": 0
        }
      ],
      "place_id": "ChIJTzAUu0i054kRAznGlquL7Zw",
      "reference": "CkQxAAAAp_dYeYcqItrZI-2AK_S5MGT7uAE2c3eOldkJ-EQDkPMECUXhV7eLsMKW7eYvfVIiiWK57PkzP2EsG0IxSKLPdhIQCOau83ngD3yToKfylt4i7xoUb6Io_Op6zFKuKyDcC3Ari1F3BNw",
      "structured_formatting": {
        "main_text": "Berlin",
        "main_text_matched_substrings": [
          {
            "length": 6,
            "offset": 0
          }
        ],
        "secondary_text": "CT, United States"
      },
      "terms": [
        {
          "offset": 0,
          "value": "Berlin"
        },
        {
          "offset": 8,
          "value": "CT"
        },
        {
          "offset": 12,
          "value": "United States"
        }
      ],
      "types": [
        "locality",
        "political",
        "geocode"
      ]
    },
    {
      "description": "Berlin Turnpike, Berlin, CT, United States",
      "id": "2747a9d2af2ed5af3341ee13719e35425917f278",
      "matched_substrings": [
        {
          "length": 6,
          "offset": 0
        }
      ],
      "place_id": "EipCZXJsaW4gVHVybnBpa2UsIEJlcmxpbiwgQ1QsIFVuaXRlZCBTdGF0ZXM",
      "reference": "CjQuAAAAvxUvktR4IjWq_KAgoXudTyHkn_K7B1YRLdRI4FrcdsL9_v_8TLrSybCMq_VITtm6EhD6gV-9CHgeyhUlMxvcAJ-lGhTZKp8TfGHaX5AvZ4tGQsoFTZzzSQ",
      "structured_formatting": {
        "main_text": "Berlin Turnpike",
        "main_text_matched_substrings": [
          {
            "length": 6,
            "offset": 0
          }
        ],
        "secondary_text": "Berlin, CT, United States"
      },
      "terms": [
        {
          "offset": 0,
          "value": "Berlin Turnpike"
        },
        {
          "offset": 17,
          "value": "Berlin"
        },
        {
          "offset": 25,
          "value": "CT"
        },
        {
          "offset": 29,
          "value": "United States"
        }
      ],
      "types": [
        "route",
        "geocode"
      ]
    },
    {
      "description": "Berlin, MD, United States",
      "id": "fd76e128442b7f1e64025e1c8a423cc18e244ccb",
      "matched_substrings": [
        {
          "length": 6,
          "offset": 0
        }
      ],
      "place_id": "ChIJlaa_MaUmuYkRrBvqEfEZjp4",
      "reference": "CkQxAAAAEY0ye6MbP9LqgcP8FF2espoRUZ2zbdz5aS4qFwQFpIv3S2JB-vcQc9pW0SiJxVnTRPWDjIHSW9K1cablw4HJ0RIQBd_f4dXpklz4pcIKQw9NphoU7AUTTpJEaVArCbbnVKpz2nUoh1s",
      "structured_formatting": {
        "main_text": "Berlin",
        "main_text_matched_substrings": [
          {
            "length": 6,
            "offset": 0
          }
        ],
        "secondary_text": "MD, United States"
      },
      "terms": [
        {
          "offset": 0,
          "value": "Berlin"
        },
        {
          "offset": 8,
          "value": "MD"
        },
        {
          "offset": 12,
          "value": "United States"
        }
      ],
      "types": [
        "locality",
        "political",
        "geocode"
      ]
    }
  ],
  "status": "OK"
  }          
  """
  val weatherResponse = """
  {
  "coord": {
    "lon": 13.41,
    "lat": 52.52
  },
  "weather": [
    {
      "id": 803,
      "main": "Clouds",
      "description": "broken clouds",
      "icon": "04d"
    }
  ],
  "base": "stations",
  "main": {
    "temp": 17.49,
    "pressure": 1020,
    "humidity": 68,
    "temp_min": 17,
    "temp_max": 18
  },
  "visibility": 10000,
  "wind": {
    "speed": 0.5
  },
  "clouds": {
    "all": 75
  },
  "dt": 1499322000,
  "sys": {
    "type": 1,
    "id": 4892,
    "message": 0.0053,
    "country": "DE",
    "sunrise": 1499309530,
    "sunset": 1499369382
  },
  "id": 2950159,
  "name": "Berlin",
  "cod": 200
  }  
  """            
  val forecastResponse = """
  {
    "cod": "200",
    "message": 0.0172,
    "cnt": 37,
    "list": [
      {
        "dt": 1499331600,
        "main": {
          "temp": 292.95,
          "temp_min": 290.938,
          "temp_max": 292.95,
          "pressure": 1029.95,
          "sea_level": 1034.42,
          "grnd_level": 1029.95,
          "humidity": 89,
          "temp_kf": 2.02
        },
        "weather": [
          {
            "id": 801,
            "main": "Clouds",
            "description": "few clouds",
            "icon": "02d"
          }
        ],
        "clouds": {
          "all": 20
        },
        "wind": {
          "speed": 2.04,
          "deg": 78.5031
        },
        "rain": {
          
        },
        "sys": {
          "pod": "d"
        },
        "dt_txt": "2017-07-06 09:00:00"
      },
      {
        "dt": 1499342400,
        "main": {
          "temp": 295.11,
          "temp_min": 293.601,
          "temp_max": 295.11,
          "pressure": 1029.18,
          "sea_level": 1033.64,
          "grnd_level": 1029.18,
          "humidity": 91,
          "temp_kf": 1.51
        },
        "weather": [
          {
            "id": 801,
            "main": "Clouds",
            "description": "few clouds",
            "icon": "02d"
          }
        ],
        "clouds": {
          "all": 12
        },
        "wind": {
          "speed": 2.03,
          "deg": 148.502
        },
        "rain": {
          
        },
        "sys": {
          "pod": "d"
        },
        "dt_txt": "2017-07-06 12:00:00"
      },
      {
        "dt": 1499353200,
        "main": {
          "temp": 295.96,
          "temp_min": 294.951,
          "temp_max": 295.96,
          "pressure": 1027.87,
          "sea_level": 1032.2,
          "grnd_level": 1027.87,
          "humidity": 84,
          "temp_kf": 1.01
        },
        "weather": [
          {
            "id": 800,
            "main": "Clear",
            "description": "clear sky",
            "icon": "01d"
          }
        ],
        "clouds": {
          "all": 0
        },
        "wind": {
          "speed": 2.03,
          "deg": 173.501
        },
        "rain": {
          
        },
        "sys": {
          "pod": "d"
        },
        "dt_txt": "2017-07-06 15:00:00"
      },
      {
        "dt": 1499364000,
        "main": {
          "temp": 295.11,
          "temp_min": 294.603,
          "temp_max": 295.11,
          "pressure": 1026.22,
          "sea_level": 1030.62,
          "grnd_level": 1026.22,
          "humidity": 76,
          "temp_kf": 0.5
        },
        "weather": [
          {
            "id": 800,
            "main": "Clear",
            "description": "clear sky",
            "icon": "01d"
          }
        ],
        "clouds": {
          "all": 0
        },
        "wind": {
          "speed": 1.66,
          "deg": 135.501
        },
        "rain": {
          
        },
        "sys": {
          "pod": "d"
        },
        "dt_txt": "2017-07-06 18:00:00"
      },
      {
        "dt": 1499374800,
        "main": {
          "temp": 290.254,
          "temp_min": 290.254,
          "temp_max": 290.254,
          "pressure": 1025.9,
          "sea_level": 1030.37,
          "grnd_level": 1025.9,
          "humidity": 80,
          "temp_kf": 0
        },
        "weather": [
          {
            "id": 803,
            "main": "Clouds",
            "description": "broken clouds",
            "icon": "04n"
          }
        ],
        "clouds": {
          "all": 68
        },
        "wind": {
          "speed": 1.76,
          "deg": 58.5057
        },
        "rain": {
          
        },
        "sys": {
          "pod": "n"
        },
        "dt_txt": "2017-07-06 21:00:00"
      },
      {
        "dt": 1499385600,
        "main": {
          "temp": 288.755,
          "temp_min": 288.755,
          "temp_max": 288.755,
          "pressure": 1024.73,
          "sea_level": 1029.08,
          "grnd_level": 1024.73,
          "humidity": 82,
          "temp_kf": 0
        },
        "weather": [
          {
            "id": 801,
            "main": "Clouds",
            "description": "few clouds",
            "icon": "02n"
          }
        ],
        "clouds": {
          "all": 20
        },
        "wind": {
          "speed": 3.26,
          "deg": 95.5024
        },
        "rain": {
          
        },
        "sys": {
          "pod": "n"
        },
        "dt_txt": "2017-07-07 00:00:00"
      },
      {
        "dt": 1499396400,
        "main": {
          "temp": 287.72,
          "temp_min": 287.72,
          "temp_max": 287.72,
          "pressure": 1023.02,
          "sea_level": 1027.57,
          "grnd_level": 1023.02,
          "humidity": 86,
          "temp_kf": 0
        },
        "weather": [
          {
            "id": 804,
            "main": "Clouds",
            "description": "overcast clouds",
            "icon": "04n"
          }
        ],
        "clouds": {
          "all": 88
        },
        "wind": {
          "speed": 1.46,
          "deg": 88.0015
        },
        "rain": {
          
        },
        "sys": {
          "pod": "n"
        },
        "dt_txt": "2017-07-07 03:00:00"
      },
      {
        "dt": 1499407200,
        "main": {
          "temp": 289.246,
          "temp_min": 289.246,
          "temp_max": 289.246,
          "pressure": 1022.02,
          "sea_level": 1026.48,
          "grnd_level": 1022.02,
          "humidity": 99,
          "temp_kf": 0
        },
        "weather": [
          {
            "id": 501,
            "main": "Rain",
            "description": "moderate rain",
            "icon": "10d"
          }
        ],
        "clouds": {
          "all": 92
        },
        "wind": {
          "speed": 1.77,
          "deg": 128.005
        },
        "rain": {
          "3h": 4.405
        },
        "sys": {
          "pod": "d"
        },
        "dt_txt": "2017-07-07 06:00:00"
      },
      {
        "dt": 1499418000,
        "main": {
          "temp": 290.5,
          "temp_min": 290.5,
          "temp_max": 290.5,
          "pressure": 1021.87,
          "sea_level": 1026.26,
          "grnd_level": 1021.87,
          "humidity": 98,
          "temp_kf": 0
        },
        "weather": [
          {
            "id": 502,
            "main": "Rain",
            "description": "heavy intensity rain",
            "icon": "10d"
          }
        ],
        "clouds": {
          "all": 24
        },
        "wind": {
          "speed": 1.97,
          "deg": 124.5
        },
        "rain": {
          "3h": 16.875
        },
        "sys": {
          "pod": "d"
        },
        "dt_txt": "2017-07-07 09:00:00"
      },
      {
        "dt": 1499428800,
        "main": {
          "temp": 294.236,
          "temp_min": 294.236,
          "temp_max": 294.236,
          "pressure": 1021.77,
          "sea_level": 1026.13,
          "grnd_level": 1021.77,
          "humidity": 100,
          "temp_kf": 0
        },
        "weather": [
          {
            "id": 801,
            "main": "Clouds",
            "description": "few clouds",
            "icon": "02d"
          }
        ],
        "clouds": {
          "all": 20
        },
        "wind": {
          "speed": 2.05,
          "deg": 25.5046
        },
        "rain": {
          
        },
        "sys": {
          "pod": "d"
        },
        "dt_txt": "2017-07-07 12:00:00"
      },
      {
        "dt": 1499439600,
        "main": {
          "temp": 294.326,
          "temp_min": 294.326,
          "temp_max": 294.326,
          "pressure": 1022.21,
          "sea_level": 1026.68,
          "grnd_level": 1022.21,
          "humidity": 96,
          "temp_kf": 0
        },
        "weather": [
          {
            "id": 500,
            "main": "Rain",
            "description": "light rain",
            "icon": "10d"
          }
        ],
        "clouds": {
          "all": 88
        },
        "wind": {
          "speed": 3.51,
          "deg": 322.506
        },
        "rain": {
          "3h": 0.015000000000001
        },
        "sys": {
          "pod": "d"
        },
        "dt_txt": "2017-07-07 15:00:00"
      },
      {
        "dt": 1499450400,
        "main": {
          "temp": 293.19,
          "temp_min": 293.19,
          "temp_max": 293.19,
          "pressure": 1022.85,
          "sea_level": 1027.23,
          "grnd_level": 1022.85,
          "humidity": 93,
          "temp_kf": 0
        },
        "weather": [
          {
            "id": 500,
            "main": "Rain",
            "description": "light rain",
            "icon": "10d"
          }
        ],
        "clouds": {
          "all": 64
        },
        "wind": {
          "speed": 2.69,
          "deg": 316.502
        },
        "rain": {
          "3h": 0.024999999999999
        },
        "sys": {
          "pod": "d"
        },
        "dt_txt": "2017-07-07 18:00:00"
      },
      {
        "dt": 1499461200,
        "main": {
          "temp": 291.621,
          "temp_min": 291.621,
          "temp_max": 291.621,
          "pressure": 1023.67,
          "sea_level": 1028,
          "grnd_level": 1023.67,
          "humidity": 93,
          "temp_kf": 0
        },
        "weather": [
          {
            "id": 500,
            "main": "Rain",
            "description": "light rain",
            "icon": "10n"
          }
        ],
        "clouds": {
          "all": 92
        },
        "wind": {
          "speed": 2.02,
          "deg": 279.001
        },
        "rain": {
          "3h": 0.010000000000002
        },
        "sys": {
          "pod": "n"
        },
        "dt_txt": "2017-07-07 21:00:00"
      },
      {
        "dt": 1499472000,
        "main": {
          "temp": 290.845,
          "temp_min": 290.845,
          "temp_max": 290.845,
          "pressure": 1023.42,
          "sea_level": 1027.84,
          "grnd_level": 1023.42,
          "humidity": 96,
          "temp_kf": 0
        },
        "weather": [
          {
            "id": 500,
            "main": "Rain",
            "description": "light rain",
            "icon": "10n"
          }
        ],
        "clouds": {
          "all": 76
        },
        "wind": {
          "speed": 1.32,
          "deg": 255.001
        },
        "rain": {
          "3h": 0.009999999999998
        },
        "sys": {
          "pod": "n"
        },
        "dt_txt": "2017-07-08 00:00:00"
      },
      {
        "dt": 1499482800,
        "main": {
          "temp": 289.861,
          "temp_min": 289.861,
          "temp_max": 289.861,
          "pressure": 1022.41,
          "sea_level": 1026.81,
          "grnd_level": 1022.41,
          "humidity": 98,
          "temp_kf": 0
        },
        "weather": [
          {
            "id": 500,
            "main": "Rain",
            "description": "light rain",
            "icon": "10n"
          }
        ],
        "clouds": {
          "all": 80
        },
        "wind": {
          "speed": 1.32,
          "deg": 196.512
        },
        "rain": {
          "3h": 0.010000000000002
        },
        "sys": {
          "pod": "n"
        },
        "dt_txt": "2017-07-08 03:00:00"
      },
      {
        "dt": 1499493600,
        "main": {
          "temp": 292.106,
          "temp_min": 292.106,
          "temp_max": 292.106,
          "pressure": 1021.87,
          "sea_level": 1026.29,
          "grnd_level": 1021.87,
          "humidity": 100,
          "temp_kf": 0
        },
        "weather": [
          {
            "id": 802,
            "main": "Clouds",
            "description": "scattered clouds",
            "icon": "03d"
          }
        ],
        "clouds": {
          "all": 44
        },
        "wind": {
          "speed": 2.17,
          "deg": 237
        },
        "rain": {
          
        },
        "sys": {
          "pod": "d"
        },
        "dt_txt": "2017-07-08 06:00:00"
      },
      {
        "dt": 1499504400,
        "main": {
          "temp": 292.325,
          "temp_min": 292.325,
          "temp_max": 292.325,
          "pressure": 1021.73,
          "sea_level": 1026.15,
          "grnd_level": 1021.73,
          "humidity": 96,
          "temp_kf": 0
        },
        "weather": [
          {
            "id": 500,
            "main": "Rain",
            "description": "light rain",
            "icon": "10d"
          }
        ],
        "clouds": {
          "all": 80
        },
        "wind": {
          "speed": 3.96,
          "deg": 266.501
        },
        "rain": {
          "3h": 0.09
        },
        "sys": {
          "pod": "d"
        },
        "dt_txt": "2017-07-08 09:00:00"
      },
      {
        "dt": 1499515200,
        "main": {
          "temp": 292.548,
          "temp_min": 292.548,
          "temp_max": 292.548,
          "pressure": 1021.74,
          "sea_level": 1026.16,
          "grnd_level": 1021.74,
          "humidity": 96,
          "temp_kf": 0
        },
        "weather": [
          {
            "id": 500,
            "main": "Rain",
            "description": "light rain",
            "icon": "10d"
          }
        ],
        "clouds": {
          "all": 64
        },
        "wind": {
          "speed": 5.76,
          "deg": 274.002
        },
        "rain": {
          "3h": 0.135
        },
        "sys": {
          "pod": "d"
        },
        "dt_txt": "2017-07-08 12:00:00"
      },
      {
        "dt": 1499526000,
        "main": {
          "temp": 292.923,
          "temp_min": 292.923,
          "temp_max": 292.923,
          "pressure": 1022.45,
          "sea_level": 1026.83,
          "grnd_level": 1022.45,
          "humidity": 93,
          "temp_kf": 0
        },
        "weather": [
          {
            "id": 500,
            "main": "Rain",
            "description": "light rain",
            "icon": "10d"
          }
        ],
        "clouds": {
          "all": 8
        },
        "wind": {
          "speed": 5.81,
          "deg": 297.502
        },
        "rain": {
          "3h": 0.0050000000000026
        },
        "sys": {
          "pod": "d"
        },
        "dt_txt": "2017-07-08 15:00:00"
      },
      {
        "dt": 1499536800,
        "main": {
          "temp": 291.621,
          "temp_min": 291.621,
          "temp_max": 291.621,
          "pressure": 1022.85,
          "sea_level": 1027.15,
          "grnd_level": 1022.85,
          "humidity": 85,
          "temp_kf": 0
        },
        "weather": [
          {
            "id": 801,
            "main": "Clouds",
            "description": "few clouds",
            "icon": "02d"
          }
        ],
        "clouds": {
          "all": 12
        },
        "wind": {
          "speed": 5.41,
          "deg": 291.5
        },
        "rain": {
          
        },
        "sys": {
          "pod": "d"
        },
        "dt_txt": "2017-07-08 18:00:00"
      },
      {
        "dt": 1499547600,
        "main": {
          "temp": 289.004,
          "temp_min": 289.004,
          "temp_max": 289.004,
          "pressure": 1023.85,
          "sea_level": 1028.24,
          "grnd_level": 1023.85,
          "humidity": 86,
          "temp_kf": 0
        },
        "weather": [
          {
            "id": 802,
            "main": "Clouds",
            "description": "scattered clouds",
            "icon": "03n"
          }
        ],
        "clouds": {
          "all": 44
        },
        "wind": {
          "speed": 4.1,
          "deg": 289
        },
        "rain": {
          
        },
        "sys": {
          "pod": "n"
        },
        "dt_txt": "2017-07-08 21:00:00"
      },
      {
        "dt": 1499558400,
        "main": {
          "temp": 286.943,
          "temp_min": 286.943,
          "temp_max": 286.943,
          "pressure": 1024.64,
          "sea_level": 1029.01,
          "grnd_level": 1024.64,
          "humidity": 91,
          "temp_kf": 0
        },
        "weather": [
          {
            "id": 500,
            "main": "Rain",
            "description": "light rain",
            "icon": "10n"
          }
        ],
        "clouds": {
          "all": 32
        },
        "wind": {
          "speed": 3.71,
          "deg": 288.002
        },
        "rain": {
          "3h": 0.009999999999998
        },
        "sys": {
          "pod": "n"
        },
        "dt_txt": "2017-07-09 00:00:00"
      },
      {
        "dt": 1499569200,
        "main": {
          "temp": 284.124,
          "temp_min": 284.124,
          "temp_max": 284.124,
          "pressure": 1024.55,
          "sea_level": 1028.97,
          "grnd_level": 1024.55,
          "humidity": 96,
          "temp_kf": 0
        },
        "weather": [
          {
            "id": 800,
            "main": "Clear",
            "description": "clear sky",
            "icon": "01n"
          }
        ],
        "clouds": {
          "all": 0
        },
        "wind": {
          "speed": 2.47,
          "deg": 283.504
        },
        "rain": {
          
        },
        "sys": {
          "pod": "n"
        },
        "dt_txt": "2017-07-09 03:00:00"
      },
      {
        "dt": 1499580000,
        "main": {
          "temp": 286.708,
          "temp_min": 286.708,
          "temp_max": 286.708,
          "pressure": 1024.71,
          "sea_level": 1029.11,
          "grnd_level": 1024.71,
          "humidity": 97,
          "temp_kf": 0
        },
        "weather": [
          {
            "id": 800,
            "main": "Clear",
            "description": "clear sky",
            "icon": "01d"
          }
        ],
        "clouds": {
          "all": 0
        },
        "wind": {
          "speed": 2.06,
          "deg": 247.501
        },
        "rain": {
          
        },
        "sys": {
          "pod": "d"
        },
        "dt_txt": "2017-07-09 06:00:00"
      },
      {
        "dt": 1499590800,
        "main": {
          "temp": 289.43,
          "temp_min": 289.43,
          "temp_max": 289.43,
          "pressure": 1024.62,
          "sea_level": 1029.07,
          "grnd_level": 1024.62,
          "humidity": 100,
          "temp_kf": 0
        },
        "weather": [
          {
            "id": 800,
            "main": "Clear",
            "description": "clear sky",
            "icon": "02d"
          }
        ],
        "clouds": {
          "all": 8
        },
        "wind": {
          "speed": 2.07,
          "deg": 251.5
        },
        "rain": {
          
        },
        "sys": {
          "pod": "d"
        },
        "dt_txt": "2017-07-09 09:00:00"
      },
      {
        "dt": 1499601600,
        "main": {
          "temp": 292.483,
          "temp_min": 292.483,
          "temp_max": 292.483,
          "pressure": 1023.8,
          "sea_level": 1028.23,
          "grnd_level": 1023.8,
          "humidity": 92,
          "temp_kf": 0
        },
        "weather": [
          {
            "id": 800,
            "main": "Clear",
            "description": "clear sky",
            "icon": "01d"
          }
        ],
        "clouds": {
          "all": 0
        },
        "wind": {
          "speed": 2.06,
          "deg": 246.004
        },
        "rain": {
          
        },
        "sys": {
          "pod": "d"
        },
        "dt_txt": "2017-07-09 12:00:00"
      },
      {
        "dt": 1499612400,
        "main": {
          "temp": 293.952,
          "temp_min": 293.952,
          "temp_max": 293.952,
          "pressure": 1022.58,
          "sea_level": 1026.91,
          "grnd_level": 1022.58,
          "humidity": 81,
          "temp_kf": 0
        },
        "weather": [
          {
            "id": 800,
            "main": "Clear",
            "description": "clear sky",
            "icon": "01d"
          }
        ],
        "clouds": {
          "all": 0
        },
        "wind": {
          "speed": 2.01,
          "deg": 249.5
        },
        "rain": {
          
        },
        "sys": {
          "pod": "d"
        },
        "dt_txt": "2017-07-09 15:00:00"
      },
      {
        "dt": 1499623200,
        "main": {
          "temp": 293.162,
          "temp_min": 293.162,
          "temp_max": 293.162,
          "pressure": 1021.34,
          "sea_level": 1025.7,
          "grnd_level": 1021.34,
          "humidity": 77,
          "temp_kf": 0
        },
        "weather": [
          {
            "id": 800,
            "main": "Clear",
            "description": "clear sky",
            "icon": "01d"
          }
        ],
        "clouds": {
          "all": 0
        },
        "wind": {
          "speed": 1.51,
          "deg": 114.002
        },
        "rain": {
          
        },
        "sys": {
          "pod": "d"
        },
        "dt_txt": "2017-07-09 18:00:00"
      },
      {
        "dt": 1499634000,
        "main": {
          "temp": 288.079,
          "temp_min": 288.079,
          "temp_max": 288.079,
          "pressure": 1020.44,
          "sea_level": 1024.88,
          "grnd_level": 1020.44,
          "humidity": 73,
          "temp_kf": 0
        },
        "weather": [
          {
            "id": 801,
            "main": "Clouds",
            "description": "few clouds",
            "icon": "02n"
          }
        ],
        "clouds": {
          "all": 20
        },
        "wind": {
          "speed": 2.21,
          "deg": 56.0018
        },
        "rain": {
          
        },
        "sys": {
          "pod": "n"
        },
        "dt_txt": "2017-07-09 21:00:00"
      },
      {
        "dt": 1499644800,
        "main": {
          "temp": 287.469,
          "temp_min": 287.469,
          "temp_max": 287.469,
          "pressure": 1018.69,
          "sea_level": 1023.09,
          "grnd_level": 1018.69,
          "humidity": 69,
          "temp_kf": 0
        },
        "weather": [
          {
            "id": 804,
            "main": "Clouds",
            "description": "overcast clouds",
            "icon": "04n"
          }
        ],
        "clouds": {
          "all": 88
        },
        "wind": {
          "speed": 4.01,
          "deg": 85.5054
        },
        "rain": {
          
        },
        "sys": {
          "pod": "n"
        },
        "dt_txt": "2017-07-10 00:00:00"
      },
      {
        "dt": 1499655600,
        "main": {
          "temp": 288.839,
          "temp_min": 288.839,
          "temp_max": 288.839,
          "pressure": 1015.63,
          "sea_level": 1020.05,
          "grnd_level": 1015.63,
          "humidity": 73,
          "temp_kf": 0
        },
        "weather": [
          {
            "id": 500,
            "main": "Rain",
            "description": "light rain",
            "icon": "10n"
          }
        ],
        "clouds": {
          "all": 92
        },
        "wind": {
          "speed": 4.7,
          "deg": 90.5002
        },
        "rain": {
          "3h": 0.080000000000002
        },
        "sys": {
          "pod": "n"
        },
        "dt_txt": "2017-07-10 03:00:00"
      },
      {
        "dt": 1499666400,
        "main": {
          "temp": 288.956,
          "temp_min": 288.956,
          "temp_max": 288.956,
          "pressure": 1013.31,
          "sea_level": 1017.61,
          "grnd_level": 1013.31,
          "humidity": 99,
          "temp_kf": 0
        },
        "weather": [
          {
            "id": 501,
            "main": "Rain",
            "description": "moderate rain",
            "icon": "10d"
          }
        ],
        "clouds": {
          "all": 92
        },
        "wind": {
          "speed": 5.07,
          "deg": 100.002
        },
        "rain": {
          "3h": 8.135
        },
        "sys": {
          "pod": "d"
        },
        "dt_txt": "2017-07-10 06:00:00"
      },
      {
        "dt": 1499677200,
        "main": {
          "temp": 291.26,
          "temp_min": 291.26,
          "temp_max": 291.26,
          "pressure": 1009.94,
          "sea_level": 1014.23,
          "grnd_level": 1009.94,
          "humidity": 98,
          "temp_kf": 0
        },
        "weather": [
          {
            "id": 501,
            "main": "Rain",
            "description": "moderate rain",
            "icon": "10d"
          }
        ],
        "clouds": {
          "all": 92
        },
        "wind": {
          "speed": 6.7,
          "deg": 115.001
        },
        "rain": {
          "3h": 7.36
        },
        "sys": {
          "pod": "d"
        },
        "dt_txt": "2017-07-10 09:00:00"
      },
      {
        "dt": 1499688000,
        "main": {
          "temp": 294.235,
          "temp_min": 294.235,
          "temp_max": 294.235,
          "pressure": 1007.15,
          "sea_level": 1011.62,
          "grnd_level": 1007.15,
          "humidity": 97,
          "temp_kf": 0
        },
        "weather": [
          {
            "id": 500,
            "main": "Rain",
            "description": "light rain",
            "icon": "10d"
          }
        ],
        "clouds": {
          "all": 92
        },
        "wind": {
          "speed": 4.92,
          "deg": 172.001
        },
        "rain": {
          "3h": 0.445
        },
        "sys": {
          "pod": "d"
        },
        "dt_txt": "2017-07-10 12:00:00"
      },
      {
        "dt": 1499698800,
        "main": {
          "temp": 293.79,
          "temp_min": 293.79,
          "temp_max": 293.79,
          "pressure": 1006.52,
          "sea_level": 1010.89,
          "grnd_level": 1006.52,
          "humidity": 94,
          "temp_kf": 0
        },
        "weather": [
          {
            "id": 500,
            "main": "Rain",
            "description": "light rain",
            "icon": "10d"
          }
        ],
        "clouds": {
          "all": 44
        },
        "wind": {
          "speed": 8.17,
          "deg": 238.002
        },
        "rain": {
          "3h": 0.090000000000003
        },
        "sys": {
          "pod": "d"
        },
        "dt_txt": "2017-07-10 15:00:00"
      },
      {
        "dt": 1499709600,
        "main": {
          "temp": 291.371,
          "temp_min": 291.371,
          "temp_max": 291.371,
          "pressure": 1007.54,
          "sea_level": 1011.82,
          "grnd_level": 1007.54,
          "humidity": 92,
          "temp_kf": 0
        },
        "weather": [
          {
            "id": 500,
            "main": "Rain",
            "description": "light rain",
            "icon": "10d"
          }
        ],
        "clouds": {
          "all": 0
        },
        "wind": {
          "speed": 9.42,
          "deg": 252.001
        },
        "rain": {
          "3h": 0.0049999999999955
        },
        "sys": {
          "pod": "d"
        },
        "dt_txt": "2017-07-10 18:00:00"
      },
      {
        "dt": 1499720400,
        "main": {
          "temp": 288.678,
          "temp_min": 288.678,
          "temp_max": 288.678,
          "pressure": 1009.04,
          "sea_level": 1013.44,
          "grnd_level": 1009.04,
          "humidity": 93,
          "temp_kf": 0
        },
        "weather": [
          {
            "id": 801,
            "main": "Clouds",
            "description": "few clouds",
            "icon": "02n"
          }
        ],
        "clouds": {
          "all": 24
        },
        "wind": {
          "speed": 7.52,
          "deg": 266.505
        },
        "rain": {
          
        },
        "sys": {
          "pod": "n"
        },
        "dt_txt": "2017-07-10 21:00:00"
      }
    ],
    "city": {
      "id": 2911298,
      "name": "Hamburg",
      "coord": {
        "lat": 53.55,
        "lon": 10
      },
      "country": "DE"
    }
  }  
  """


}