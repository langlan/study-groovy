package langlan.study.groovy.operators

import org.junit.Test

import langlan.study.groovy.User

/**
 * <ul>
 *     <li>obj.prop</li>
 *     <li>obj.?prop</li>
 *     <li>obj.@prop</li>
 *     <li>obj.method()</li>
 *     <li>obj.&method</li>
 * </ul>
 */
class ObjectOperators {

	/**
	 * test if object is null before access it's member.
	 */
	@Test
	void testSafeNavigation() {
		def user
		assert user == null

		def name = user?.getClass()
		if (user == null) {
			assert name == null
		}
	}

	/**
	 * <b>(.@)</b>
	 */
	@Test
	void testDirectFiledAccess() {
		def user = new User("Bob")
		assert user.name == 'Name: Bob'
		assert user.@name == 'Bob'
	}

	/**
	 * @See Closure , interface implemetation?
	 */
	@Test
	void testMethodReference() {
		def str = 'example of method reference'
		def func = str.&toUpperCase
		assert func() == str.toUpperCase()

		func = String.&valueOf
		assert '123' == func(123)
	}


}