package langlan.study.groovy.meta

import org.junit.Test

/**
 * There are two requirements that extension methods from a <b>category class</b> should met <ul>
 *     <li>The extension methods are all declared as static methods</li>
 *     <li>The first argument of the static method must define the type the method is attached to. The other arguments are the normal arguments the method will take as parameters.</li>
 * </ul>
 * Because of the parameter and static method convention, category method definitions may be a bit less intuitive than normal method definitions. <br/>
 * As an alternative Groovy comes with a {@link Category} annotation that transforms annotated classes into category classes at compile-time.
 */
class MetaUseCategoryAnnotation {
	@Category(Number)
	static class NumberCategory {
		Distance getMeters() {
			new Distance(number: this)
		}
	}

	static class Distance {
		def number

		String toString() {
			"${number}m"
		}
	}

	@Test
	void test() {
		use(NumberCategory) {
			assert 42.meters.toString() == '42m'
		}
	}

}
