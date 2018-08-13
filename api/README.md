
## Install

 - install `scala` und `sbt`
 - `git clone https://github.com/malaman/scala-weather-app.git`
 - `cd api`
 - `sbt compile`

## Run

It is necessary to set following environment variables to run the project:
 - `DB_PATH` jdbc url for the sqlite3 database  (i.e. DB_PATH=jdbc:sqlite:<absolute path>) if sqlite3 file does not exist, new file will be created
 - `WEATHER_API_KEY` [https://openweathermap.org/](https://openweathermap.org/) api key. It is possible to get test key for free after registration
 
To run the app in dev mode use in `api` directory:
 
`WEATHER_API_KEY=<KEY> DB_PATH=<PATH> sbt run`
