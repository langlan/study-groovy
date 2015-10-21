package langlan.study.groovy.io

import org.junit.Assert;
import org.junit.Test

class FileTest {
	@Test
	void test(){
		File f = new File("D:/TEMP_FILES");
		f.eachDir {
			Assert.assertTrue it.isDirectory()
		}
	}
}
