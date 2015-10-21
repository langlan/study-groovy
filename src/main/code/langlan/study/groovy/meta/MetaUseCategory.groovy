package langlan.study.groovy.meta

import groovy.time.TimeCategory
import org.junit.Test

/**
 * There are situations where it is useful if a class not under control had additional methods. In order to enable this capability, Groovy implements a feature borrowed from <b>Objective-C</b>, called <b>Categories</b>.<p>
 * Categories are implemented with so-called <b>category classes</b>. A category class is special in that it needs to meet certain pre-defined rules for defining extension methods. <p>
 * Category classes aren’t enabled by default.
 * To use the methods defined in a category class it is necessary to apply the scoped <b style="color:blue;">use</b> method that is provided by the GDK and available from inside every Groovy object instance.<br/>
 * The <b style="color:blue;">use</b> method takes the category class as its first parameter and a closure code block as second parameter. Inside the Closure, access to the category methods is available.<p>
 * A category needs not to be directly exposed to the user code, see {@link MetaUseCategory#testImplicit()}
 * @see groovy.time.TimeCategory
 * @see groovy.servlet.ServletCategory
 * @see groovy.xml.dom.DOMCategory
 * @see MetaMethodMissing
 */
class MetaUseCategory {
	@Test
	void testTimeCategory(){
		use(TimeCategory)  {
			// base now
			println 5.minute.from.now
			println 10.hours.ago

			// base some date
			def someDate = new Date()
			println someDate - 3.months
		}
	}

	@Test
	void testImplicit(){
		use(TimeCategory){
			userCode()
		}
	}

	//TODO: is there any way to tell IDE/Type-checker we are using Categories? Someway like @DelegateTo
	void userCode() {
		assert 5.minute.from.now instanceof Date
		assert 10.hours.ago instanceof Date
	}
}
