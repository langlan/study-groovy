package langlan.study.groovy.closure;

import static org.junit.Assert.assertEquals;

import org.junit.Assert;
import org.junit.Test;

import langlan.study.groovy.closure.JInnerClassTest.Level1.Level2;
import langlan.study.groovy.closure.JInnerClassTest.Level1.Level2.Level3;

public class JInnerClassTest {
	class Level1 {
		int a, b, c;

		class Level2 {
			int a, b;

			class Level3 {
				int a;

				void go() {
					a++;
					Level2.this.a++;
					Level1.this.a++;
					b++;
					c++;
					assertEquals(Level3.class, this.getClass());
				}
			}
		}
	}

	/**
	 * So the class name of Multiple-Level-Inner-Class will contains many '$'
	 */
	@Test
	public void testInnerClassName() {
		assertEquals(this.getClass().getName() + "$" + Level1.class.getSimpleName(), Level1.class.getName());
		assertEquals(Level1.class.getName() + "$" + Level2.class.getSimpleName(), Level2.class.getName());
		assertEquals(Level2.class.getName() + "$" + Level3.class.getSimpleName(), Level3.class.getName());
	}

	@Test
	public void testScope() {
		Level1 l1 = this.new Level1();
		Level2 l2 = l1.new Level2();
		Level3 l3 = l2.new Level3();
		l3.go();
		
		Assert.assertEquals(l3.a, 1);
		Assert.assertEquals(l2.a, 1);
		Assert.assertEquals(l2.b, 1);
		assertEquals(l1.a, 1);
		assertEquals(l1.b, 0);
		assertEquals(l1.c, 1);

	}
}
