import sbt._, Keys._
import org.scalajs.core.tools.io.FileVirtualJSFile
import org.scalajs.core.tools.jsdep.ResolvedJSDependency
import org.scalajs.core.tools.linker.backend.ModuleKind
import org.scalajs.jsenv.nodejs.JSDOMNodeJSEnv
import org.scalajs.sbtplugin.ScalaJSPlugin, ScalaJSPlugin.autoImport._
import org.scalajs.sbtplugin.FrameworkDetectorWrapper
import org.scalajs.sbtplugin.Loggers.sbtLogger2ToolsLogger
import org.scalajs.testadapter.ScalaJSFramework

object Webpack {

  // Fine for this experimentation; replace with proper logic later
  object Hardcoded {
    val testBundleOutput = "demo-test-fastopt-bundle.js"
  }

  def testSettings: Seq[Setting[_]] =
    Seq(
      bundle := bundleTask.value,
      emitSourceMaps := false, // https://github.com/webpack/webpack/issues/4518
      loadedTestFrameworks := testFrameworkTask.value
    )

  val bundle = taskKey[File]("xxxxxxxxxxxxxxxxx")

  val bundleTask = Def.task {
    import sys.process._
    // Needs caching too
    fastOptJS.value
    val e = "./webpack --config webpack-test.js".!
    if (e != 0) sys.error("webpack returned exit status of " + e)
    crossTarget.value / Hardcoded.testBundleOutput
  }

  // Plundered from scalajs-bundler
  // https://github.com/scalacenter/scalajs-bundler/blob/master/sbt-scalajs-bundler/src/main/scala/scalajsbundler/sbtplugin/ScalaJSBundlerPlugin.scala
  val testFrameworkTask = Def.task[Map[TestFramework, sbt.testing.Framework]] {
    val console = scalaJSConsole.value
    val toolsLogger = sbtLogger2ToolsLogger(streams.value.log)
    val frameworks = testFrameworks.value

    val outputFile = FileVirtualJSFile(bundle.value)
    val env = new JSDOMNodeJSEnv().loadLibs(Seq(ResolvedJSDependency.minimal(outputFile)))

    val (moduleKind, moduleIdentifier) = (ModuleKind.NoModule, None)

    val detector = new FrameworkDetectorWrapper(env, moduleKind, moduleIdentifier).wrapped

    detector.detect(frameworks, toolsLogger).map { case (tf, frameworkName) =>
      val f = new ScalaJSFramework(frameworkName, env, moduleKind, moduleIdentifier, toolsLogger, console)
      (tf, f)
    }
  }

}
