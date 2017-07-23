# DAXB (Dan's architecture for XML binding!)

This is a project wrapping StaX xml parsers with my own architecture to better facilitate reading streams of XML. This is completely obsolete and should not be used, however it does attempt to solve a real problem! Reading large/complex XML files using StaX is horrible to write, usually concluding in huge nests of switch statements. Jaxb solves that issue very nicely, but is not so easily streamed and often requires a very high number of annotated classes to be created per XML schema.

Building a 'Criteria' would be done as follows

```scala
val criteria : Criteria[T] = CriteriaBuilder
	.forType(classOf[T])
	.at(Components
			.elementOf("FIRST")
			.appendElement("SECOND")
		)
	.createWith(() => T)
	.andInBatchesOf().perform(T => ())
	.build
```

Projections can be pushed into the Criteria objects for setters to be call later with the converted values
```scala
criteria.project(Components.detachedElementOf("THIRD")).asString()				.into().method(AnyRef)
													   .asInteger()
													   .asDate()
													   .asDateTime()
													   .asEnum(typeof(ENUM))
													   .convertedWith(CONVERTER)
```

'Runners' then wrap the 'Criteria' objects and recall the initializer; finalizer; and projection methods based on the cursor position of the wrapped StaX reader
```scala
val runner : Runner = Runners.runnerFor(crit)
runner.run()
```


### TODO
* Lots!
* Mutable builder for Criteria for easier addition of projections
* Add depth val for fail-fast equals on checking matching locations
* Finish converters
* Tests... always tests!
