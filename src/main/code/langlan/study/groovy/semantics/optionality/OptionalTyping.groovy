package langlan.study.groovy.semantics.optionality

import groovy.transform.TypeChecked
import groovy.transform.TypeCheckingMode
import org.junit.Test

/**
 * <b>NOTE: </b>Omitting types is in general considered a bad practice in method parameters or method return types for public APIs. <p>
 *     While using <b>def</b> in a local variable is not really a problem because the visibility of the variable is limited to the method itself,
 *     while set on a method parameter, <b>def</b> will be converted to <b>Object</b> in the method signature, making it difficult for users to know which is the expected type of the arguments.
 *     This means that you should limit this to cases where you are explicitly relying on duck typing.
 * <p/>
 */
@TypeChecked
class OptionalTyping {
	@Test
	void test() {
		assert 3 == concat(1, 2)
		assert '12' == concat('1', '2')
	}

	@TypeChecked(TypeCheckingMode.SKIP)
	def concat(a, b) {
		a + b
	}
}
