package langlan.study.groovy.operators

import groovy.transform.TypeChecked
import org.junit.Test;

/**
 * The call operator <b style="color:blue">()</b> followed an object is used to call a method (of that object) that named <b>call</b> implicitly.<br/>
 * For any object which defines a call method, you can omit the <b>.call</b> part and use the call operator instead.<br/>
 * The signature of call method has no any limit.<ul>
 * 	<li>Can <span style="color:blue">void</span> or return any type</li>
 * 	<li>Can has any number of arguments</li>
 * </ul>
 * Differents between obj<b style="color:blue">(...)</b> and obj<b style="color:blue">.call(...)</b>
 * <ul>
 * 	<li>The operator <b style="color:blue">()</b> can not be used on keywords like <b style="color:blue">this</b>.</li>
 * 	<li>The operator <b style="color:blue">()</b> always return a value, will be <b style="color:blue">null</b> if correspond call method is <span style="color:blue">void</span>.</li>
 * </ul>
 *
 */
@TypeChecked
class CallOperator {
	def call() {
		'parameterless'
	}

	def call(i) {
		"single_parameter:$i"
	}

	def call(i, j) {
		"couple_parameters:$i,$j"
	}

	void call(i, j, k) {
		// do nothing
	}

	@Test
	void test0_CallOperator() {
		def a = this
		assert a() == 'parameterless' && a() == a.call()
		assert a(5) == "single_parameter:5" && a(5) == a.call(5)
		assert a(5, 6) == "couple_parameters:5,6" && a(5, 6) == a.call(5, 6)

		// void method returns null
		assert a(5, 6, 7) == null
		assert a.call(5, 6, 7) == null
		// the same as normal-method-call
		assert this.call(5, 6, 7) == null
	}

	@Test
	void test1_MethodReferenceAndClosure() {
		def m = 'abcdef'.&substring
		assert m(1) == m.call(1)
		assert m(1, 3) == m.call(1, 3)

		def c = {i, j, k -> "clousre args:$i,$j,$k"}
		assert c(1, 2, 3) == c.call(1, 2, 3)
	}
}
