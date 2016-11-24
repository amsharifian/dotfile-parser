package Graph

/**
  * Created by amirali
  */
class Node (cid :Int, copcode :String, ctype :String, cvalue :Int ){
  val ID :Int = cid
  val OpCode :String = copcode
  val Type:String = ctype
  val value :Int = cvalue
  val NType :Boolean = setType(ctype)
  val Target :Boolean = false

  def isTarget(): Boolean = {
    Target
  }

  private def setType(ty : String): Boolean = {
    if(ty == "const") true else false
  }

  override def toString: String = "Node(" + ID + "): " + OpCode
}
