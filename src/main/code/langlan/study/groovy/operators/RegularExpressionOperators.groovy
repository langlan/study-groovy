package langlan.study.groovy.operators

import groovy.transform.TypeChecked

import java.util.regex.Matcher
import java.util.regex.Pattern

import org.junit.Test

/**
 * <ul>
 *     <li>~ string : Pattern Operator : return a {@link java.util.regex.Pattern}</li>
 *     <li>~= string : Find Operator : return a {@link java.util.regex.Matcher}</li>
 *     <li>~== string : Match Operator : return a {@link Boolean}, strict matches.</li>
 * </ul>
 */
@TypeChecked
class RegularExpressionOperators {
	/**
	 * The pattern operator <b>(~)</b> provides a simple way to create a {@link java.util.regex.Pattern} instance.<br/>
	 * <b>NOTE:</b> /literals/ is Gstring syntax.
	 */
	@Test
	void test0_PatternOperator() {
		def str2 = 'foo', str3 = /foo/
		def p = ~/foo/, p2 = ~str2, p3 = ~str3
		assert p instanceof Pattern
		assert p2 instanceof Pattern
		assert p3 instanceof Pattern

		assert str3 instanceof String

		assert p2.pattern() == p.pattern()
	}

	/**
	 * Alternatively to building a pattern, you can directly use the find operator <b>(=~)</b> to build a 
	 * {@link java.util.regex.Matcher} instance. <br/>
	 * Since a Matcher coerces to a boolean by calling its find method, 
	 * the =~ operator is consistent with the simple use of <b>Perl’s =~ operator</b>, when it appears as a predicate (in if, while, etc.).
	 */
	@Test
	void test1_FindOperator() {
		def text = "some text to match"
		def m = (text =~ /\w+/)
		assert m instanceof Matcher

		def words = []
		//equivalent to calling if (!m.find())
		while (m) {
			words << m.group()
		}
		assert words == ['some', 'text', 'to', 'match']
	}

	/**
	 * The match operator <b>(==~)</b> is a slight variation of the find operator, 
	 * that does not return a Matcher but a <b>boolean</b> and requires a <b>strict match</b> of the input string
	 */
	@Test
	void test2_ExactlyMatchOperator() {
		def m = ('some text to match' ==~ /match/)
		assert m instanceof Boolean && !m

		// strict matches
		assert 'some text to match' ==~ 'so.*ch'
		// not strict matches
		assert 'some text to match' !=~ 'o.*ch'
	}
}
