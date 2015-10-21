package langlan.study.groovy.semantics.optionality

import groovy.transform.TypeChecked
import org.junit.Test

/**
 * In Groovy, the last expression evaluated in the body of a <i>method</i> or a <i>closure</i> is returned.<br/>
 * This means that the <b>return</b> keyword is optional..
 */
@TypeChecked
class OptionalKeywordReturn {

	@Test
	void testOptional() {
		assert 3 == add(1, 2)
	}

	/** a example method do addition that omit return-keyword */
	int add(int a, int b) {
		return a + b
	}
}
