package DFG

import chisel3._
import chisel3.util.unless
import chisel3.util.Enum

class DataLink extends Bundle{
  val data  = Input(UInt(width=32))
  val valid = Input(Bool())
  val ready = Output(Bool())
}