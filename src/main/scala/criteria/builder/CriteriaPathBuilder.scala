package criteria.builder

import components.Component.BaseComponent

trait CriteriaPathBuilder[T] {

  def at(baseComponent: BaseComponent) : CriteriaInitialiseBuilder[T]
}
