package io

import java.io.ByteArrayInputStream
import java.nio.charset.StandardCharsets

import scala.util.{Try}

object Sources {

  def fromString(input : String) : Try[Source] = {
    Try( new BasicSource( () => new ByteArrayInputStream(input.getBytes(StandardCharsets.UTF_8))) )
  }

}