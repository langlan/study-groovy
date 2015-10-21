package langlan.study.groovy.syntax

import langlan.study.groovy.operators.SubscriptOperator
import langlan.study.groovy.operators.CoercionOperator
import langlan.study.groovy.operators.OperatorOverloading
import org.junit.Test

class ListsAndArrays extends GroovyTestCase {

	/**
	 * Groovy uses a <b>comma-separated</b> list of values, surrounded by <b>square brackets</b>, to denote lists. <br/>
	 * Groovy lists are plain JDK java.util.List, as Groovy doesn’t define its own collection classes.
	 */
	@Test
	void test0_Definitions() {
		def numbers = [1, 2, 3]

		assert numbers instanceof List
		assert numbers.size() == 3

		// In the above example, we used a homogeneous list, but you can also create lists containing values of heterogeneous types:
		def heterogeneous = [1, "a", true]
	}

	/**
	 * <ul>
	 * 	<li>Default type is ArrayList</li>
	 *  <li>To use concrete type of List
	 *    <ul>
	 *      <li>using <b><i>type coercion</i></b> with the <b>as</b> operator</li>
	 *      <li>using <b><i>explicit type declaration</i></b> for variables</li>
	 *    </ul>
	 *  </li>
	 * </ul>
	 * @see CoercionOperator
	 */
	@Test
	void test10_ListTypes() {
		// default : ArrayList
		def arrayList = [1, 2, 3]
		assert arrayList instanceof java.util.ArrayList

		// coercion
		def linkedList = [2, 3, 4] as LinkedList
		assert linkedList instanceof java.util.LinkedList

		// explicit type declaration
		LinkedList otherLinked = [3, 4, 5]
		assert otherLinked instanceof java.util.LinkedList

		/** casting (not very robust, but will work for this test-example) see explanation in {@link CoercionOperator} */
		arrayList = (LinkedList) arrayList
		assert arrayList instanceof LinkedList
	}

	/**
	 * Groovy reuses the list notation for arrays, but like other concrete List types,
	 * to make such literals arrays, you need to explicitely define the type of the array through
	 * coercion or type declaration.
	 */
	@Test
	void test11_ArrayTypes() {
		String[] arrStr = ['Ananas', 'Banana', 'Kiwi']

		assert arrStr instanceof String[]
		assert !(arrStr instanceof List)

		def numArr = [1, 2, 3] as int[]

		assert numArr instanceof int[]
		assert numArr.size() == 3
	}

	@Test
	void test20_Operators_Subscript() {
		new SubscriptOperator().test0_ListAndArry()
	}

	/**
	 * Leftshift Operator <b>(<<)</b> support List but not array. 
	 * cause array type has no method <b style="color:blue;">leftShift(Object)</b>
	 * @see OperatorOverloading
	 */
	@Test
	void test21_Operators_Leftshift() {
		def letters = ['a']
		letters << 'b'
		assert letters[0] == 'a'
		assert letters[1] == 'b'

		shouldFail(MissingMethodException) {
			def nums = [] as int[]
			nums << 1
		}
	}


}
