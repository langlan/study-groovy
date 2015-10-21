package langlan.study.groovy.semantics.type.coercion

import org.junit.Test

/**
 * Usually using a single closure to implement an interface or a class with multiple methods is not the way to go.
 * As an alternative, Groovy allows you to coerce a map into an interface or a class.
 * <ul>
 *     <li>keys of the map are interpreted as method names</li>
 *     <li>values are the method implementation.</li>
 * </ul>
 */
class Map2Type extends GroovyTestCase {
	interface X {
		Object f()

		void g(int n)
	}

	@Test
	void test() {
		def map
		map = [
			i      : 10,
			hasNext: { map.i > 0 },
			next   : { map.i-- },
		]
		def iter = map as Iterator
		assert 10 == iter.size()
	}

	/**
	 * if a method is called that doesn’t exist in the map or interface a {@link MissingMethodException} or an {@link UnsupportedOperationException} is thrown
	 */
	@Test
	void test_throw() {
		def x = [f: { 1 }, z: {}] as X
		assert 1 == x.f() // method exists

		// no signature-of-method exists in interface
		shouldFail(MissingMethodException) { x.g() }
		// signature-of-method exists in interface, but not implemented
		shouldFail(UnsupportedOperationException) { x.g(5) }
		// no signature-of-method exists in interface, even implemented (in java will be a compile-error)
		shouldFail(MissingMethodException) { x.z() }
	}
}
