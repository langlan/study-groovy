package langlan.study.groovy.closure;

import org.junit.Assert;
import org.junit.Test;

/**
 * Considering:
 * <ul>
 * <li>Groovy-Anonymous-Closure-ClassName VS Java-Inner-Class-ClassName</li>
 * <li>Groovy-FreeVariables VS Java-final-local-variables</li>
 * <li>instance-of-Groovy-Anonymous-Closure VS instance-of-Java-Inner-Class this/owner/delegate</li>
 * <li>Groovy-Closure : block/method/class</li>
 * </ul>
 *
 *
 */
public class JClosureSimulate {
	String mask;

	@SuppressWarnings("unused")
	@Test
	public void testMultiLevel() {
		final SReference<Integer> i = new SReference<Integer>(0);
		final SClosure[] closures = new SClosure[3];

		closures[0] = new SClosure() {// anonymous
            /**abc*/
			String closure_field;
			String mask;

			@Override
			public void go() {// ===================================================================
				final SReference<String> closure_local = new SReference<String>("abc");
				final SReference<String> mask = new SReference<String>("mask");

				Assert.assertTrue(this == closures[0]);
				i.set(i.get() + 1);

				class NamedC implements SClosure {// named
					String mask;

					@Override
					public void go() {// ========================================
						final SReference<String> mask = new SReference<String>("mask");

						Assert.assertTrue(this == closures[1]);
						i.set(i.get() + 1);

						closures[2] = new SClosure() {// anonymous
							@Override
							public void go() {// =====================
								Assert.assertTrue(this == closures[2]);
								// unmasked variables
								i.set(i.get() + 1);
								closure_field = "closure_field";
								closure_local.set("closure_local");

								// nearest variable (have masked others)
								mask.set(mask.get() + "mask");

								// can access masked member by ClassName.this.
								JClosureSimulate.this.mask = "mask";
								NamedC.this.mask = "mask";
							}// ======================================
						};
					}// =========================================================

				}
				closures[1] = new NamedC();
			}
		};// =======================================================================================

		for (int x = 0; x < closures.length; x++) {
			closures[x].go();
			System.out.println(closures[x].getClass().getName());
			System.out.println(i.get());
		}
	}
}

interface SClosure {
	void go();
}

class SReference<T> {
	T t;

	public SReference(T value) {
		this.t = value;
	}

	T get() {
		return t;
	}

	void set(T value) {
		this.t = value;
	}
}
