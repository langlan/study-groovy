package langlan.study.groovy.meta
import org.junit.Test

class MetaMethodMissing {

	@Test
	void test() {
		assert this.someUnknownMethod(42l) == 'someUnknownMethod'
		assert this._abc() == '_abc';
		assert this._abc(1) == '_abc';

		def ano = new MetaMethodMissing()
		assert ano.someUnknownMethod(42l) == 'someUnknownMethod'
		assert ano._abc() == ''
		assert ano._abc(1) == '1'
	}

	def methodMissing(String name, def args) {
		if (name.startsWith("_")) {
			MetaMethodMissing.metaClass."$name" = { vars ->
				vars?.toString() ?: ''
			}
		}
		return name
	}


}