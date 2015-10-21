package langlan.study.groovy.semantics.type.coercion

import groovy.transform.TypeChecked
import org.junit.Test

/**
 * A <span style="color:blue;font-weight:bold;">SAM</span> type is a type which defines a <b>Single Abstract Method</b>. This includes:<ul>
 *     <li>Functional interfaces(correspond same concept in Java 8)</li>
 *     <li>Abstract classes with single abstract method</li>
 * </ul>
 */
@TypeChecked
class Closure2_SAM_Type {
	/** example: A functional interface */
	static interface Predicate<T> {
		boolean accept(T obj)
	}

	/** example: A abstract class with single abstract method */
	static abstract class Named {
		abstract String getName()
	}
	/**
	 * <ul>
	 *     <li>Any closure can be converted into a SAM type using the <span style="color:blue;font-weight:bold;">as</span> operator</li>
	 *     <li>The (<span style="color:blue;font-weight:bold;">as</span> Type expression) is optional since Groovy 2.2.0,
	 *     <b>NOTE:</b> We shouldn't omit it if we use static-type-check and any of following case<ul>
	 *         <li>if SAM Type is a abstract class</li>
	 *         <li>if Closure is not literal(variable or method reference/pointer)</li>
	 *     </ul></li>
	 * </ul>
	 */
	@Test
	void test0_Syntax() {
		Predicate filter = { it.toString().contains 'G' } as Predicate
		Named named = { 'a constant name' } as Named
		assert 'a constant name' == named.name
		assert filter.accept('Groovy')

		// Omit as-type-expression, since Groovy 2.2.0
		filter = { String it -> it.contains 'G' }

		// We can't omit as-type-expression, if SAM Type is a abstract class.
		named = { 'a constant name' } as Named
		assert 'a constant name' == named.name
		assert filter.accept('Groovy')

		// We can't omit as-type-expression, if Closure is not literal.
		def closure = { String it -> it.contains 'G' }
		filter = closure as Predicate
		assert filter.accept('Groovy')
	}

	/**
	 * Since a method reference/pointer can represents a closure, we are allowed to use method pointers
	 * <br/>NOTE: We can't omit as-type-expression, if Closure is not literal.
	 */
	@Test
	void test0_Syntax_MethodReference() {
		Predicate filter = this.&doFilter as Predicate
		assert filter.accept('Groovy')

		filter = Closure2_SAM_Type.&staticDoFilter as Predicate
		assert filter.accept('Groovy')
	}

	/**
	 * We can call a method-Acception-SAM-type with a closure, without having to create an explicit implementation of the SAM-type
	 */
	@Test
	void test1_Syntax_CallingMethodWitchAcceptSAM() {
		assert filterWithPredicate(['Java', 'Groovy']) { it.contains 'G' } == ['Groovy']

		// Before Groovy 2.2.0, We need use as operator.
		// Weird : if we use as-type-expression, static-type-check will apply on closure variable 'it', but not appliy (or saying infered type not intelligent) if we don't use as-type-expression.
		// so 'it' need typed by String
		assert filterWithPredicate(['Java', 'Groovy'], { String it -> it.contains 'G' } as Predicate) == ['Groovy']
	}

	boolean doFilter(String s) {
		s.contains('G')
	}

	static boolean staticDoFilter(String s) {
		s.contains('G')
	}

	public <T> Collection<T> filterWithPredicate(Collection<T> source, Predicate<T> predicate) {
		source.findAll { predicate.accept(it) }
	}
}
