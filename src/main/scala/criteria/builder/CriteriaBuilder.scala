package criteria.builder

import builder.Builder
import components.Component.BaseComponent
import criteria.Criteria

object CriteriaBuilder {

  def forType[T](clazz : Class[T]) : CriteriaPathBuilder[T] = {
    baseComponent : BaseComponent => pathed[T](baseComponent)
  }

  private def pathed[T](baseComponent: BaseComponent) : CriteriaInitialiseBuilder[T] = {
    (initialize : () => T) => initialized[T](baseComponent, initialize)
  }

  private def initialized[T](baseComponent: BaseComponent, initializer : () => T) : CriteriaFinalizeBuilder[T] = {
    (finalizer : T => Unit) => finalized[T](baseComponent, initializer, finalizer)
  }

  private def finalized[T](baseComponent: BaseComponent, initializer : () => T, finalizer : T => Unit) : Builder[Criteria[T]] = {
    () => built[T](baseComponent, initializer, finalizer)
  }

  private def built[T](baseComponent: BaseComponent, initializer : () => T, finalizer : T => Unit) : Criteria[T] = new Criteria[T](baseComponent, initializer, finalizer, List.empty)

}
