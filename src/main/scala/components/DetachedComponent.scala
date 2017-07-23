package components

import convertion.Converter

object DetachedComponent {

  sealed abstract class BaseDetachedComponent(){

    def appendElement(localName : String) : DetachedElement = DetachedElement(localName, this)

    def appendAttribute(localName : String) : DetachedAttribute = DetachedAttribute(localName, this)

    def appendValue[T](converter : Converter[String, T]) : DetachedValue[T] = DetachedValue(converter : Converter[String, T], this)

  }

  sealed case class DetachedHead() extends BaseDetachedComponent

  sealed case class DetachedElement(localName : String, baseDetachedComponent: BaseDetachedComponent) extends BaseDetachedComponent

  sealed case class DetachedAttribute(localName : String, baseDetachedComponent: BaseDetachedComponent) extends BaseDetachedComponent

  sealed case class DetachedValue[T](converter : Converter[String, T], baseDetachedComponent: BaseDetachedComponent) extends BaseDetachedComponent

}
