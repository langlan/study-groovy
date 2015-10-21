package langlan.study.groovy.semantics.type.coercion

import groovy.transform.TypeChecked
import org.junit.Test

/**
 * In addition to SAM types, a closure can be coerced to any type and in particular interfaces.<br/>
 * <b>Note:</b> Except for unit-tests, Usually using a single closure to implement an interface or a class with multiple methods is not the way to go. see {@link Map2Type}
 */
@TypeChecked
class Closure2ArbitraryType {
	ThreadLocal<Integer> count = new ThreadLocal<>()
	interface FooBar {
		int foo()

		void bar()
	}

	static class FooBarImpl {
		int foo() {
			1
		}

		void bar() {
			println 'bar'
		}
	}

	/**
	 * all methods are implemented using the closure
	 */
	@Test
	public void test0_interface() {
		count.set(0);
		def impl = {count.set(count.get() + 1); 123} as FooBar
		impl.bar() // count++
		assert 123 == impl.foo() // count++
		assert 2 == count.get()
	}

	@Test
	public void test1_class() {
		count.set(0);
		def impl = {count.set(count.get() + 1); 123} as FooBarImpl
		impl.bar() // count++
		assert 123 == impl.foo() // count++
		assert 2 == count.get()
	}

}
