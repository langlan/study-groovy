package langlan.study.groovy.semantics.statements

import org.junit.Test;

/**
 * <ul>
 * 	<li>simple</li>
 * 	<li>multiple assignment <br/>
 *  Groovy supports multiple assignment, i.e. where multiple variables can be assigned at once.
 * 	 <ul>
 *     <li>variables quoted using parentheses</li>
 *     <li>values could be instance of array or List</li>
 *     <li>values could be returned by a method</li>
 *     <li>values would be consumed like subscript operator by invoking <b>getAt(index)</b>.
 *     <span style="color:green;">So it could be any type that has a method getAt(i)</span></li>
 *     <li>in definition context, you can supply explicity types to qutoted variables.</li>
 *   </ul>
 *  </li>
 * </ul> 
 */
class VariableAssignment {
	def x, y, z

	@Test
	void test00_WithDefinition() {
		def x = 1
		String s = 'hello'

		assert x == 1
		assert s == 'hello'
	}

	@Test
	void test01_AfterDefinition() {
		x = 1
		assert x == 1

		x = new java.util.Date()
		assert x instanceof Date

		x = -3.1499392
		assert x == -3.1499392

		x = false
		assert !x

		x = "Hi"
		assert x == 'Hi'
	}

	@Test
	void test10_Multiple_WithDefinition() {
		def (a, b, c) = [10, 20, 'foo']
		assert a == 10 && b == 20 && c == 'foo'

		//types
		def (int i, String j) = [10, 'foo']
		assert i == 10 && j == 'foo'
	}

	@Test
	void test11_Multiple_AfterDefinition() {
		def nums = [1, 3, 5]
		def a, b, c

		//assignment after definition
		(a, b, c) = nums
		assert a == 1 && b == 3 && c == 5

		//array values. and method returned values.
		(x, y, z) = 'hello world haha'.split(' ')
		assert x == 'hello' && y == 'world' && z == 'haha'
	}

	/**
	 * Overflow of values is no harm to multiple assignment. 
	 * Overflowed values will just be ignored
	 */
	@Test
	void test12_Multiple_Overflow() {
		def (a, b) = [1, 3, 5]
		assert a == 1 && b == 3
	}

	/**
	 * Be carefull about Underflow of values. Excess are filled with null’s.
	 */
	@Test
	void test13_Multiple_Underflow() {
		def (a, b, c) = [1, 3, 5]
		assert a == 1 && b == 3 && c == 5

		(a, b, c) = [1, 3]
		assert a == 1 && b == 3 && c == null
	}

	/**
	 * Like subscript operator, mutiple assignment still use subscript operator methods: getAt(obj). <br/>
	 * We can combine multiple assignments and the subscript operator methods to implement <b>object destructuring</b>.
	 */
	@Test
	void test21_Multiple_ObjectDestructuring() {
		def (a, b, c) = this
		assert a == this[0] && b == this[1] && c == this[2]
		assert a == this.getAt(0) && b == this.getAt(1) && c == this.getAt(2)
	}

	def getAt(o) {
		return "^${o}^"
	}
}
