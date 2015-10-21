package langlan.study.groovy.syntax

import org.junit.Test;

/**
 * Sometimes called <b>dictionaries</b> or <b>associative arrays</b> in other languages, Groovy features maps. <br/>
 * Maps associate keys to values, <ul>
 *     <li>separating keys and values with <b>colons</b></li>
 *     <li>and each key/value pairs with <b>commas</b></li>
 *     <li>the whole keys and values surrounded by <b>square brackets</b></li>
 * </ul>
 *
 * by default, keys are Strings, no need to quote them
 * you can wrap keys with () like [(variableStateAcronym): stateName] to insert a variable
 */
class Maps {
	@Test
	void test0_Definitions() {
		def colors = [red: '#FF0000', green: '#00FF00', blue: '#0000FF']
		def emptyMap = [:]
		assert colors instanceof LinkedHashMap
		assert emptyMap instanceof LinkedHashMap
		assert colors.size() == 3 && emptyMap.size() == 0

		// element accessors : Using .identifier or [name] insteadof put()/get()
		assert colors['red'] == '#FF0000'
		assert colors.green == '#00FF00'

		colors['pink'] = '#FF00FF'
		colors.yellow = '#FFFF00'

		assert colors.pink == '#FF00FF'
		assert colors['yellow'] == '#FFFF00'
	}

	@Test
	void test01_Definition_VarAsKey() {
		def nameKey = 'name', ageKey = 'age'
		def person = [(nameKey): 'Guillaume', ageKey: 13]

		assert person.containsKey('name')
		assert !person.containsKey('nameKey')

		assert person.containsKey('ageKey')
		assert !person.containsKey('age')
	}
}
