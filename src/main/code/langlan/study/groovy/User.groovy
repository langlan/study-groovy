package langlan.study.groovy

import langlan.study.groovy.operators.ObjectOperators;
import langlan.study.groovy.operators.SpreadOperators


/**
 * A POJO. Using for show differents between direct-field-access and getter-access.
 * @see ObjectOperators#testDirectFiledAccess()
 * @see SpreadOperators#test0_ElementsSpreadOperator_DirectField()
 */
class User {
	/** field(name of user) */
	String name

	User(String name) {
		this.name = name
	}
	/** getter(name of user) */
	String getName() {
		"Name: $name"
	}
}
