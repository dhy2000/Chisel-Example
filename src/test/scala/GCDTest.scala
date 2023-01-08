import chiseltest.{ChiselScalatestTester, _}
import org.scalatest.freespec.AnyFreeSpec

import scala.util.Random

class GCDTest extends AnyFreeSpec with ChiselScalatestTester {
  "gcd" in {
    test(new GCD)
      .withAnnotations(Seq(VcsBackendAnnotation, WriteFsdbAnnotation)) // use VCS backend
      { dut =>
        for (i <- 1 to 100) {
          val x = Random.between(1, 64)
          val y = Random.between(1, 64)
          dut.value1.poke(x)
          dut.value2.poke(y)
          dut.loadingValues.poke(true)
          dut.clock.step()
          dut.loadingValues.poke(false)
          while (!dut.outputValid.peekBoolean()) {
            dut.clock.step()
          }

          val expected = BigInt(x).gcd(BigInt(y))
          dut.outputGCD.expect(expected)
          println(s"$i: gcd($x, $y) = 1")
        }
      }
  }
}
