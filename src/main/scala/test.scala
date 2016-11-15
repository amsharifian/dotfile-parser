import scala.io.Source
import scala.util.matching.Regex
import uk.co.turingatemyhamster.graphvizs._
import uk.co.turingatemyhamster.graphvizs.dsl._
import uk.co.turingatemyhamster.graphvizs.exec._

object main {

  //val pattern = "shape".r
  //val pattern = new Regex("^[0-9]+\\s\\[.*$", "label", "opcode", "type", "value")
  val pattern = """(\d+)\s*\[label=\"(.*?)\", opcode=\"(.*?)\", type=\"(.*?)\", value=\"(\d+)\"\].*$""".r
  val edge    = """(\d+) -> (\d+)""".r

  def isEmpty(x: String) = Option(x).forall(_.isEmpty)

  def main(args: Array[String]) {

    //pattern(id,label,opcode,type,value) = 
    //val pattern = """(\d+).*$""".r

    for(line <- Source.fromFile("test/out.dot").getLines()){
        
        //println(line)
        val rsnode: String = pattern findFirstIn line match {
          case Some(pattern(id, label, opcode, ty, va)) => "Res: " + id + "," + label + "," + opcode + "," + ty + "," + va
          case None                                     => ""
        }

        if(!isEmpty(rsnode)){
          println(rsnode)
        }
        //println(rsnode)
        
        val rsedge: String = edge findFirstIn line match {
          case Some(edge(src, tar)) => "Res: " + src+ "," + tar
          case None                                     => ""
        }
        if(!isEmpty(rsedge)){
          println(rsedge)
        }
        //println(rsedge)
        //val pattern(id) = line
        //pattern(id,label,opcode,ty,value) = line
        //println((pattern findFirstIn line).mkString(","))
        //println((pattern findAllMatchIn line).mkString(","))
        //if(!isEmpty((pattern findAllMatchIn line).mkString(",")))
          //println(line)
          //line match {
            //case pattern(label, opcode, type, value) => 
          //}
    }
    //val g = StrictDigraph(
      //id = "g",
      //"lacI" :| ("shape" := "box"),
      //"tetR" :| ("shape" := "box"),
      //"lacI" --> "tetR" :| ("label" := "represses")
    //)
    
    //println("Input graph:")
    //renderGraph(g, System.out)
  }
}

