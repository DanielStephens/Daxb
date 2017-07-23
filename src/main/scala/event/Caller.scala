package event

trait Caller[T] {

  def call(value : T)

}
