import Graph._

val fname = "/home/amirali/git/dotfile-parser/test/out.dot"
val mygraph = new DataGraph(fname)
//val mm = new Edge(1,2)
mygraph.NodeList.foreach(println)
