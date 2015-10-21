package langlan.study.groovy.closure

import org.junit.Test

class DirectiveUse {
	@Test
	void test() {
		use(DirectiveUse) {
			assert 6 == ([1, 2, 3].mysum())
			assert [2, 4, 6] == [1, 2, 3].mycollect {it * 2}
		}
	}

	static mysum(numbers) {
		int sum = 0
		for (i in numbers) {
			sum += i
		}
		sum
	}

	static mycollect(numbers, Closure c) {
		def ret = []
		for (i in numbers) {
			ret << c.call(i)
		}
		ret
	}
}