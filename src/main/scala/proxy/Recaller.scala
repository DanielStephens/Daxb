package proxy

import java.lang.reflect.Method

import event.Caller

class Recaller(target: Any, method: Method) extends Caller[AnyRef] {
  require(method.getParameterCount == 1, "Only methods that accept a single argument can be handled by this recaller")

  private val args: Array[AnyRef] = Array{1}

  override def call(value: AnyRef) = {
    args{0} = value
    method.invoke(target, args)
  }

}
