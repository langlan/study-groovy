package langlan.study.groovy.semantics.type.coercion

import groovy.transform.TypeChecked
import org.junit.Test

/**
 * Groovy allows transparent String (or GString) to enum values coercion.
 */
@TypeChecked
class String2enum extends GroovyTestCase {
	static enum State {
		up,
		down
	}

	@Test
	void test() {
		String _up = 'up', _down = 'down', _invalid = 'invalid-state'
		// assgined by String
		State state = _up
		assert state == State.up

		// assgined by GString
		state = "${_down}"
		assert state == State.down

		shouldFail(IllegalArgumentException) {
			state = _invalid
		}
	}

	/**
	 * We can switch enum-value case Strings, but <br/>
	 * <b>Note: </b> we can't switch String-value case enums
	 */
	@Test
	void test_implicitCoercionInSwitch() {
		// switch enum-value case String
		switch (State.up) {
			case 'up':
				break
			default:
				assert false
		}
		// assertion failed : switch String-value case enum
		shouldFail(AssertionError) {
			switch ('up') {
				case State.up:
					break
				default:
					assert false
			}
		}
	}

	@Test
	void test_implicitCoercionInReturn() {
		assert State.up == stateForName('up')
		shouldFail(IllegalArgumentException) { stateForName('invalid-state') }
	}

	/** Groovy will implicit coerce returned-String-value to required-enum-type-value */
	State stateForName(String name) {
		name
	}
}
