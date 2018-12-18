import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class AffineCipherTest {

	private AffineCipher affineCipher;

	@Before
	public void genAffineCipher() {
		affineCipher = new AffineCipher();
	}

	@Rule
	public ExpectedException expectedException = ExpectedException.none();

	@Test
	public void testEncodeYes() {
		assertEquals("xbt", affineCipher.encode("yes", 5, 7));
	}

	@Test
	public void testEncodeNo() {
		assertEquals("fu", affineCipher.encode("no", 15, 18));
	}

	@Test
	public void testEncodeOMG() {
		assertEquals("lvz", affineCipher.encode("OMG", 21, 3));
	}

	@Test
	public void testEncodeO_M_G() {
		assertEquals("hjp", affineCipher.encode("O M G", 25, 47));
	}

	@Test
	public void testEncodeMindBlowingly() {
		assertEquals("rzcwa gnxzc dgt", affineCipher.encode("mindblowingly", 11, 15));
	}

	@Test
	public void testEncodeNumbers() {
		assertEquals("jqgjc rw123 jqgjc rw", affineCipher.encode("Testing,1 2 3, testing.", 3, 4));
	}

	@Test
	public void testEncodeDeepThought() {
		assertEquals("iynia fdqfb ifje", affineCipher.encode("Truth is fiction.", 5, 17));
	}

	@Test
	public void testEncodeAllTheLetters() {
		assertEquals("swxtj npvyk lruol iejdc blaxk swxmh qzglf",
				affineCipher.encode("The quick brown fox jumps over the lazy dog.", 17, 33));
	}

	@Test
	public void testEncodeThrowsMeaningfulException() throws IllegalArgumentException {
		expectedException.expect(IllegalArgumentException.class);
		expectedException.expectMessage("Error: keyA and alphabet size must be coprime.");

		affineCipher.encode("This is a test", 6, 17);
	}

	@Test
	public void testDecodeExercism() {
		assertEquals("exercism", affineCipher.decode("tytgn fjr", 3, 7));
	}

	@Test
	public void testDecodeSentence() {
		assertEquals("anobstacleisoftenasteppingstone",
				affineCipher.decode("qdwju nqcro muwhn odqun oppmd aunwd o", 19, 16));
	}

	@Test
	public void testDecodeNumbers() {
		assertEquals("testing123testing", affineCipher.decode("odpoz ub123 odpoz ub", 25, 7));
	}

	@Test
	public void testDecodeAllTheLetters() {
		assertEquals("thequickbrownfoxjumpsoverthelazydog",
				affineCipher.decode("swxtj npvyk lruol iejdc blaxk swxmh qzglf", 17, 33));
	}

	@Test
	public void testDecodeWithNoSpaces() {
		assertEquals("thequickbrownfoxjumpsoverthelazydog",
				affineCipher.decode("swxtjnpvyklruoliejdcblaxkswxmhqzglf", 17, 33));
	}

	@Test
	public void testDecodeWithTooManySpaces() {
		assertEquals("jollygreengiant", affineCipher.decode("vszzm    cly   yd cg    qdp", 15, 16));
	}

	@Test
	public void testDecodeThrowsMeaningfulException() {
		expectedException.expect(IllegalArgumentException.class);
		expectedException.expectMessage("Error: keyA and alphabet size must be coprime.");

		affineCipher.decode("Test", 13, 5);
	}

}