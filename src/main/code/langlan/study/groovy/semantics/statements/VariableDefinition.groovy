package langlan.study.groovy.semantics.statements

import org.junit.Test;

/**
 * Variables can be defined using either their type (like <code style="color:blue;">String</code>) or by using the keyword 
 * <b style="color:blue;">def</b>.
 * <p>
 * <b style="color:blue;">def</b> is a replacement for a type name. 
 * In variable definitions it is used to indicate that you don’t care about the type. 
 * In variable definitions it is mandatory to either provide a type name explicitly or to use "def" in replacement. 
 * This is needed to the make variable definitions detectable for the Groovy parser.
 * </p>
 * <p> You can think of def as an alias of Object and you will understand it in an instant.
 </p>
 * 
 * 
 */
class VariableDefinition extends GroovyTestCase{
	@Test
	void test0_Simple() {
		String x
		def obj
		assert x==null
		assert obj==null
	}

	@Test
	void test1_Multiple() {
		def a,b,c
		String x,y,z
		assert a==null && b==null && c==null
		assert x==null && y==null && z==null
	}

	@Test
	void test2_WithAssignment() {
		def test = new VariableAssignment()
		test.test00_WithDefinition()
		test.test10_Multiple_WithDefinition()
	}

	/**
	 * In Groovy, Local Variables has no need to specify a initial value. <br/>
	 * Default initial value according default value rules of Class member variable in Java.
	 */
	@Test
	void test3_Local_InitialValue() {
		def o
		boolean b
		int i
		assert o==null;
		assert !b;
		assert i==0;
	}
	
	/**
	 * Free-Variables for Closure will be rewrite to  by groovy compiler.  
	 */
	@Test
	void test3_Local_InitialValue_Reference() {
		int i,j
		assert i==0 && i!=null;
		assert j==null && j!=0;
		def closure={j++}
		shouldFail(NullPointerException,closure)
	}
}
