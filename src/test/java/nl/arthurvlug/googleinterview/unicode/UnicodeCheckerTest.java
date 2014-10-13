package nl.arthurvlug.googleinterview.unicode;

import static org.junit.Assert.*;
import nl.arthurvlug.googleinterview.unicode.UnicodeChecker;

import org.junit.Test;

public class UnicodeCheckerTest {
	private UnicodeChecker checker = new UnicodeChecker();
	
	@Test
	public void printTest() {
		assertEquals("00000000", checker.printByte(Prefix.ASCII.getPrefix()));
		assertEquals("10000000", checker.printByte(Prefix.CONTINUATION.getPrefix()));
		assertEquals("11000000", checker.printByte(Prefix.BYTE2.getPrefix()));
		assertEquals("11100000", checker.printByte(Prefix.BYTE3.getPrefix()));
		assertEquals("11110000", checker.printByte(Prefix.BYTE4.getPrefix()));
		assertEquals("11111000", checker.printByte(Prefix.BYTE5.getPrefix()));
		assertEquals("11111100", checker.printByte(Prefix.BYTE6.getPrefix()));
		assertEquals("11111110", checker.printByte(Prefix.BYTE7.getPrefix()));
	}
	
	@Test
	public void asciiTest() throws Exception {
		boolean isValidUtf8 = checker.checkUnicode(new byte[] {
				Prefix.ASCII.getPrefix(),
				Prefix.ASCII.getPrefix()
		});
		assertTrue(isValidUtf8);
	}
	
	@Test
	public void continuationByteTest() throws Exception {
		boolean isValidUtf8 = checker.checkUnicode(new byte[] {
				Prefix.ASCII.getPrefix(),
				Prefix.CONTINUATION.getPrefix()
		});
		assertFalse(isValidUtf8);
	}
	
	@Test
	public void continuationByteTest2() throws Exception {
		boolean isValidUtf8 = checker.checkUnicode(new byte[] {
				Prefix.CONTINUATION.getPrefix(),
				Prefix.ASCII.getPrefix()
		});
		assertFalse(isValidUtf8);
	}
	
	@Test
	public void byte2Test2() throws Exception {
		boolean isValidUtf8 = checker.checkUnicode(new byte[] {
				(byte) (Prefix.BYTE2.getPrefix() | 1 << 2),
				Prefix.CONTINUATION.getPrefix(),
				Prefix.CONTINUATION.getPrefix()
		});
		assertTrue(isValidUtf8);
	}
	
	@Test
	public void byte3Test() throws Exception {
		boolean isValidUtf8 = checker.checkUnicode(new byte[] {
				(byte) (Prefix.BYTE3.getPrefix() | 1 << 1),
				Prefix.CONTINUATION.getPrefix(),
				Prefix.CONTINUATION.getPrefix()
		});
		assertFalse(isValidUtf8);
	}
	
	@Test
	public void byte3Test2() throws Exception {
		boolean isValidUtf8 = checker.checkUnicode(new byte[] {
				(byte) (Prefix.BYTE3.getPrefix() | 1 << 1),
				Prefix.CONTINUATION.getPrefix(),
				Prefix.CONTINUATION.getPrefix(),
				Prefix.CONTINUATION.getPrefix()
		});
		assertTrue(isValidUtf8);
	}
	
	@Test
	public void byte3Test3() throws Exception {
		boolean isValidUtf8 = checker.checkUnicode(new byte[] {
				(byte) (Prefix.BYTE3.getPrefix() | 1 << 1),
				Prefix.CONTINUATION.getPrefix(),
				Prefix.CONTINUATION.getPrefix(),
				Prefix.CONTINUATION.getPrefix(),
				Prefix.CONTINUATION.getPrefix()
		});
		assertFalse(isValidUtf8);
	}
	
	@Test
	public void byte3Test4() throws Exception {
		boolean isValidUtf8 = checker.checkUnicode(new byte[] {
				(byte) (Prefix.BYTE3.getPrefix() | 1 << 1),
				Prefix.ASCII.getPrefix(),
				Prefix.CONTINUATION.getPrefix(),
				Prefix.CONTINUATION.getPrefix()
		});
		assertFalse(isValidUtf8);
	}
	
	@Test
	public void byte3Test5() throws Exception {
		boolean isValidUtf8 = checker.checkUnicode(new byte[] {
				(byte) (Prefix.BYTE3.getPrefix() | 1 << 1),
				Prefix.CONTINUATION.getPrefix(),
				Prefix.CONTINUATION.getPrefix(),
				Prefix.CONTINUATION.getPrefix(),
				Prefix.ASCII.getPrefix()
		});
		assertTrue(isValidUtf8);
	}
}
