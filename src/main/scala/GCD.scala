import chisel3._
import chisel3.stage.ChiselStage

/**
 * Compute GCD using subtraction method.
 * Subtracts the smaller from the larger until register y is zero.
 * value in register x is then the GCD
 */
class GCD extends Module {
  // set module name
  override val desiredName = "gcd"

  // define io ports
  val value1: UInt = IO(Input(UInt(16.W)))
  val value2: UInt = IO(Input(UInt(16.W)))
  val loadingValues: Bool = IO(Input(Bool()))
  val outputGCD: UInt = IO(Output(UInt(16.W)))
  val outputValid: Bool = IO(Output(Bool()))

  // suggest signal name
  loadingValues.suggestName("load")
  outputGCD.suggestName("output_gcd")
  outputValid.suggestName("valid")

  val x: UInt = RegInit(UInt(), 0.U)
  val y: UInt = RegInit(UInt(), 0.U)

  when(x > y) {
    x := x - y
  }.otherwise {
    y := y - x
  }

  when(loadingValues) {
    x := value1
    y := value2
  }

  outputGCD := x
  outputValid := y === 0.U
}

object GCD {
  def main(args: Array[String]): Unit = {
    // generate verilog to "output_dir" (use "--target_dir" to set path to generated files)
    new ChiselStage().emitVerilog(new GCD, Array("--target-dir", "output_dir"))
  }
}