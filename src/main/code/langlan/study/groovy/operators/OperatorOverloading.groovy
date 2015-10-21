package langlan.study.groovy.operators

import org.junit.Test;

/**
 * Groovy allows you to overload the various operators so that they can be used with your own classes.
 * <table style="margin-left:5px">
 * <tr><th>Operator</th><th width="95px">Method</th><th width="85px">Operator</th><th>Method</th></tr>
 * <tr><td>+</td>	<td>a.plus(b)</td>	<td>a[b]</td>	<td>a.getAt(b)</td></tr>
 * <tr><td>-</td>	<td>a.minus(b)</td>	<td>a[b] = c</td>	<td>a.putAt(b, c)</td></tr>
 * <tr><td>*</td>	<td>a.multiply(b)</td>	<td>a&lt;&lt;b</td>	<td>a.leftShift(b)</td></tr>
 * <tr><td>/</td>	<td>a.div(b)</td>	<td>a>>b</td>	<td>a.rightShift(b)</td></tr>
 * <tr><td>%</td>	<td>a.mod(b)</td>	<td>a++</td>	<td>a.next()</td></tr>
 * <tr><td>**</td>	<td>a.power(b)</td>	<td>a--</td>	<td>a.previous()</td></tr>
 * <tr><td>|</td>	<td>a.or(b)</td>	<td>+a</td>	<td>a.positive()</td></tr>
 * <tr><td>&</td>	<td>a.and(b)</td>	<td>-a</td>	<td>a.negative()</td></tr>
 * <tr><td>^</td>	<td>a.xor(b)</td>	<td>~a</td>	<td>a.bitwiseNegative()</td></tr>
 * </table>
 */
class OperatorOverloading {
	@Test
	void test() {
		def a = this, b = new Object()

		assert a + b == a.plus(b)
		assert a - b == a.minus(b)
		assert a * b == a.multiply(b)
		assert a / b == a.div(b)
		assert a % b == a.mod(b)

		assert -a == this.negative()
	}

	def plus(o) {
		"${hashCode()} + ${o.hashCode()}"
	}

	def minus(o) {
		"${hashCode()} - ${o.hashCode()}"
	}

	def multiply(o) {
		"${hashCode()} * ${o.hashCode()}"
	}

	def div(o) {
		"${hashCode()} / ${o.hashCode()}"
	}

	def mod(o) {
		"${hashCode()} % ${o.hashCode()}"
	}

	def power(o) {
		"${hashCode()} ** ${o.hashCode()}"
	}

	def or(o) {
		"${hashCode()} | ${o.hashCode()}"
	}

	def and(o) {
		"${hashCode()} & ${o.hashCode()}"
	}

	def xor(o) {
		"${hashCode()} ^ ${o.hashCode()}"
	}

	def getAt(o) {
		"${hashCode()}[ ${o.hashCode()} ]"
	}

	def putAt(o, c) {
		"${hashCode()}[ ${o.hashCode()} ] = ${c.hashCode()}"
	}

	def leftShift(o) {
		"${hashCode()} << ${o.hashCode()}"
	}

	def rightShift(o) {
		"${hashCode()} >> ${o.hashCode()}"
	}

	def negative() {
		"-${this.hashCode()}"
	}
}
