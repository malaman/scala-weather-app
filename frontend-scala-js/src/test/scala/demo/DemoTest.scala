package demo

import utest._
import japgolly.scalajs.react._
import japgolly.scalajs.react.test._
import japgolly.microlibs.testutil.TestUtil._
import scalaz.std.string._

object DemoTest extends TestSuite {

  override def tests = TestSuite {

    'magpiePic {
      ReactTestUtils.withRenderedIntoDocument(DemoPage.MagpiePic()) { m =>
        assertEq(m.outerHtmlScrubbed(), """<img src="/test/assets/magpie.jpg" class="img-circle">""")
      }
    }

  }
}
