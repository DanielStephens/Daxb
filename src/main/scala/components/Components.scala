package components

import components.Component._
import components.DetachedComponent.{DetachedAttribute, DetachedElement, DetachedHead}

object Components {

  def head() : Start()

  def elementOf(localName: String): Element = Start().appendElement(localName)

  def detachedElementOf(localName : String) : DetachedElement = DetachedHead().appendElement(localName)

  def detachedAttributeOf(localName : String) : DetachedAttribute = DetachedHead().appendAttribute(localName)

}
