package langlan.study.groovy.config

import org.junit.Test


class GroovyConfig {
	/**
	 * abc
	 */
	@Test
	public void testConfigSluper() {
		def configSlurper = new ConfigSlurper()
		def configFile = "/test.conf"
		def input = getClass().getResourceAsStream(configFile)
		if (!input) {
			throw new RuntimeException("Can't found config resource-file:$configFile")
		}
		ConfigObject config = configSlurper.parse(input.getText("UTF8"))
		println config
	}
}