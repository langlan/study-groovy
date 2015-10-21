package langlan.study.groovy.closure

import org.junit.Test;

/**
 * Fixing Closure Arguments to Constant Values Via Currying <p>
 * You can fix the values for one or more arguments to a closure instance using the curry() method from the Closuretype. 
 * In fact, this action is often referred to as currying in functional programming circles, 
 * and the result is generally referred to as a Curried Closure. 
 * Curried closures are very useful for creating generic closure definitions, 
 * and then creating several curried versions of the original with differing parameters bound to them.<br/>
 * When the curry() method is called on a closure instance with one or more arguments, a copy of the closure is first made. 
 * The incoming arguments are then bound permanently to the new closure instance so that the parameters 1..N to the curry() 
 * call are bound to the 1..N parameters of the closure. The new curried closure is then returned the caller.<br/>
 * Callers to the new instance will have their invocation parameters bound to the new closure in the 
 * N+1 parameter position of the original closure.
 * </p>
 */
class ClosureCurrying extends GroovyTestCase{
	@Test
	void test() {
		def c = { arg1, arg2-> "${arg1} ${arg2}" }
		def d = c.curry("hello")
		def e = d.curry('world')
		def f = c.curry('hello','world')

		assert 'hello world' == c('hello','world')
		shouldFail(MissingMethodException){ d('hello','world') }
		assert 'hello world' == d("world")
		assert 'hello currying' == d("currying")
		assert e()==f() && e()=='hello world'
	}
}
