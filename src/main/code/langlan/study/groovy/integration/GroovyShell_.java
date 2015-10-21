package langlan.study.groovy.integration;

import groovy.lang.Binding;
import groovy.lang.GroovyClassLoader;
import groovy.lang.GroovyShell;
import groovy.lang.Script;
import org.codehaus.groovy.control.CompilationUnit;
import org.junit.Assert;
import org.junit.Test;

import java.io.StringReader;
import java.util.HashMap;
import java.util.Map;

/**
 * The {@link groovy.lang.GroovyShell} class is the preferred way to evaluate scripts with the ability to cache the
 * resulting script instance. Although the Eval class returns the result of the execution of the compiled script, the
 * GroovyShell class offers more options.
 *
 * @see CompilationUnit
 * @see GroovyClassLoader
 */
public class GroovyShell_ extends Assert {

	@Test
	public void test() {
		GroovyShell shell = new GroovyShell();
		Object result = shell.evaluate("3*5");
		Object result2 = shell.evaluate(new StringReader("3*5"));
		assertEquals(result, result2);

		Script script = shell.parse("3*5");
		assertEquals(15, script.run());
	}

	@Test
	public void testBinding() {
		Map<String, Object> map = new HashMap<String, Object>();
		Binding binding = new Binding(map);
		GroovyShell shell = new GroovyShell(binding);

		binding.setProperty("aaa", "baby");
		assertEquals("baby", map.get("aaa"));

		Script script = shell.parse("aaa");
		assertEquals("baby", script.run());
		binding.setProperty("aaa", "baby2");
		assertEquals("baby2", script.run());


		// use an undeclared variable to store the result into the binding
		shell.evaluate("println foo=123");
		assertEquals(123, binding.getProperty("foo"));
		assertEquals(123, map.get("foo"));
	}
}
