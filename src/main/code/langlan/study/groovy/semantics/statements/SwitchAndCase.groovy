package langlan.study.groovy.semantics.statements

import langlan.study.groovy.operators.MembershipOperator
import langlan.study.groovy.operators.MembershipOperator.OnlyCase
import org.codehaus.groovy.runtime.DefaultGroovyMethods
import org.junit.Test

import java.util.regex.Pattern

/**
 * <p style="color:gray;">
 * The switch statement in Groovy is backwards compatible with Java code; 
 * so you can fall through cases sharing the same code for multiple matches. </p>
 * <p>
 * One difference though is that the Groovy switch statement can handle 
 * <b>any kind of switch value</b> and 
 * <b>different kinds of matching</b> can be performed.</p>
 * Switch supports the following kinds of comparisons/matching:
 * <ul>
 *  <li>Class case values matches if the switch value is an instance of the class</li>
 *  <li>Regular expression case values match if the toString() representation of the switch value matches the regex</li>
 *  <li>Collection case values match if the switch value is contained in the collection. This also includes ranges (since they are Lists) </li>
 *  <li>Closure case values match if the calling the closure returns a result which is true according to the Groovy truth</li>
 *  <li>If none of the above are used then the case value matches if the case value equals the switch value</li>
 * </ul>
 * <p style="color:blue;">
 *  default must go at the end of the switch/case. While in Java the default can be placed anywhere in the switch/case, 
 *  the default in Groovy is used more as an else than assigning a default case.</p>
 *  <p style="color:blue;">
 *  when using a closure case value, the default it parameter is actually the switch value (in our example, variable x)</li>
 *  </p>
 * @see DefaultGroovyMethods#isCase(Number, Number)
 * @see DefaultGroovyMethods#isCase(String, Object)
 * @see DefaultGroovyMethods#isCase(CharSequence, Object)
 * @see DefaultGroovyMethods#isCase(GString, Object)
 * @see DefaultGroovyMethods#isCase(Pattern, Object)
 * @see DefaultGroovyMethods#isCase(Object, Object)
 * @see DefaultGroovyMethods#isCase(Collection, Object)
 * @see DefaultGroovyMethods#isCase(Map, Object)
 * @see MembershipOperator
 */
class SwitchAndCase {
	OnlyCase _in = new OnlyCase(onlyCase: this)

	@Test
	void testSwitchCase() {
		assert evalBySwitch(1.23) == "number"
		assert evalBySwitch(4) == "number : 4,5,6 or string : 'inList'"
		assert evalBySwitch('inList') == "number : 4,5,6 or string : 'inList'"
		assert evalBySwitch(23) == "range: [20,30]"
		assert evalBySwitch(100) == "integer"
		assert evalBySwitch(100.0) == "number"
		assert evalBySwitch('regexabc') == "match 'regex.*'"
		assert evalBySwitch([1]) == 'non-empty collection'
		assert evalBySwitch([1]) == 'non-empty collection'
		assert evalBySwitch(this) == 'in operator'
		assert evalBySwitch(new Object()) == 'default'
	}

	def evalBySwitch(x) {
		def result
		switch (x) {
			case [4, 5, 6, 'inList']:
				result = "number : 4,5,6 or string : 'inList'"
				break

			case 12..30:
				result = "range: [20,30]"
				break

			case Integer:
				result = "integer"
				break

			case Number:
				result = "number"
				break

			case ~/regex.*/: // toString() representation of x matches the pattern?
				result = "match 'regex.*'"
				break

			case {it instanceof Collection && !it.isEmpty()}:
				result = "non-empty collection"
				break

			case _in :
				result = 'in operator'
				break

			default:
				result = "default"
		}
		result
	}
}
