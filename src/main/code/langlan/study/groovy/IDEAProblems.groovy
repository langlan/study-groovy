package langlan.study.groovy

import org.junit.Test

class IDEAProblems {
	/**
	 * Inffered type of <code>charA</code> is Character, But inffered type of quoted expression <code>('a' as char)</code> seems like primary type char.
	 * @TODO: So It's IDEA's problem or just diferences come from groovy inffer strategies?
	 */
	@Test
	void testAsChar() {
		def charA = 'a' as char
		assert 0 == ('a' as char).compareTo(charA)
		assert 0 == charA.compareTo('a' as char)
	}

}
