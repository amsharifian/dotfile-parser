package Graph

import scala.io.Source
import scala.util.matching.Regex

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


  val Nodes = Source.fromFile(fname).getLines.toList.filter(filterNode).map(returnNode)
  val Edges = Source.fromFile(fname).getLines.toList.filter(filterEdge).map(returnEdge)

  val NodeList :List[Node] = Nodes.map(makeNode)
  val EdgeList :List[Edge] = Edges.map(makeEdge)


}
