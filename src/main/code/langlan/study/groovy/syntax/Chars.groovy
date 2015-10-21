package langlan.study.groovy.syntax

import langlan.study.groovy.operators.CoercionOperator
import org.junit.Test

/**
 * Unlike Java, Groovy doesn’t have an explicit character literal. However, you can be explicit about making a Groovy string an actual character, by three different means:
 * @see CoercionOperator
 */
class Chars {

	@Test
	void testDeclaration_char() {
		char c1 = 'A'
		def c2 = 'B' as char
		def c3 = (char) 'C'
		assert c1 instanceof Character
		assert c2 instanceof Character
		assert c3 instanceof Character
	}

	@Test
	void testDeclaration_Character() {
		Character c1 = 'A'
		def c2 = 'B' as Character
		def c3 = (Character) 'C'
		assert c1 instanceof Character
		assert c2 instanceof Character
		assert c3 instanceof Character
	}
}
