package xml.reader

import javax.xml.stream.{XMLInputFactory, XMLStreamConstants, XMLStreamReader}
import java.io.InputStream
import javax.management.modelmbean.XMLParseException

import components.Component.{Attribute, BaseComponent, Element, Start, Value}
import xml.reader
import xml.reader.Event.{EndEvent, ReadEvent, StartEvent}

import scala.util.{Failure, Success, Try}

class MemorableReader(reader : XMLStreamReader, onClose : () => Unit) extends Reader with AutoCloseable{

  private var attributePositional : Int = 0

  private var currentValue : Try[String] = failure()

  private def failure() : Try[String] = Failure(new XMLParseException("No value is available for this XML event"))

  private def success(value: String) : Try[String] = Success(value)

  override def next : Int = {
    currentValue = failure()

    if (!reader.hasNext) XMLStreamConstants.END_DOCUMENT

    else if(reader.isStartElement && readAttribute) XMLStreamConstants.ATTRIBUTE

    else
      reader.next() match {
        case XMLStreamConstants.START_ELEMENT =>
          attributePositional = 0
          currentValue = success(reader.getLocalName)
          XMLStreamConstants.START_ELEMENT
        case XMLStreamConstants.CHARACTERS =>
          currentValue = success(reader.getText)
          XMLStreamConstants.CHARACTERS
        case _ =>
      }
  }

  private def readAttribute() : Boolean = {
    if(attributePositional < reader.getAttributeCount){
      currentValue = success(reader.getAttributeValue(attributePositional))
      attributePositional += 1
      true
    } else false
  }

  override def hasNext : Boolean = reader.hasNext

  override def value : Try[String] = currentValue

  override def close() = onClose()

}
