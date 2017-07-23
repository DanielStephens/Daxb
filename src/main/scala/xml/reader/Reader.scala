package xml.reader

trait Reader {

  def next : Int

  def hasNext : Boolean

  def value : Option[String]

}
