package Graph

/**
  * Created by amirali
  */
class Edge(esrc: Int, etar: Int) {
  val Src :Int = esrc
  val Tar :Int = etar
  var dir :Boolean = false

  override def toString: String = Src + " -> " + Tar
}
