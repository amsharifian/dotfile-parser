import Graph._

import chisel3._
import chisel3.util.unless
import chisel3.util.Enum

val fname = "/home/amirali/git/dotfile-parser/test/out.dot"
val mygraph = new DataGraph(fname)
mygraph.NodeList.foreach(println)
mygraph.EdgeList.foreach(println)



