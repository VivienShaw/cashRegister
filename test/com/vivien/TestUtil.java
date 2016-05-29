package com.vivien;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.vivien.Util;

import junit.framework.TestCase;

public class TestUtil extends TestCase {

	@Before
	protected void setUp() throws Exception {
		super.setUp();
	}

	@After
	protected void tearDown() throws Exception {
		super.tearDown();
	}

	@Test
	public void testReadFile() {
		String path = "test/test.txt";
		String res = Util.readFile(path);
		assertEquals("This is a test file.", res);
	}

	@Test
	public void testNumberFormat() {
		double num = 123.121;
		assertEquals("123.12", Util.numberFormat(num));
	}

}
