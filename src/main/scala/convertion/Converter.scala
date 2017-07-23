package convertion

trait Converter[I, O] {

  def convert(input : I) : O

}
