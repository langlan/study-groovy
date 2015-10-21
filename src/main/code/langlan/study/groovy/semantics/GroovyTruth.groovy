package langlan.study.groovy.semantics

import org.codehaus.groovy.runtime.DefaultGroovyMethods
import org.junit.Test

/**
 *
 * <ul>
 * 	<li>Java standard: boolean, Boolean</li>
 * 	<li>Collection: !isEmpty() / size()>0 </li>
 * 	<li>Map: !isEmpty() / size()>0 </li>
 * 	<li>Iterator: hasNext() </li> 
 * 	<li>Enumeration: hasMoreElements() </li> 
 * 	<li>Matcher: find() </li> 
 * 	<li>Strings: !isEmpty() (GStrings and CharSequences are coerced to string first.) </li>
 * 	<li>Numbers,Character(s): non-zero </li>
 * 	<li>Object-Reference: non-null </li>
 * 	<li>Custom-Class: implements method <b>boolean asBoolean()</b></li>
 * </ul>
 * @see DefaultGroovyMethods#asBoolean(Boolean)
 * @see DefaultGroovyMethods#asBoolean(Object)
 * @see DefaultGroovyMethods#asBoolean(Object[])
 * @see DefaultGroovyMethods#asBoolean(CharSequence)
 * @see DefaultGroovyMethods#asBoolean(Character) ...
 *
 */
class GroovyTruth {
	@Test
	void testTruth4DifferentTypes() {
		//Non-empty Strings, GStrings and CharSequences are coerced to true.

		// Strings
		assert 'This is true' && !''
		//GStrings (!!!Official Docs says Always true)
		def s1 = '', s2 = 'a'
		assert !"$s1"
		assert "$s2"

		//Non-null object references are coerced to true.
		assert new Object()
		assert !null

		//Non-zero numbers are coerced to true.
		assert 1
		assert !0 && !0.0

		//Collections
		assert [1]
		assert ![]

		//Matcher
		def m = ('string' =~ /abc/)
		assert !m && m != null
		assert !this
	}

	boolean asBoolean() {
		false
	}
}
