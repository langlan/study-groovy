package langlan.study.groovy.semantics.optionality

import groovy.transform.TypeChecked
import org.junit.Test

/**
 * Method calls can omit the parentheses if <ul>
 *     <li>there is at least one parameter</li>
 *     <li>there is no ambiguity</li>
 * </ul>
 */
@TypeChecked
class OptionalParentheses {
	@Test
	void testOptional() {
		def maximum = Math.max 5, 10
		assert maximum == Math.max(5, 10)
		// ambiguity : assert maximum == Math.max 5, 10
	}
}
