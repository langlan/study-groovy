package langlan.study.groovy.operators

import org.junit.Test

/**
 * @see ObjectOperators
 * @see ObjectOperators#testSafeNavigation()
 */
class ConditionOperators {
	@Test
	void testTernaryOperator() {
		def str = 'abc'
		assert str[0] == 'a'
		assert !(str[1] == 'a')
	}

	@Test
	void testElvisOperator() {
		def user = [name: 'Bob']
		def displayName = user.name ? user.name : 'Anonymous'
		def displayName2 = user.name ?: 'Anonymous'
		assert displayName == displayName2
	}
}
