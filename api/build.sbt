name := """play-scala"""

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayScala)

scalaVersion := "2.11.11"

libraryDependencies += cache
libraryDependencies += ws
libraryDependencies += filters
libraryDependencies += "org.xerial" % "sqlite-jdbc" % "3.18.0"
libraryDependencies += "com.typesafe.play" %% "play-slick" % "2.0.0"
libraryDependencies += "com.typesafe.play" %% "play-slick-evolutions" % "2.0.0"
libraryDependencies += "com.github.nscala-time" %% "nscala-time" % "2.16.0"
libraryDependencies +=  "org.scalatestplus.play" %% "scalatestplus-play" % "1.5.0" % "test"
