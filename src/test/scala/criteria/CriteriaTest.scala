package criteria

import components.Components
import criteria.builder.CriteriaBuilder
import resource.TestObject
import org.scalatest.FunSuite

class CriteriaTest extends FunSuite {

  test("abc"){

    val crit : Criteria[TestObject] = CriteriaBuilder.forType(classOf[TestObject]).at(Components.elementOf("FIRST").appendElement("SECOND")).createWith(() => new TestObject("DEFAULT")).andDo((t : TestObject) => println(t)).build
    val crit2 : crit.project(Components.detachedElementOf("")).into().string(null)



    val runner : Runner = Runners.runnerFor(crit)

    val s : SourceHolder = SourceHolders.of(source, runner, validator)
    s.validate
    s.run


    val t : TestObject = new TestObject("abc")
    t.string = "bca"

    assert(t.string == "bca")
  }

}
