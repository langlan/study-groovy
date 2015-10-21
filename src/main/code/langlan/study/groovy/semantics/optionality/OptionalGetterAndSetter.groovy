package langlan.study.groovy.semantics.optionality

import groovy.transform.TypeChecked
import org.junit.Test

/**
 * In Groovy, a getters and setters form what we call a "property", and offers a shortcut notation for accessing and setting such properties.<p>
 * When writing beans in Groovy, often called POGOs (Plain Old Groovy Objects), you don’t have to create the field and getter / setter yourself, but let the Groovy compiler do it for you.<p>
 * A free standing 'field' without modifier visibility actually makes the Groovy compiler to generate a <b>private field</b> and a <b>getter</b> and <b>setter</b> for you.<p>
 * <b>NOTE:</b><br/>
 * When using such POGOs from Java, the getter and setter are indeed there, and can be used as usual, of course.<br/>
 * Although the compiler creates the usual getter/setter logic, if you wish to do anything additional or different in those getters/setters, you’re free to still provide them, and the compiler will use your logic, instead of the default generated one.
 */
@TypeChecked
class OptionalGetterAndSetter {
	/** a POGO example */
	static class Person {
		/** name of person */
		String name
	}

	@Test
	void test() {
		def p = new Person(name: "TEST")
		assert p.name == p.getName()
		p.name += ' SUFFIX'
	}
}
