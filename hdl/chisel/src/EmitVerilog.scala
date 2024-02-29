package %NAME%

import chisel3._
import circt.stage.ChiselStage

object EmitVerilog {
  def main(args: Array[String]): Unit = {
    ChiselStage.emitSystemVerilogFile(
      gen = new Foo(),
      args = Array("--target-dir", "rtl"),
      firtoolOpts = Array("-disable-all-randomization", "-strip-debug-info")
    )
  }
}

class Foo() extends Module {
  val in = IO(Input(Bool()))
  val out = IO(Output(Bool()))
  out := in
}
