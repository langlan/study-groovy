package langlan.study.groovy.syntax

import org.junit.Test

/**
 * interpolation : 插值，篡改
 */
class StringInterpolation {
	final shouldFail = new GroovyTestCase().&shouldFail

	/**
	 * Double quoted strings are plain {@link String} if there’s no interpolated expression, but are {@link GString} instances if interpolation is present.
	 */
	@Test
	void test00_DoubleQuotedString(){
		def plainString = "pain java.lang.String"
		def gString = "GString by ${'interpolated values'}"
		assert plainString instanceof String
		assert gString instanceof GString

		// same as Slashy or $Slashy
		plainString = /pain java.lang.String/
		assert plainString instanceof String
		plainString = $/pain java.lang.String/$
		assert plainString instanceof String
	}

	/**
	 * Any groovy expressions in braces.
	 */
	@Test
	void test0_$Braces() {
		def number = 10;
		def person = [name: 'Guillaume', age: 36]
		assert "hello ${number}" == 'hello 10'
		assert "${person.name} is ${person.age} years old" == 'Guillaume is 36 years old'
		assert "${person.'age'}" == "${person.age}"
		assert "${person['age']}" == "${person.age}"
		assert "${number.toString()}" == '10'
	}

	/**
	 * <ul>
	 * <li>$ Only For dotted expression or simply java identifier.
	 *   <ul>
	 * 	   <li>Dotted expression can not be method invoking.</li>
	 *     <li>Suffix of dotted expression can not be quoted. (Only java identifier)</li>
	 * 	 </ul>
	 * </li>
	 * </ul>
	 */
	@Test
	void test1_$() {
		def number = 10;
		def person = [name: 'Guillaume', age: 36]

		assert "hello $number" == 'hello 10'
		assert "$person.name is $person.age years old" == 'Guillaume is 36 years old'

		shouldFail(MissingPropertyException) {println "$number.toString()"}
		// You can think of "$number.toString()" as being interpreted by the parser as "${number.toString}()".

		assert person.age == person.'age'
		assert person.age == person['age']
		assert "$person['age']" == person.toString() + "['age']"
		assert "$person.'age'" == person.toString() + ".'age'"
	}

	@Test
	void test2_$Closure() {
		def s = 'world'
		assert "${-> def a = 'hello'; a + ' ' + s}" == 'hello world'
		assert "${it -> it << 'hello'; it << ' '; it << s; it instanceof StringWriter}" == 'hello world'
	}

	/**
	 * <ul>
	 *     <li><b>Eager:</b> With a plain interpolated expression, the value was actually bound at the time of creation of the GString.</li>
	 *     <li><b>Lazy:</b> With a closure expression, the closure is called upon each coercion of the GString into String, resulting in an updated string containing the new number value.</li>
	 * </ul>
	 *
	 */
	@Test
	void test3_EagerAndLazy() {
		def number = 10;
		def gstr_plain = "$number"
		def gstr_closure = "${-> number}"

		assert gstr_plain == number.toString()
		assert gstr_closure == number.toString()

		number++
		assert gstr_plain != number.toString() // eager
		assert gstr_closure == number.toString() // lazy
	}
}
