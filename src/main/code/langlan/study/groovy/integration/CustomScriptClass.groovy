package langlan.study.groovy.integration

import org.codehaus.groovy.control.CompilerConfiguration
import org.junit.Test

/**
 * The custom class can be used as the script base class by using a custom configuration.
 * @see CompilerConfiguration
 */
class CustomScriptClass {
	static abstract class MyScript extends Script {
		String name

		String greet() {
			"Hello, $name!"
		}
	}

	@Test
	void test() {
		def config = new CompilerConfiguration()
		config.scriptBaseClass = 'CustomScriptClass.MyScript'

		def shell = new GroovyShell(this.class.classLoader, new Binding(), config)
		def script = shell.parse('greet()')
		assert script instanceof MyScript
		script.setName('Michel')
		assert script.run() == 'Hello, Michel!'

		//
		shell.setProperty("hi", {'HI~'})
		assert 'HI~' == shell.evaluate("hi()")

		println shell.classLoader
		println GroovyShell.classLoader

	}

	@Test
	void testClassLoader(){
		println String.classLoader
		println this.class.classLoader
		println Thread.currentThread().getContextClassLoader()
		def cl = ClassLoader.getSystemClassLoader()
		while(cl!=null){
			println cl;
			cl = cl.parent;
		}
	}

}


