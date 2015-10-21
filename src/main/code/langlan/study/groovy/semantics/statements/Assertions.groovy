package langlan.study.groovy.semantics.statements

import org.junit.Test

/**
 * Unlike Java with which Groovy shares the assert keyword, the latter in Groovy behaves very differently. <br/>
 * First of all, an assertion in Groovy is always executed, independently of the -ea flag of the JVM.
 * It makes this a first class choice for unit tests. The notion of "power asserts" is directly related to how the Groovy assert behaves.
 */
class Assertions {
	@Test
	void test() {
		assert true
		assert true: 'Assertion always success, so this message never shows.'
	}

	/**
	 * The result of the assertion is very different from what you would get in Java. <br/>
	 * If the assertion is true, then nothing happens. If the assertion is false, then it provides a visual representation of the value of each sub-expressions of the expression being asserted. <br/>
	 * Uncommend the Test annotaion, and see what happens.
	 */
	//@Test
	void assert_fail() {
		assert 1 + 1 == 3
	}
}
