package langlan.study.groovy.meta

import org.junit.Test

/**
 * Created by langlan on 2015/6/16.
 */
class MetaPropertyMissing {
	Map storage = [:]

	def propertyMissing(String name, value) {
		storage[name] = value
	}

	def propertyMissing(String name) {
		storage[name]
	}

	@Test
	void test() {
		this.abc = 'xyz'
		assert storage.abc == this.abc
	}
}
