package proxy

import java.lang.reflect.Method

import event.Trigger
import net.sf.cglib.proxy.{MethodInterceptor, MethodProxy}

class TriggerProxy[E, T] extends MethodInterceptor with Trigger[E, T] {

  var answer : Option[Recaller] = Option.empty[Recaller]

  override def intercept(target: Any, method: Method, args: Array[AnyRef], proxy: MethodProxy): AnyRef = {
    answer = answer.orElse(Option(new Recaller(target, method)))
    null
  }

  override def trigger(event: E, value: T): Unit = ???

}
