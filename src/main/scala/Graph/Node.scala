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
  var single:Boolean = false

  private def setType(ty : String): Boolean = {
    if(ty == "const") true else false
  }

  override def toString: String = "Node(" + ID + "): " + OpCode
}
