package langlan.study.groovy.semantics.type.check

import langlan.study.groovy.semantics.type.check.TypeInference.SomeClass
import groovy.transform.TypeChecked
import org.junit.Test

/**
 * <a href="http://www.groovy-lang.org/semantics.html#type-inference"><b>Type-Inference</b></a><br/>
 * When code is annotated with @TypeChecked, the compiler performs type inference.
 * It doesn’t simply rely on static types, but also uses various techniques to infer the types of variables, return types, literals, …​
 * so that the code remains as clean as possible even if you activate the type checker. e.g. {@link TypeInference#testInference()}<p>
 * It is worth noting that although the compiler performs type inference on local variables,
 * it does not perform any kind of type inference on fields, always falling back to the declared type of a field. e.g {@link SomeClass#someMethod()}
 * <p>
 * Why such a difference? The reason is thread safety.
 * At compile time, we can’t make any guarantee about the type of a field.
 * Any thread can access any field at any time and between the moment a field is assigned a variable of some type in a method and the time is is used the line after,
 * another thread may have changed the contents of the field.
 * This is not the case for local variables: we know if they "escape" or not, so we can make sure that the type of a variable is constant (or not) over time.
 * Note that even if a field is final, the JVM makes no guarantee about it, so the type checker doesn’t behave differently if a field is final or not.
 * <p>
 * <b>NOTE: </b>
 * This is one of the reasons why we recommend to use <b>typed fields</b>.
 * While using def for local variables is perfectly fine thanks to type inference, this is not the case for fields,
 * which also belong to the public API of a class, hence the type is important.
 */
@TypeChecked
class TypeInference {
	@Test
	void testInference() {
		// variable message was inferred as being a String
		def message = 'Welcome to Groovy!'
		// calling toUpperCase is allowed by the type checker, because message was inferred as being a String
		assert 'WELCOME TO GROOVY!' == message.toUpperCase()

		// next line : compile-time error
		// println message.upper()
	}

	@Test
	void testInerence_variablesVSfeild() {
		def sc = new SomeClass()
		sc.someMethodUsingLocalVariable()
		sc.someSafeMethod()
		// NOTE: test will success because we comment the compile-error-cause line
		sc.someMethod()
	}

	static class SomeClass {
		def someUntypedField
		String someTypedField

		/** feild will not be inferred */
		void someMethod() {
			// feild was inferred as being a Object (or saying not inferred)
			someUntypedField = '123'
			// next line : compile-time error
			// someUntypedField = someUntypedField.toUpperCase()
		}

		/** typed feild works like in Java */
		void someSafeMethod() {
			someTypedField = '123'
			someTypedField = someTypedField.toUpperCase()
		}

		/** local variable will be inferred */
		void someMethodUsingLocalVariable() {
			def localVariable = '123'
			someUntypedField = localVariable.toUpperCase()
		}
	}
}
