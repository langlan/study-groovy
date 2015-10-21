package langlan.study.groovy.operators

import org.junit.Test

/**
 * Groovy supports the concept of ranges and provides a notation <b>(..)</b> or <b>(..<)</b> to create ranges of objects
 * <ul>
 * 	<li>Result in an instance of {@link groovy.lang.Range} </li>
 * 	<li>{@link groovy.lang.Range} extended {@link java.util.List} </li>
 * 	<li>You can create a range from any?? {@link Comparable} object </li>
 * </ul>
 */
class RangeOperator {
	@Test
	void test0() {
		def intRange = 3..8
		assert intRange instanceof List
		assert intRange instanceof Range
		assert intRange == [3, 4, 5, 6, 7, 8]

		assert intRange[0..-2] == 3..<8

		assert 'a'..'c' == ['a', 'b', 'c']
	}
}
