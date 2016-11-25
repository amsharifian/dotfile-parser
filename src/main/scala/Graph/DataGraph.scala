package Graph

import scala.io.Source
import scala.util.matching.Regex
import scala.collection.mutable.ListBuffer

/**
  * Created by amirali
  *
  */
class DataGraph(fname :String) {

  private def filterNode(line :String) = {
    val pattern = """(\d+)\s*\[label=.*$""".r
    val rsnode: Boolean = pattern findFirstIn line match{
      case Some(pattern(id)) => true
      case None => false
    }

    rsnode
  }

  private def filterEdge(line :String) = {
    val pattern = """(\d+) ->.*$""".r
    val rsnode: Boolean = pattern findFirstIn line match{
      case Some(pattern(id)) => true
      case None => false
    }

    rsnode
  }

  private def returnNode(line :String) = {
    val pattern =
      """(\d+)\s*\[label=\"(.*?)\", opcode=\"(.*?)\", type=\"(.*?)\", value=\"(\d+)\"\].*$""".r
    val pattern(id, label, opcode, ty, va) = line

    (id, opcode, ty, va)
  }

  private def returnEdge(line :String) = {
    val edge = """(\d+) -> (\d+)""".r
    val edge(src, target) = line

    (src, target)
  }

  private def makeNode(n: (String, String, String, String)) : Node = {
    val new_node:Node = new Node(n._1.toInt, n._2, n._3, n._4.toInt)
    new_node
  }

  private def makeEdge(e: (String, String)): Edge = {
    val new_edge: Edge = new Edge(e._1.toInt, e._2.toInt)
    new_edge
  }

  def findById(n : Int): Node = {
    val fnode: Node = NodeList.find(x => x.ID == n).get
    fnode
  }


  val Nodes = Source.fromFile(fname).getLines.toList.filter(filterNode).map(returnNode)
  val Edges = Source.fromFile(fname).getLines.toList.filter(filterEdge).map(returnEdge)

  val NodeList :List[Node] = Nodes.map(makeNode)
  val EdgeList :List[Edge] = Edges.map(makeEdge)

  val SrcSet : Set[Int] = EdgeList.map(x => x.Tar).toSet
  val TarSet : Set[Int] = EdgeList.map(x => x.Src).toSet

  //Fixing edge direction
//  val imporTarget = ListBuffer.empty[Int]
//
//  for(e <- EdgeList){
//    if(NodeList.find(x => (x.ID == e.Src)).get.NType)
//      e.dir = true
//    else
//      if(!imporTarget.exists(x => x == e.Tar))
//        e.dir = false
//      else
//        e.dir = true
//        imporTarget += e.Tar
//
//  }

  //Fixing single or double problem
//  for(e <- EdgeList) {
//    if (NodeList.find(x => (x.ID == e.Src)).get.NType)
//      NodeList.find(x => (x.ID == e.Tar)).get.single = true
//  }
}
