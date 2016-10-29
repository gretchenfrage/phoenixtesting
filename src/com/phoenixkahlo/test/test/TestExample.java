package com.phoenixkahlo.test.test;

import com.phoenixkahlo.test.Test;
import com.phoenixkahlo.test.Testing;

public class TestExample {

	public static void main(String[] args) {
		Testing.test(TestExample.class);
		Testing.test(DependencyInjectionTest.class);
	}
	
	@Test
	public static void test1() {
		System.out.println("test1");
	}
	
	@Test
	public static void test2() {
		System.out.println("test2");
	}
	
	@Test
	public static void test3() {
		System.out.println("test3");
	}
	
}
