import sbt._, Keys._
import org.scalajs.sbtplugin.ScalaJSPlugin, ScalaJSPlugin.autoImport._

object Experiment {

  object Ver {
    val Scala         = "2.12.2"
    val ScalaJsReact  = "1.0.0-RC3"
    val MTest         = "0.4.5"
    val Microlibs     = "1.5"
    val Circe         = "0.7.0"
  }

  type PE = Project => Project

  def commonSettings: PE =
    _.enablePlugins(ScalaJSPlugin)
      .settings(
        scalaVersion       := Ver.Scala,
        scalacOptions     ++= Seq("-deprecation", "-unchecked", "-feature",
                                "-language:postfixOps", "-language:implicitConversions",
                                "-language:higherKinds", "-language:existentials"),
        updateOptions      := updateOptions.value.withCachedResolution(true),
        incOptions         := incOptions.value.withNameHashing(true).withLogRecompileOnMacro(false),
        triggeredMessage   := Watched.clearWhenTriggered)

  def utestSettings: PE =
    _.settings(
      scalacOptions in Test += "-language:reflectiveCalls",
      libraryDependencies   += "com.lihaoyi" %%% "utest" % Ver.MTest % Test,
      testFrameworks        += new TestFramework("utest.runner.Framework"),
      requiresDOM           := true)
    .settings(inConfig(Test)(Webpack.testSettings))

  def addCommandAliases(m: (String, String)*): PE = {
    val s = m.map(p => addCommandAlias(p._1, p._2)).reduce(_ ++ _)
    _.settings(s: _*)
  }

  // ==============================================================================================
  lazy val root = Project("root", file("."))
    .configure(commonSettings, utestSettings, addCommandAliases(
      "c"   -> "compile",
      "tc"  -> "test:compile",
      "t"   -> "test",
      "tq"  -> "testQuick",
      "to"  -> "test-only",
      "cc"  -> ";clean;compile",
      "ctc" -> ";clean;test:compile",
      "ct"  -> ";clean;test"))
    .settings(
      name := "demo",
      libraryDependencies ++= Seq(
        "com.github.japgolly.scalajs-react" %%% "core" % Ver.ScalaJsReact,
        "com.github.japgolly.scalajs-react" %%% "test" % Ver.ScalaJsReact % Test,
        "com.github.japgolly.microlibs" %%% "test-util" % Ver.Microlibs % Test,
        "io.circe" %%% "circe-core" % Ver.Circe),
      scalaJSUseMainModuleInitializer := true,
      scalaJSUseMainModuleInitializer in Test := true, // Doesn't seem to work
      mainClass in Test := Some("demo.TestAssets"),
      scalaJSModuleKind := ModuleKind.CommonJSModule)
}
