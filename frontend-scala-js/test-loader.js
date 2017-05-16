// Plundered from scalajs-bundler
// https://github.com/scalacenter/scalajs-bundler/blob/master/sbt-scalajs-bundler/src/main/scala/scalajsbundler/JsDomTestEntries.scala
{
  var x0 = require('./target/scala-2.12/demo-test-fastopt.js');
  Object.keys(x0).forEach((function(x1) {
    return window[x1] = x0[x1]
  }))
}
