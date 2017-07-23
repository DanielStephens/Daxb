package io

import java.io.InputStream

class BasicSource(builder : () => InputStream ) extends Source {
  override def inputStream: InputStream = builder()
}
