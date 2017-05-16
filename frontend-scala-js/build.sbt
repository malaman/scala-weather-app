version      in ThisBuild := "1.0-SNAPSHOT"
organization in ThisBuild := "com.github.japgolly.experiment.webpack"
shellPrompt  in ThisBuild := ((s: State) => Project.extract(s).currentRef.project + "> ")

lazy val root = Experiment.root
