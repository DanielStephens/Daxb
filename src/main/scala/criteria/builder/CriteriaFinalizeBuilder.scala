package criteria.builder

import builder.Builder
import criteria.Criteria

trait CriteriaFinalizeBuilder[T] {

  def andDo(finalizer : (T) => Unit) : Builder[Criteria[T]]

}
