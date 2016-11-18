package Graph

import scala.io.Source
import scala.util.matching.Regex

/**
  * Created by amirali
  *
  */
class Graph(fname :String) {

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


  val Nodes = Source.fromFile(fname).getLines.toList.filter(filterNode).map(returnNode)
  val Edges = Source.fromFile(fname).getLines.toList.filter(filterEdge).map(returnEdge)


}
