package criteria

import components.Component.BaseComponent
import components.DetachedComponent.{BaseDetachedComponent, DetachedValue}
import event.{SimplePathTrigger, Trigger}

class Criteria[T]( baseComponent: BaseComponent, initializer: () => T, finalizer: (T) => Unit, seq: List[BaseDetachedComponent]){


}
