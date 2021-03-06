import scala.io.Source
import Graph.Node
import Graph.Edge

import scala.util.matching.Regex

//val pattern = "shape".r
//val pattern = new Regex("^[0-9]+\\s\\[.*$", "label", "opcode", "type", "value")

def isEmpty(x: String) = Option(x).forall(_.isEmpty)

def filterNode(line :String) = {
  val pattern = """(\d+)\s*\[label=.*$""".r
  val rsnode: Boolean = pattern findFirstIn line match{
    case Some(pattern(id)) => true
    case None => false
  }

  rsnode
}

def filterEdge(line :String) = {
  val pattern = """(\d+) ->.*$""".r
  val rsnode: Boolean = pattern findFirstIn line match{
    case Some(pattern(id)) => true
    case None => false
  }

  rsnode
}

def returnNode(line :String) = {
  val pattern =
    """(\d+)\s*\[label=\"(.*?)\", opcode=\"(.*?)\", type=\"(.*?)\", value=\"(\d+)\"\].*$""".r
  val pattern(id, label, opcode, ty, va) = line

  (id, opcode, ty, va)
}

def returnEdge(line :String) = {
  val edge = """(\d+) -> (\d+)""".r
  val edge(src, target) = line

  (src, target)
}


//  val edge = """(\d+) -> (\d+)""".r
//  val rsnode: String = pattern findFirstIn line match {
//    case Some(pattern(id, label, opcode, ty, va)) => "Res: " + id + "," + label + "," + opcode + "," + ty + "," + va
//    case None => ""
//  }
//  if (!isEmpty(rsnode)) true else false

val fname = "/home/amirali/git/dotfile-parser/test/out.dot"
val lines = Source.fromFile(fname).getLines().toList
val nodes = lines.filter(filterNode).map(returnNode)
val edges = lines.filter(filterEdge).map(returnEdge)



def makeNode(n: (String, String, String, String)) : Node = {
  val new_node:Node = new Node(n._1.toInt, n._2, n._3, n._4.toInt)
  new_node
}

def makeEdge(e: (String, String)): Edge = {
  val new_edge: Edge = new Edge(e._1.toInt, e._2.toInt)
  new_edge
}


val n = nodes.map(makeNode)
val m = edges.map(makeEdge)

n.foreach(println)
//

//
//    println(f.next())


//printfile(Source.fromFile("/home/amirali/git/dotfile-parser/test/out.dot") getLines)





//for (line <- Source.fromFile("/home/amirali/git/dotfile-parser/test/out.dot").getLines()) {
//
//  val rsnode: String = pattern findFirstIn line match {
//    case Some(pattern(id, label, opcode, ty, va)) => "Res: " + id + "," + label + "," + opcode + "," + ty + "," + va
//    case None => ""
//  }
//
//  if (!isEmpty(rsnode)) {
//    println(rsnode)
//  }
//
//  val rsedge: String = edge findFirstIn line match {
//    case Some(edge(src, tar)) => "Res: " + src + "," + tar
//    case None => ""
//  }
//  if (!isEmpty(rsedge)) {
//    println(rsedge)
//  }
//}
