package node

import chisel3.iotesters.{PeekPokeTester, Driver, ChiselFlatSpec}
import scala.util.Properties.envOrElse
import scala.collection.mutable.ArrayBuffer

class NodeTests(d: DataFlow) extends PeekPokeTester(d) {
  val (a, b, z) = (3, 2, 13)
  poke(d.io.a.data, a)
  poke(d.io.b.data, b)
  poke(d.io.a.valid, 1)
  poke(d.io.b.valid, 1)
  poke(d.io.e, 1)
  step(1)
  step(1)
  step(1)
  step(1)
  step(1)
  step(1)
  step(1)
  step(1)
  step(1)
  step(1)
  expect(d.io.c.data, z)
}

object Launcher {
  def main(args: Array[String]): Unit = {
    // Choose the default backend based on what is available.
    lazy val firrtlTerpBackendAvailable: Boolean = {
      try {
        val cls = Class.forName("chisel3.iotesters.FirrtlTerpBackend")
        cls != null
      } catch {
        case e: Throwable => false
      }
    }
    lazy val defaultBackend = if (firrtlTerpBackendAvailable) {
      "firrtl"
    } else {
      ""
    }
    val backendName = envOrElse("TESTER_BACKENDS", defaultBackend).split(" ").head
    println(backendName)
    if(Driver(() => new DataFlow(), backendName){
        (d) => new NodeTests(d)
        }){

          println(s"OK!")
        }
    else
      println(s"Failed!")

        //println(s"Failed!")
      //println(s"OK!")
    //if(Driver(() => new GCD(), backendName) {
      //})
    //else
      //errors += s"Tutorial $testName: test error occurred"
  }
}

//class GCDTester extends ChiselFlatSpec {
  //behavior of "GCD"
  //backends foreach { backend =>
    //it should s"correctly compute GCD of two numbers in $backend" in {
      //Driver(() => new GCD, backend)(c => new GCDTests(c)) should be (true)
    //}
  //}
//}
