package builder

trait Builder[T] {

  def build() : T

}
