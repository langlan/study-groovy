package langlan.study.groovy.syntax

import groovy.transform.TypeChecked
import groovy.transform.TypeCheckingMode
import org.junit.Test

@TypeChecked
class StringAndGString {
	/**
	 * When a method (whether implemented in Java or Groovy) expects a {@link String}, but we pass a {@link GString} instance, the toString() method of the {@link GString} is automatically and transparently called.
	 */
	private String takeString(String message) {
		assert message instanceof String
		return message
	}

	@Test
	void testInteoperability() {
		def message = "The message is ${'hello'}"
		assert !(message instanceof String)
		assert message instanceof GString

		def result = takeString(message)
		assert result instanceof String
		assert result == 'The message is hello'
	}

	@Test
	void testHashCode() {
		assert "one: ${1}".hashCode() != "one: 1".hashCode()
	}

	//TODO: GString in dotted-expression : Groovyc: [Static type checking] - No such property: null for class: java.util.LinkedHashMap <groovy.lang.GString, java.lang.String>
	@TypeChecked(TypeCheckingMode.SKIP)
	@Test
	void test_Map_which_keys_are_GString() {
		def key = "a"
		def gkey = "$key"
		def gkey2 = "${key}2"
		// two ways to make keys be GString!
		def map = ["$key": 'key is GString!', (gkey2): 'key is GString too!']

		assert !map.a && !map['a'] && !map.getAt('a') && !map.get('a')
		assert !map[key] && !map.get(key)
		assert !map."$key" && !map["$key"] && !map[gkey]

		// subscript operator(getAt putAt)
		assert !map.getAt(key) && !map.getAt("$key") && !map.getAt(gkey)

		// only get(..) regards the parameter type(GString)
		assert map.get("$key") && map.get(gkey) && map.get(gkey2)
	}

	//TODO: GString in dotted-expression : Groovyc: [Static type checking] - No such property: null for class: **Map**
	@TypeChecked(TypeCheckingMode.SKIP)
	@Test
	void testDottedExpressionWithQuotedString() {
		def map = [a: 'abc', b: 'bcd']
		def _a = 'a'
		assert map.'a' == map.get('a')
		assert map.'''a''' == map.get('a')
		assert map."$_a" == map.get('a')
		assert map."""$_a""" == map.get('a')
		assert map./$_a/ == map.get('a')
		assert map.$/$_a/$ == map.get('a')
	}
}
