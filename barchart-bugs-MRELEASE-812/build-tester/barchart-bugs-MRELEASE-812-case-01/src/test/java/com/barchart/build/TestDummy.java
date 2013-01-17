package com.barchart.build;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TestDummy {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test0() {

		final Pattern pattern = Pattern.compile("job/(.*)/build");

		final Matcher matcher = pattern
				.matcher("Handling GET /view/feed/job/barchart-feed-ddf_SITE/build : RequestHandlerThread[#60]");

		matcher.find();

		matcher.group(1);

	}

	@Test
	public void test1() {

		final long n = 1000L;

		final int major = 2;
		final int minor = 3;
		final int patch = 48;

		final long value = ((major * n) + minor) * n + patch;

		System.out.println("" + value);

	}

}
