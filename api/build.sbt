name := """scala-weather-app-api"""

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayScala)

scalaVersion := "2.12.1"

libraryDependencies += guice
libraryDependencies += cacheApi
libraryDependencies += ws
libraryDependencies += filters
libraryDependencies += "org.xerial" % "sqlite-jdbc" % "3.18.0"
libraryDependencies += "com.h2database" % "h2" % "1.4.194"
libraryDependencies += "com.typesafe.play" %% "play-json" % "2.6.3"
libraryDependencies += "com.typesafe.play" %% "play-slick" % "3.0.0"
libraryDependencies += "com.typesafe.play" %% "play-slick-evolutions" % "3.0.0"
libraryDependencies += "com.github.nscala-time" %% "nscala-time" % "2.16.0"
libraryDependencies += "org.scalatestplus.play" %% "scalatestplus-play" % "3.0.0" % "test"
libraryDependencies += "io.lemonlabs" %% "scala-uri" % "0.4.16"
