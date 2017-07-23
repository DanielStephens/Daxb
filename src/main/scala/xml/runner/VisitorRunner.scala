package xml.runner

import java.io.InputStream

import event.Trigger
import xml.reader.Event.ReadEvent


class VisitorRunner(list: List[Trigger[ReadEvent]]) extends Runner {

  override def run(inputStream: InputStream): Unit = {

  }
}
