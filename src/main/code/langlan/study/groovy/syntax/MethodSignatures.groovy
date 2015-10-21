package langlan.study.groovy.syntax

import org.junit.Test

/**
 * 
 */
class MethodSignatures {
	def defaultValues(a='a',int b=3,String c){
		"${a} ${b} ${c}"
	}

	/**treate the first parameter as map*/
	def namedArgs(map,b,c){
		"$map $b $c"
	}

	/**
	 * last parameter : Object[] 
	 */
	def variableArgs(Object[] args){
		args.length
	}
	
	/**
	 * if last parameter is Closure, then you can invoke method like obj.closureArg(preargs){...}
	 */
	def closureArg(a,Closure c){
		c.call(a)
	}

	@Test
	void test_Argument_VaiableLength(){
		assert 0 == variableArgs()
		assert 1 == variableArgs(1)
		assert 3 == variableArgs(1,2,'a')
	}

	@Test
	void test_Argument_Closure(){
		assert 3 == closureArg(3,{it})
		assert 4 == closureArg(4){it}
	}

	@Test
	void test_Argument_DefaultValues(){
		assert 'a 3 a' == defaultValues('a')
		assert 'a 3 a' == defaultValues('a','a')
		assert '0 3 a' == defaultValues(0,'a')
		assert 'a 3 a' == defaultValues('a',3,'a')
	}

	@Test
	void test_Argument_Named(){
		assert '[a:a, b:b] c d' == namedArgs(a:'a',b:'b','c','d')
		assert '[a:a, b:b] c d' == namedArgs(a:'a','c','d',b:'b')
		assert '[a:a, b:b] c d' == namedArgs('c','d',a:'a',b:'b')
		assert '[a:a, b:b] c d' == namedArgs('c',a:'a','d',b:'b')
		assert '[b:b, a:a] c d' == namedArgs('c','d',b:'b',a:'a')
	}
}
