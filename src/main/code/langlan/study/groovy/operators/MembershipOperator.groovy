package langlan.study.groovy.operators

import langlan.study.groovy.semantics.statements.SwitchAndCase
import org.codehaus.groovy.runtime.DefaultGroovyMethods
import org.junit.Test;

/**
 * The membership operator <b>(in)</b> is equivalent to calling the <b>isCase(c)</b> method. <br/>
 * In the context of a List, it is equivalent to calling <b>contains(e)</b>.
 * @see SwitchAndCase
 * @see DefaultGroovyMethods#isCase(Collection, Object)
 * @see DefaultGroovyMethods#isCase(Object, Object)
 */
class MembershipOperator {
	static class OnlyCase {
		def onlyCase = 'abc'

		boolean isCase(obj) {
			return obj == this.onlyCase
		}
	}

	@Test
	void testInOperator() {
		def onlyCaseObj = new OnlyCase()
		def list = ['Grace', 'Rob', 'Emmy']
		assert 'Emmy' in list == list.isCase('Emmy')
		assert list.isCase('Emmy') == list.contains('Emmy')
		assert onlyCaseObj.onlyCase in onlyCaseObj
		assert 'abc' in 'abc'
		assert 'regexpabc' in ~/regex.*/

		for (val in ['a', onlyCaseObj.onlyCase, 1]) {
			switch (val) {
				case onlyCaseObj:
					assert val == onlyCaseObj.onlyCase
					break
				default:
					assert val != onlyCaseObj.onlyCase
					println val

			}
		}

	}
}
