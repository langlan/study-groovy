package langlan.study.groovy.semantics.statements

import org.junit.Test
/**
 * The for loop in Groovy is much simpler and works with any kind of array, collection, Map, etc.
 */
class LoopForIn {

	@Test
	void test() {
		// iterate over a range
		def x = 0
		for (i in 0..9) {
			x += i
		}
		assert x == 45

		// iterate over a list
		x = 0
		for (i in [0, 1, 2, 3, 4]) {
			x += i
		}
		assert x == 10

		// iterate over an array
		def array = (0..4).toArray()
		x = 0
		for (i in array) {
			x += i
		}
		assert x == 10

		// iterate over a map (map.entrySet())
		def map = ['abc': 1, 'def': 2, 'xyz': 3]
		x = 0
		for (e in map) {
			x += e.value
		}
		assert x == 6

		// iterate over values in a map
		x = 0
		for (v in map.values()) {
			x += v
		}
		assert x == 6

		// iterate over the characters in a string
		def text = "abc"
		def list = []
		for (c in text) {
			list.add(c)
		}
		assert list == ["a", "b", "c"]
	}
}
