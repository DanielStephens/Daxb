package xml.runner

import java.io.InputStream

import scala.util.Try

trait Runner {

  def run(inputStream: InputStream)

}
