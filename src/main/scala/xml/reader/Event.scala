package xml.reader

import javax.xml.stream.{XMLStreamConstants, XMLStreamReader}

import components.Component._
import components.Component.BaseComponent

object Event {

  sealed abstract class ReadEvent(component: BaseComponent, reader : MemorableReader){
    def next : ReadEvent = {
      if(!reader.hasNext) EndDocumentEvent(reader) else resolveEvent(reader.next)
    }

    private def resolveEvent(next : Int) : ReadEvent = {
      next match {
        case XMLStreamConstants.START_ELEMENT => StartEvent(reader)
        case XMLStreamConstants.CHARACTERS => ValueEvent(component, reader)
        case XMLStreamConstants.ATTRIBUTE => AttributeEvent(resolveAttribute, reader)
        case XMLStreamConstants.END_ELEMENT => EndEvent(levelAbove, reader)
      }
    }

    private def resolveAttribute : Attribute = {
      component match {
        case Attribute(l, p) => p.appendAttribute(reader.value.get)
        case _ => component.appendAttribute(reader.value.get)
      }
    }

    private def levelAbove : BaseComponent = {
      component match {
        case Attribute(l, p) => p
        case Element(l, p) => p
        case Value(l, p) => p
        case _ => component
      }
    }
  }

  sealed case class StartEvent(reader : MemorableReader) extends ReadEvent(new Start, reader)

  sealed case class ValueEvent(sC: BaseComponent, reader : MemorableReader) extends ReadEvent(sC, reader)

  sealed case class AttributeEvent(sC: Attribute, reader : MemorableReader) extends ReadEvent(sC, reader)

  sealed case class EndEvent(sC: BaseComponent, reader : MemorableReader) extends ReadEvent(sC, reader)

  sealed case class EndDocumentEvent(reader : MemorableReader) extends ReadEvent(Start(), reader){
    override def next : ReadEvent = this
  }
}
