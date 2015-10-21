package langlan.study.groovy.semantics.statements
import groovy.transform.TypeChecked
import org.junit.Test

@TypeChecked
class ExceptionHandling extends GroovyTestCase {

	/**
	 * Like Java 7, since Groovy 2.0. <br/>
	 * With the multi catch block , we're able to define several exceptions to be catch and treated by the same catch block
	 */
	void catchMultiple(Throwable e) throws Throwable {
		try {
			throw e
		} catch (IOException | NullPointerException ee) {
			assert ee instanceof IOException || ee instanceof NullPointerException
		}
	}

	/**
	 * Note that it’s catching all <b>Exception</b>s, not `<b>Throwable</b>`s. If you need to really catch "everything", you’ll have to be explicit and say you want to catch `Throwable`s.
	 */
	void catchAny(Throwable e) throws Throwable {
		try {
			throw e
		} catch (any) {
			assert any instanceof Exception
		}
	}

	@Test
	void testCatch() {
		catchMultiple(new IOException())
		catchMultiple(new NullPointerException())
		catchAny(new Exception())

		shouldFail(RuntimeException){
			catchMultiple(new RuntimeException())
		}
		shouldFail(Throwable) {
			catchAny(new Throwable())
		}
	}
}
