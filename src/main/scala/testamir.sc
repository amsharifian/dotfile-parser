import scala.collection.mutable
import Graph._

import chisel3._
import chisel3.util.unless
import chisel3.util.Enum

def returnMapSet (n: Int, nlist: List[Edge]): (Int, List[Int]) = {
  n -> nlist.filter(x => x.Tar == n ).map(x => x.Src)
}

val fname = "/home/amirali/git/dotfile-parser/test/out.dot"
val mygraph = new DataGraph(fname)
mygraph.NodeList.foreach(println)
mygraph.EdgeList.foreach(println)
val graphmap = mygraph.SrcSet.map(x => returnMapSet(x, mygraph.EdgeList)).toMap

val test = List(1,2)
val ee = test.map(x => mygraph.findById(x)).map(x => x.constStat).reduceLeft((x,y) => x + y)
graphmap.map(x => (x._1, x._2.map(
  x => mygraph.findById(x)).map(x => x.constStat).reduceLeft((x,y) => x+y)
  ))

mygraph.findById(4)

//graphmap.values.reduceLeft((x,y) => x+y)

//for ((k,v) <- graphmap){
//  v.reduceLeft((x,y) => x+y)
//}


//def compweights (nlist : List[Node], nmap : Map[Int,List[Int]]) = {
//
//}

