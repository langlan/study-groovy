package langlan.study.groovy.closure

import langlan.study.groovy.semantics.statements.VariableDefinition
import org.junit.Test

class ClosureDefinition extends GroovyTestCase {


	@Test
	void testArgument_Single() {
		/*def c = {hasProperty('it')}
		 println c()
		 c = {->hasProperty(it)}
		 println c()*/

		// one implicit untyped parameter (named 'it')
		def cImplicit_It_0 = {}
		def cImplicit_It_1 = {it}
		def cImplicit_It_2 = {it + 1}

		// one explicit untyped parameter (still named 'it')
		def cExplicity_It = {it -> it}

		// one explicit typed parameter (still named 'it')
		def cExplicity_It_int = {int it -> it}
		def cExplicity_It_String = {String it -> it}

		// one explicit untyped parameter (named 'x')
		def cExplicity_X = {x -> x}

		// one explicit typed parameter (named 'x')
		def cExplicity_X_int = {int x -> x}
		def cExplicity_X_String = {String x -> x}

		def i = 1
		assert cImplicit_It_2(i) == i + 1

		// explicit-typed closure will check type at runtime
		shouldFail(MissingMethodException) {cExplicity_It_int('i am a string')}
		shouldFail(MissingMethodException) {cExplicity_It_String(123)}

		assert cExplicity_It_int(1) == 1
		assert cExplicity_It_String('abc') == 'abc'
	}

	@Test
	void testArgument_VariableNumbers() {
		for (int i = 0; i < 2; i++) {
			def c = i > 0 ? {Object[] args -> args.length} : {Object... args -> args.length}
			assert c() == 0
			assert c('') == 1
			assert c('', 0, 0, '') == 4
		}


		def cc = {a, Object[] args -> args.length}
		shouldFail(MissingMethodException) {assert cc() == 0}
		assert cc('') == 0
		assert cc('', 0, 0, '') == 3
	}

	@Test
	void testNested() {
		def cs = [:]
		int a = 0
		cs.a = {
			assert this.is(thisObject)
			assert owner.is(delegate)
			view(this, thisObject, owner, delegate)
			int ab = 0
			cs.b = {
				//== get real this ==
				def c = {}
				println 'real this: ' + c.owner.class.name + ' ' + c.owner.hashCode() + ' **'
				//== get real this ==

				assert this.is(thisObject)
				assert owner.is(delegate)
				view(this, thisObject, owner, delegate)
				int abc = 0
				cs.c = {
					assert this.is(thisObject)
					assert owner.is(delegate)
					view(this, thisObject, owner, delegate)
					abc++
					ab++
					a++
				}
			}
		}
		['a', 'b', 'c'].each {
			println "closure ${it}: ${cs[it].class.name} ${cs[it].hashCode()}"
			cs[it]()
		}
		println a
	}

	def seeAlso() {
		new VariableDefinition().test3_Local_InitialValue_Reference()
	}

	private void view(thiz, thisObject, owner, delegate) {
		assert thiz.is(this)
		assert thiz.is(thisObject)

		println 'this    >> ' + ("${thiz.class.name} $thiz ${thiz.hashCode()}")
		println 'owner   >> ' + (thiz.is(owner) ? 'same as this' : "${owner.class.name} ${owner.hashCode()}")
		println 'delegate>> ' + (thiz.is(delegate) ? 'same as this' : "${delegate.class.name} ${delegate.hashCode()}")
		println()
	}


}
