package langlan.study.groovy.syntax

import org.junit.Test;

class Numbers {
	/**
	 * byte char short int long 
	 * java.lang.BigInteger
	 */
	@Test
	void test0_AutoTypeDecision() {
		def a = 1
		assert a instanceof Integer

		// Integer.MAX_VALUE
		def b = 2147483647
		assert b instanceof Integer

		// Integer.MAX_VALUE + 1
		def c = 2147483648
		assert c instanceof Long

		// Long.MAX_VALUE
		def d = 9223372036854775807
		assert d instanceof Long

		// Long.MAX_VALUE + 1
		def e = 9223372036854775808
		assert e instanceof BigInteger

		//As well as for negative numbers:

		def na = -1
		assert na instanceof Integer

		// Integer.MIN_VALUE
		def nb = -2147483648
		assert nb instanceof Integer

		// Integer.MIN_VALUE - 1
		def nc = -2147483649
		assert nc instanceof Long

		// Long.MIN_VALUE
		def nd = -9223372036854775808
		assert nd instanceof Long

		// Long.MIN_VALUE - 1
		def ne = -9223372036854775809
		assert ne instanceof BigInteger
	}

	/**Java7 Or Groovy2*/
	@Test
	void test10_BinaryLiteral() {
		int xInt = 0b10101111
		assert xInt == 175

		short xShort = 0b11001001
		assert xShort == 201 as short

		byte xByte = 0b11
		assert xByte == 3 as byte

		long xLong = 0b101101101101
		assert xLong == 2925l

		BigInteger xBigInteger = 0b111100100001
		assert xBigInteger == 3873g

		int xNegativeInt = -0b10101111
		assert xNegativeInt == -175
	}

	@Test
	void test11_OctalLiteral() {
		int xInt = 077
		assert xInt == 63

		short xShort = 011
		assert xShort == 9 as short

		byte xByte = 032
		assert xByte == 26 as byte

		long xLong = 0246
		assert xLong == 166l

		BigInteger xBigInteger = 01111
		assert xBigInteger == 585g

		int xNegativeInt = -077
		assert xNegativeInt == -63
	}

	@Test
	void test12_HexadecimalLiteral() {
		int xInt = 0x77
		assert xInt == 119

		short xShort = 0xaa
		assert xShort == 170 as short

		byte xByte = 0x3a
		assert xByte == 58 as byte

		long xLong = 0xffff
		assert xLong == 65535l

		BigInteger xBigInteger = 0xaaaa
		assert xBigInteger == 43690g

		Double xDouble = new Double('0x1.0p0')
		assert xDouble == 1.0d

		int xNegativeInt = -0x77
		assert xNegativeInt == -119
	}

	@Test
	void testUnderscores() {
		long creditCardNumber = 1234_5678_9012_3456L
		long socialSecurityNumbers = 999_99_9999L
		double monetaryAmount = 12_345_132.12
		long hexBytes = 0xFF_EC_DE_5E
		long hexWords = 0xFFEC_DE5E
		long maxLong = 0x7fff_ffff_ffff_ffffL
		long alsoMaxLong = 9_223_372_036_854_775_807L
		long bytes = 0b11010010_01101001_10010100_10010010
	}

	/**
	 *
	 */
	@Test
	void testTypeSuffix() {
		assert 42I == new Integer('42')
		assert 42i == new Integer('42') // lowercase i more readable
		assert 123L == new Long("123") // uppercase L more readable
		assert 2147483648 == new Long('2147483648') // Long type used, value too large for an Integer
		assert 456G == new BigInteger('456')
		assert 456g == new BigInteger('456')
		assert 123.45 == new BigDecimal('123.45') // default BigDecimal type used
		assert 1.200065D == new Double('1.200065')
		assert 1.234F == new Float('1.234')
		assert 1.23E23D == new Double('1.23E23')
		assert 0b1111L.class == Long // binary
		assert 0xFFi.class == Integer // hexadecimal
		assert 034G.class == BigInteger // octal
	}
}
