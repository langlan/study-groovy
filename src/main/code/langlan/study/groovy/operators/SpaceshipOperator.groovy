package langlan.study.groovy.operators

import groovy.transform.TypeChecked
import org.codehaus.groovy.runtime.ScriptBytecodeAdapter
import org.junit.Test

/**
 * The spaceship operator <b>(<=>)</b> delegates to the <b style="color:blue">compareTo(Object)</b> method.<br/>
 * * But for now, It seems that:
 * <ul>
 * 	<li>operator always return a value that is one of <b>[-1,0,1]</b>, never others.</li>
 * 	<li>compareTo(Object) always return a value that is one kind of <b>[-n,0,n]</b>, n could be any non-zero.</li>
 * </ul>
 * @see ScriptBytecodeAdapter#compareTo(Object, Object)
 */
@TypeChecked
class SpaceshipOperator {
	@Test
	void testSpaceshipOperator() {
		assert (1 <=> 1) == 0
		assert (1 <=> 22) == 1.compareTo(22)
		assert (33 <=> 1) == 33.compareTo(1)
		char a = 'a', z = 'z'
		assert (a <=> z) == a.compareTo(z)
		assert ('aa' <=> 'zz') == -1
		assert 'aa'.compareTo('zz') == -25

		// NOTE: spaceship not-always-equals compareTo()
		assert 'aa' <=> 'zz' != 'aa'.compareTo('zz')

		// NOTE: numbers' spaceship or compareTo() not (always?) equals their delta.
		assert a - z != a.compareTo(z)
		assert 1 - 10 != 1.compareTo(10)

		def ca = 'a' as char, cz = 'z' as char
		assert ca.compareTo(cz) < 0
	}
}
