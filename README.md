# Introduction

Yet another weather application, based on Scala.js, scalajs-react and Playframework.

Features:
 - city search with autocomplete
 - weather forecast is displayed for a selected city 
 - authentication via Github OAuth
 - saving city to favorites (for authenticated users) 
 
# Demo 

[Demo](http://ec2-52-59-160-108.eu-central-1.compute.amazonaws.com/)

# Frontend

Tech stack:
 - [Scala.js](https://github.com/scala-js/scala-js)
 - [scalajs-react](https://github.com/japgolly/scalajs-react)
 - [diode](https://github.com/suzaku-io/diode)
 - [scalajs.dom](https://github.com/scala-js/scala-js-dom)
 - [circe](https://circe.github.io/circe/)
 
## Setup

1. Clone repository
2. `cd frontent`
3. `yarn install`
4. `sbt fastOptJS`
5. `npm run start:frontend`
6. Open in browser `http://localhost:8080`

`npm run start:frontend` command uses `http://ec2-52-59-160-108.eu-central-1.compute.amazonaws.com/api` host to fetch data from API


## JS packages

The project is using following JavaScript packages:

- [react-select](https://github.com/JedWatson/react-select)
- [google-map-reaact](https://github.com/istarkov/google-map-react)
- [lodash-throttle](https://github.com/lodash/lodash)

Full list of JS dependencies is in `package.json`.

## Routing

[react-select](https://github.com/JedWatson/react-select) is used for SPA routing.

## State Management

[diode](https://github.com/suzaku-io/diode) is used for managing application state.

## XHR client

XHR client of [scalajs.dom](https://github.com/scala-js/scala-js-dom) is used for XHR requests.
[circe](https://github.com/circe/circe) is used for JSON encoding/decoding

## Bundler 

Webpack is used to bundle Scala.js generated javascript.
SCSS/Images are bundled via webpack as well.

# API

Application backend is writted with following tech stack:
 - Scala
 - Playframework
 - Slick

 Following external services are used:
  - http://openweathermap.org/
  - Github OAuth  
