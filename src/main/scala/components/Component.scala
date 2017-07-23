package components

import components.DetachedComponent._
import convertion.Converter

object Component {

  sealed abstract class BaseComponent(){

    def appendElement(localName : String) : Element = Element(localName, this)

    def appendAttribute(localName : String) : Attribute = Attribute(localName, this)

    def appendValue(value : String) : Value = Value(value, this)

    def append(element: BaseDetachedComponent) : BaseComponent = element match {
      case DetachedHead() => this
      case DetachedElement(l, p) => append(p).appendElement(l)
      case DetachedAttribute(l, p) => append(p).appendAttribute(l)
      case DetachedValue(_, _) => appendValue()
    }

    def isSameLocationAs(baseComponent: BaseComponent) : Boolean = baseComponent.getClass.equals(this.getClass)

  }

  sealed abstract class ChildComponent(parent : BaseComponent) extends BaseComponent{

    override def isSameLocationAs(baseComponent: BaseComponent) : Boolean = {
      baseComponent match {
        case Element(_, p) => super.isSameLocationAs(baseComponent) && parent.isSameLocationAs(p)
        case Attribute(_, p) => super.isSameLocationAs(baseComponent) && parent.isSameLocationAs(p)
        case Value(_, p) => parent.isSameLocationAs(p)
        case _ => super.isSameLocationAs(baseComponent)
      }
    }
  }

  sealed abstract class NamedChildComponent(localName : String, parent : BaseComponent) extends ChildComponent(parent){
    override def isSameLocationAs(baseComponent: BaseComponent) : Boolean = {
      baseComponent match {
        case Element(l, _) => super.isSameLocationAs(baseComponent) && l.equals(localName)
        case Attribute(l, _) => super.isSameLocationAs(baseComponent) && l.equals(localName)
        case _ => super.isSameLocationAs(baseComponent)
      }
    }
  }

  sealed case class Element(localName : String, parent : BaseComponent) extends NamedChildComponent(localName, parent)

  sealed case class Attribute(localName : String, parent : BaseComponent) extends NamedChildComponent(localName, parent)

  sealed case class Value(value : String, parent : BaseComponent) extends ChildComponent(parent)

  sealed case class Start() extends BaseComponent()

}
