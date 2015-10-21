package langlan.study.groovy.semantics.optionality

import groovy.transform.TypeChecked
import org.junit.Test

/**
 * In Groovy, semicolon of the last statement of one code-line can be omitted<ul>
 *     <li>the line contains only a single statement</li>
 *     <li>the line contains multiple statements that are separated by semicolon(s)</li>
 * </ul>
 */
@TypeChecked
class OptionalSemicolons {
	@Test
	void testOptional() {
		/** java syntax */
		assert true;
		boolean b = true; assert b;

		/** groovy support **/
		// single statement
		assert true
		// multiple statements
		boolean bb = true; assert bb
	}
}
