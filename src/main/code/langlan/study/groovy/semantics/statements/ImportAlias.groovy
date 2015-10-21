package langlan.study.groovy.semantics.statements

import groovy.transform.TypeChecked
import org.junit.Test
import java.util.LinkedHashMap as LHM

@TypeChecked
class ImportAlias {
	@Test
	void test(){
		def a = new LHM();
		assert a instanceof LinkedHashMap
	}
}
