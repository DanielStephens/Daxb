package xml.reader

import java.io.InputStream
import javax.xml.stream.XMLInputFactory

import components.Component.Start
import xml.reader.Event.StartEvent

object Readers {

  val factory = XMLInputFactory.newInstance()

  def eventReader(inputStream: InputStream) : StartEvent = new StartEvent(new MemorableReader(factory.createXMLStreamReader(inputStream), () => inputStream.close()))

}
