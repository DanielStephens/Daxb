package event

trait Trigger[E, T] {

  def trigger(event : E, value : T)

}
