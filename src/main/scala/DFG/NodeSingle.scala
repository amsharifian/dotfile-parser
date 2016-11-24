package DFG

import chisel3._
import chisel3.util.unless
import chisel3.util.Enum

/**
  * Created by amirali on 23/11/16.
  */

class NodeSingle extends Module {
  val io = IO(new Bundle {
    val a  = new DataLink()
    val b  = Input(UInt(width=32))
    val e  = Bool(INPUT)
    val op = Input(UInt(width=2))
    val c  = new DataLink().flip
  })
  val x  = Reg(UInt(width=32), init = UInt(0))
  val y  = Reg(UInt(width=32), init = UInt(0))
  val z  = Reg(UInt(width=32), init = UInt(0))

  val s_init :: s_comp :: s_finish :: Nil = Enum(UInt(), 3)
  val state = Reg(init = s_init)

  z := x + y

  when (state === s_init){
    io.a.ready := UInt(1)
    io.c.valid := UInt(0)
  }
  when (state === s_comp){
    io.a.ready := UInt(0)
    io.c.valid := UInt(0)
  }
  when(state === s_finish){
    io.c.valid := UInt(1)
    io.c.data  := z
  }

  when (state === s_init){
    when(io.a.valid) { state := s_comp; x := io.a.data; y := io.b }
      .otherwise{ state := s_init }
  }
  when (state === s_comp){
    when(io.c.ready) { state := s_finish }
      .otherwise { state := s_comp}
  }
  when (state === s_finish){
    when(io.c.ready){ state := s_finish }
      .otherwise{ state := s_init }
  }


}



