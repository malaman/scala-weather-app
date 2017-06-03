# Introduction

The repository is a full stack application, which shows weather forecasts for selected city.

## api (backend)

Tech stack:
 - Scala
 - Playframework

 Following external services are used:
  - http://openweathermap.org/
  - Google maps API


 ## frontend-scala-js

Tech stack:
 - Scala.js
 - scalajs-react package
 - circe


# Setup

## api (backend)


1. Clone repository
2. `cd api`
3. `WEATHER_API_KEY=<YOUR_OPENWEATHERMAP_API_KEY> GOOGLE_API_KEY=<YOU_GOOGLE_MAPS_API_KEY> sbt run`.

 [http://openweathermap.org](http://openweathermap.org/) and [Google Map API key](https://developers.google.com/maps/documentation/javascript/get-api-key) are required to start backend. It is also possible to use backend from demo application
(http://ec2-52-59-160-108.eu-central-1.compute.amazonaws.com:9000)

## frontend-scala-js

1. Clone repository
2. `cd frontent-scala-js`
3. `yarn install`
4. `sbt fastOptJS`
5. `npm run start`
6. Open in browser `http://localhost:8080`

webpack-dev-server is used for serving frontend assets in dev enviroment.

If you want to use backend from demo application (`http://ec2-52-59-160-108.eu-central-1.compute.amazonaws.com:9000`) change `API_HOST` env variable to for start task in `package.json`

## Road map

- [ ] Integrate scalajs-router into application (use separate route for weather in selected city)

- [ ] Save selected cities in browsers localStorage

- [ ] Add `slick` package to api project for saving user and selected cities information
