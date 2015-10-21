package langlan.study.groovy.integration;

import groovy.util.Eval;
import org.junit.Assert;
import org.junit.Test;

/**
 * The Eval class makes it very easy to evaluate simple scripts, but doesn’t scale: there is no caching of the script,
 * and it isn’t meant to evaluate more than one liners.
 */
public class GroovyEval extends Assert {
	@Test
	public void test() {
		assertEquals(Eval.me("33*3"), 99);
		assertEquals(Eval.me("'foo'.toUpperCase()"), "FOO");

		// Eval supports multiple variants that accept parameters for simple evaluation:
		assertEquals(Eval.x(4, "2*x"), 8);
		assertEquals(Eval.me("k", 4, "2*k"), 8);
		assertEquals(Eval.xy(4, 5, "x*y"), 20);
		assertEquals(Eval.xyz(4, 5, 6, "x*y+z"), 26);
	}
}
