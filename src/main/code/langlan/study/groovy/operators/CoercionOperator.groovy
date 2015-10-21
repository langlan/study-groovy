package langlan.study.groovy.operators
import langlan.study.groovy.User
import langlan.study.groovy.semantics.type.coercion.Closure2ArbitraryType
import langlan.study.groovy.semantics.type.coercion.Closure2_SAM_Type
import langlan.study.groovy.semantics.type.coercion.Map2Type
import langlan.study.groovy.semantics.type.coercion.String2enum
import langlan.study.groovy.syntax.Chars
import langlan.study.groovy.syntax.ListsAndArrays
import org.codehaus.groovy.runtime.ScriptBytecodeAdapter
import org.codehaus.groovy.runtime.typehandling.DefaultTypeTransformation
import org.junit.Test
/**
 * The coercion operator <b style="color:blue;">as</b> is a variant of casting. 
 * Coercion converts object from one type to another without them being compatible for assignement.<br/>
 * When an object is coerced into another, unless the target type is the same as the source type, 
 * coercion will return a <b>new object</b>. 
 * The rules of coercion differ depending on the source and target types, and coercion may fail if no conversion rules are found. <br/>
 * Custom conversion rules may be implemented thanks to the <b style="color:blue;">asType(Class)</b> method. <br/>
 * <b>NOTE:</b>
 * <ul>
 *     <li>coercion : <code>def a = b as A;</code> {@link ScriptBytecodeAdapter#asType(Object, Class)} -> a.asType(b.class)</li>
 *     <li>casting  : <code>def a = (A) b;</code> {@link ScriptBytecodeAdapter#castToType(Object, Class)} -> {@link DefaultTypeTransformation#castToType(Object, Class)}</li>
 *     <li>explicit type declaration  : same as casting or determined at compile-time (primitive, array, ArraysList)</li>
 * </ul>
 * Sometimes both work out, but they are not same. Consider using coercion first, especially between non-primitive types.
 * @see ListsAndArrays
 * @see Chars
 * @see Closure2_SAM_Type
 * @see Closure2ArbitraryType
 * @see Map2Type
 * @see String2enum
 */
class CoercionOperator extends GroovyTestCase {

	@Test
	void testAs() {
		shouldFail(ClassCastException) {
			Integer x = '123'
		}
		Integer x = '123' as int
		assert x instanceof Integer && x == 123
	}

	/**
	 * By implementing <b style="color:blue;">asType(Class)</b> method
	 */
	@Test
	void testAs_Custom() {
		def u = this as User // asType(User) Supported
		assert u instanceof User && u.@name == this.hashCode().toString()

		shouldFail(ClassCastException) {
			println this as String
		}
	}

	/**
	 * Supported only for class User
	 * @param target
	 * @return
	 */
	def asType(Class target) {
		if (target == User) {
			return new User(this.hashCode() as String);
		}
		throw new ClassCastException("${getClass()} cannot be coerced into $target")
	}
}
