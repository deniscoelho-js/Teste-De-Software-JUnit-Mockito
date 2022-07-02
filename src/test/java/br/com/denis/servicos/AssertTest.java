package br.com.denis.servicos;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class AssertTest {
	@Test
	public void test() {
		assertTrue(true);
		assertFalse(false);
		assertEquals(1, 1);
	}
}
