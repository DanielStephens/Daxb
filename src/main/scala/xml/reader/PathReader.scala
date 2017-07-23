package xml.reader

import javax.xml.stream.XMLStreamConstants

import components.Component.{Attribute, BaseComponent, Element, Value}
import components.Components
import xml.reader.Event._

import scala.util.{Failure, Success, Try}

class PathReader(reader : MemorableReader){

  var path2 : Option[BaseComponent] = Components.head()

  def next : Option[BaseComponent] = {
    if(!reader.hasNext) Option.empty else path = resolveEvent(reader.next, )
  }

  private def resolveEvent(next : Int, path : BaseComponent) : Try[BaseComponent] = {
    next match {
      case XMLStreamConstants.START_ELEMENT => reader.value.map(path.appendElement(_))
      case XMLStreamConstants.CHARACTERS => reader.value.map(path.appendValue(_))
      case XMLStreamConstants.ATTRIBUTE => reader.value.map(path.appendAttribute(_))
      case XMLStreamConstants.END_ELEMENT => levelAbove(path)
    }
  }

  private def levelAbove(path : BaseComponent) : Try[BaseComponent] = {
    path match {
      case Attribute(l, p) => Success(p)
      case Element(l, p) => Success(p)
      case Value(l, p) => Success(p)
      case _ => Failure()
    }
  }
}
