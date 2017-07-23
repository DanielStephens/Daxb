package xml.validator

import java.io.InputStream

trait Validator {

  def validate(inputStream: InputStream)

}
