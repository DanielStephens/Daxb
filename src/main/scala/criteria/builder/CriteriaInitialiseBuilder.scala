package criteria.builder

trait CriteriaInitialiseBuilder[T] {

  def createWith(initializer : () => T) : CriteriaFinalizeBuilder[T]

}
