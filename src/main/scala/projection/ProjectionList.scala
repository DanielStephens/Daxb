package projection

import components.Component.BaseComponent
import components.DetachedComponent.BaseDetachedComponent
import event.Trigger
import proxy.TriggerProxy

class ProjectionList[T](baseComponent: BaseComponent, clazz : Class[T]) {

  var triggers : List[Trigger[BaseComponent, T]]

  def project(detachedValue: BaseDetachedComponent) : ProjectionBuilder[T] = {
    val trigger : TriggerProxy[T] = new TriggerProxy[T]
    new ProjectionBuilder[T](trigger, clazz)
  }

}
