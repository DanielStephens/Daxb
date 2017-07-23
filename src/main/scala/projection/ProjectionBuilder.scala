package projection

import net.sf.cglib.proxy.Enhancer
import proxy.TriggerProxy

class ProjectionBuilder[T](triggerProxy: TriggerProxy[T], clazz : Class[T]) {

  def into : T = Enhancer.create(clazz, triggerProxy).asInstanceOf[T]

}
