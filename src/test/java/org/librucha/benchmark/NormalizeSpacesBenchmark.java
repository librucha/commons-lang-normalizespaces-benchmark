package org.librucha.benchmark;

import com.google.caliper.*;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class NormalizeSpacesBenchmark extends SimpleBenchmark {

	@Param({ "0", "10", "100", "1000" })
	private int length;

	public static final String NBSP = String.valueOf((char) 160);
	private static final String INPUT = "  \tlibor\t \n\r" + NBSP + NBSP + "ondrusek   \t\t\n\n\r  ";
	private static final String EXPECTED = "libor   ondrusek";

	public void timeApacheNormalize(int reps) {
		for (int i = 0; i < reps; i++) {
			String normalized = StringUtils.normalizeSpace(INPUT);
			assertEquals(EXPECTED, normalized);
		}
	}

	public void timeNewNormalize(int reps) {
		for (int i = 0; i < reps; i++) {
			String normalized = NewStringUtils.normalizeSpace(INPUT);
			assertEquals(EXPECTED, normalized);
		}
	}

	@Test
	public void benchmark() throws Exception {
		Runner.main(NormalizeSpacesBenchmark.class, new String[0]);
	}

	@Test
	public void testApacheNormalize() throws Exception {
		assertEquals(EXPECTED, StringUtils.normalizeSpace(INPUT));
	}

	@Test
	public void testNewNormalize() throws Exception {
		assertEquals(EXPECTED, NewStringUtils.normalizeSpace(INPUT));
	}
}
