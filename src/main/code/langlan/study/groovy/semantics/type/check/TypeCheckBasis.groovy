package langlan.study.groovy.semantics.type.check

import groovy.transform.TypeChecked
import groovy.transform.TypeCheckingMode
import org.junit.Before
import org.junit.Test

/**
 * By default, Groovy performs minimal type checking at compile time. Since it is primarily a dynamic language,
 * most checks that a static compiler would normally do aren’t possible at compile time.
 * A method added via runtime metaprogramming might alter a class or object’s runtime behavior. {@link TypeCheckBasis#dynamicFeature()}
 * <p>
 * This means that in general, in Groovy, you can’t make any assumption about the type of an object beyond its declaration type,
 * and even if you know it, you can’t determine at compile time what method will be called, or which property will be retrieved.
 * It has a lot of interest, going from writing DSLs to testing, which is discussed in other sections of this manual.
 * <p>
 * However, if your program doesn’t rely on dynamic features and that you come from the static world (in particular, from a Java mindset),
 * not catching such "errors" at compile time can be surprising.
 * We can explicitly instruct the compiler that we are switching to a type checked mode.
 * This can be done by annotating a class or a method with @{@link TypeChecked} <p>
 * The scope of type checking can be restricted. For example,
 * if a class is type checked, you can instruct the type checker to skip a method by annotating it with @{@link TypeChecked}({@link TypeCheckingMode#SKIP})
 */
@TypeChecked
class TypeCheckBasis {
	static class Person {
		String firstName
		String lastName
	}

	/**
	 * a example code-block shows one reason that why Groovy performs minimal type checking at compile time by default.
	 */
	@TypeChecked(TypeCheckingMode.SKIP)
	@Before
	void dynamicFeature() {
		Person.metaClass.getFormattedName = { "$delegate.firstName $delegate.lastName" }
	}

	/**
	 *
	 */
	@TypeChecked(TypeCheckingMode.SKIP)
	@Test
	void test() {
		def p = new Person(firstName: 'Raymond', lastName: 'Devos')
		assert p.formattedName == 'Raymond Devos'
	}
}
