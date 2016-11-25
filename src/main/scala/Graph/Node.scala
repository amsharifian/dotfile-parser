package Graph

/**
  * Created by amirali
  */
class Node (cid :Int, copcode :String, ctype :String, cvalue :Int ){
  val ID :Int = cid
  val OpCode :String = copcode
  val Type:String = ctype
  val value :Int = cvalue
  val constStat :Int = isConst(copcode)
  var single:Boolean = false

  def isConst(c: String) :Int = {
    if (c == "const") 1 else 0
  }
  override def toString: String = "Node(" + ID + "): " + OpCode + " Val: " + constStat
}
