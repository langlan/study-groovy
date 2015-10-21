package langlan.study.groovy.operators

import org.junit.Test

/**
 * The diamond operator <b>(&lt;&gt;)</b> is a syntactic sugar only operator added to support compatibility with the operator of
 * the same name in <b>Java 7</b>. It is used to indicate that generic types should be inferred from the declaration.<br/>
 * In dynamic Groovy, this is totally unused. 
 * In statically type checked Groovy, it is also optional since the Groovy type checker performs 
 * type inference whether this operator is present or not.
 */
class DiamondOperator {
	@Test
	void testDiamondOperator() {
		List<String> strings = new LinkedList<>()
		//no type check either compile time or runtime
		strings.add(new Object())
		strings.add('string')
		assert !(strings[0] instanceof String)
		assert (strings[1] instanceof String)
	}
}
