package langlan.study.groovy.operators

import org.junit.Test;

/**
 * In Groovy, using <b>(==)</b> to test equality is different from using the same operator in Java. In Groovy, it is calling equals(). <br/>
 * If you want to compare reference equality, you should use method <b>is(other)</b>
 * <table style="border:1px solid black;">
 *  <tr> <td width="100">Groovy</td> <td>Java</td> </tr>
 * 	<tr> <td><b>a == b</b></td>  <td><b>a.equals(b)</b></td></tr>
 * 	<tr> <td><b>a.is(b)</b></td> <td> <b>a == b</b></td></tr>
 * </table>
 */
class IdentityOperator {
	@Test
	void test() {
		def a = ['abc']
		def b = ['abc']
		assert a == b
		assert !a.is(b)
		def c = a
		assert c.is(a)
	}
}