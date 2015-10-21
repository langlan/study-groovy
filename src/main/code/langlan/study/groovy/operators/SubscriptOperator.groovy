package langlan.study.groovy.operators

import langlan.study.groovy.syntax.ListsAndArrays
import org.junit.Test

/**
 * The subscript operator is a short hand notation for <b>getAt(obj)</b> or <b>putAt(obj)</b>, 
 * depending on whether you find it on the left hand side or the right hand side of an assignment
 * @see ListsAndArrays#test20_Operators_Subscript()
 */
class SubscriptOperator {
	/**
	 * List and array support three forms.<br/>
	 * Suppose p(i) is a function to reindex the parameter i and i could be positive or negative or zero.<br/>
	 * <span style="color:blue;">p(i) := (i>=0 ? i : length+i)</span>
	 * <ul>
	 * <li>[i]</li>
	 * <li>[i..j]</li>
	 * <li>[i,j] or [i,j,k] or [i,j,k,a..b,c..d,x] or ...</li>
	 * </ul>
	 */
	@Test
	void test0_ListAndArry() {
		def letters = ['a', 'b', 'c', 'd']

		assert letters[0] == 'a'
		assert letters[1] == 'b'

		assert letters[-2] == 'c'
		assert letters[-1] == 'd'

		letters[2] = 'C'
		assert letters[2] == 'C'

		// range
		assert letters[2..3] == ['C', 'd']
		assert letters[0..-1] == letters
		// range with reverse
		assert letters[-1..0] == ['d', 'C', 'b', 'a']
		// collection of indices
		assert letters[1, 3] == ['b', 'd']
		assert letters[0, -1] == ['a', 'd']
		// collection of indices(with range)
		assert letters[0, -2, 1, -1, 1..-1] == ['a', 'C', 'b', 'd', 'b', 'C', 'd']

		letters = letters as char[]
		assert letters instanceof char[]
		assert letters[0, -1] == ['a', 'd']
		assert letters[0, -2, 1, -1] == ['a', 'C', 'b', 'd']
	}

	/**
	 * map support [,,,,] and [..] ,but will treat list/range as a single key.
	 */
	@Test
	void testMap() {
		def map = [a: 'aa', b: 'bb', c: 'cc']

		// normal use
		assert 'aa' == map['a']
		assert 'bb' == map['b']

		// theoretical tests : range,list will be treated as a single key against Map, not like Array or List.
		assert null == map['a', 'b']
		assert null == map['a'..'c']
		map['a', 'b'] = "keyed by array"
		map['a'..'c'] = "keyed by range"

		assert "keyed by array" == map['a', 'b']
		assert "keyed by range" == map['a'..'c']

		map.put('a'..'c',"keyed by range & put")
		assert "keyed by range & put" == map['a'..'c']
	}

	/** By overloading subscript operator */
	@Test
	void testCustom() {
		assert this[55] == 'a55'
		assert this[66] == 'a66'
		assert this[55, 66, 30] == "a[55, 66, 30]"
		assert this[55..58] == "a[55, 56, 57, 58]"
	}

	def getAt(i) {
		return "a$i"
	}
}
