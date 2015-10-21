package langlan.study.groovy.semantics.type.check

import groovy.transform.TypeChecked
import org.junit.Test

/**
 * If we use <b>instanceof expression</b>, the following selected code block will no need do casting like Java in witch we should.<br/>
 * Even we are using @TypeChecked
 */
@TypeChecked
class TypeInferenceByInstanceof {
	interface Greeter {
		String greeting()
	}

	@Test
	void testTypeInferenceByInstanceof() {
		Greeter g = { 'hello' }
		assert 'hello' == delegateGreeting(g)
	}

	String delegateGreeting(def o) {
		if (o instanceof Greeter) {
			return o.greeting()
		}
		null
	}

}
