package xml.holder

import io.Source
import xml.runner.Runner
import xml.validator.Validator


class SourceHolder(source: Source, runner: Runner, validator: Validator) {

  def run: Unit = runner.run(source.inputStream)

  def validate : Unit = validator.validate(source.inputStream)

}
