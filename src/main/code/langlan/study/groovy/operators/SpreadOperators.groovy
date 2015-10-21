package langlan.study.groovy.operators

import org.junit.Test

import langlan.study.groovy.User

class SpreadOperators {
	/**
	 * The Spread Operator <b>(*.)</b> is used to invoke an action on all items of an <b>aggregate object</b>. 
	 * It is equivalent to calling the action on each item and collecting the result into a list
	 * <ul>
	 * 	<li>The spread operator is <b>null-safe</b>, meaning that if an element of the collection is null, it will return null instead of throwing a NullPointerException:</li>
	 *  <li>The spread operator can be used on any class which implements the <b>Iterable</b> interface. Or on <b>array</b></li>
	 *  <li>The spread operator just like dot operator, it can navigate to property or method (on elements).</li>
	 *  <li>Navigate to <b>void</b> method, return value will be treated as <b>null</b></li>
	 * </ul>
	 */
	@Test
	void test0_ElementsSpreadOperator() {
		//navigate method
		def lst = ['a', 'ab', 'abc']
		assert [1, 2, 3]==lst*.length()

		//navigate property
		lst = [[name:'a'], [name:'b']]
		assert ['a', 'b']== lst*.name

		//array
		def ary = ['', 'a'] as String[]
		assert ary instanceof String[]
		assert [0, 1]== ary*.length()

		//map
		assert ['a', 'b']== [a:'aa',b:'bb']*.getKey()

		//null-safe
		assert [1, null, 3]== ['a', null, 'abc']*.length()
		assert ['a', null, 'b']== [[name:'a'], [:], [name:'b']]*.name
		assert [null, null]*.doSomeThing() == [null, null]

		//top-null-safe
		assert null*.toString() == null

		//void
		assert [null, null]== [[], []]*.clear()
	}

	/**
	 * Like <b>(.)</b> vs <b>(.@)</b>;<br/> 
	 * Against <b>(*.)</b>, Operator <b>(*.@)</b> can directly access field (on elements).
	 */
	@Test
	void test0_ElementsSpreadOperator_DirectField() {
		//navigate property or direct-field-acess
		def users = [new User("Bob"), new User("Jim")]
		assert ['Name: Bob', 'Name: Jim']== users*.name
		assert ['Bob', 'Jim']== users*.@name
	}
	
	/**
	 * <b>(*)</b>
	 */
	@Test
	void test1_ElementsSpreadToMethodParameters() {
		def str = 'abcdefg'
		def position = [3,6]
		assert 'def' == str.substring(*position)
		
		position = [3]
		assert 'defg' == str.substring(*position)
		assert 'de' == str.substring(*position,5)
	}
	
	/**
	 * <b>(*)</b>
	 */
	@Test
	void test1_ElementsSpreadToListFragment() {
		def items = [4,5]
		def list = [1,2,3,*items,6]
		assert list == [1,2,3,4,5,6]
		
		items = items as int[]
		assert items instanceof int[]
		list = [1,2,3,*items,6]
		assert list == [1,2,3,4,5,6]
	}
	
	/**
	 * <b>(*:)</b>
	 */
	@Test
	void test2_1_MapEntriesSpreadToMap() {
		def m1 = [c:3, d:4]
		def map = [a:1, b:2, *:m1]
		assert map == [a:1, b:2, c:3, d:4]
		
		//The position of the spread map operator is relevant
		map = [a:1, b:2, *:m1, d: 8]
		assert map == [a:1, b:2, c:3, d:8]
	}
}
