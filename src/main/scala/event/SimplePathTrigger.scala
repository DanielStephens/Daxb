package event

import components.Component.BaseComponent

class SimplePathTrigger[T](baseComponent: BaseComponent, caller: (T) => ()) extends Trigger[BaseComponent, T]{

  override def trigger(event: BaseComponent, value: T) = {
    if (baseComponent.isSameLocationAs(event)) caller(value)
  }
}
