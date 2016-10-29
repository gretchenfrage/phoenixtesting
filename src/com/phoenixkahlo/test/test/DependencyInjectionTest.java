package com.phoenixkahlo.test.test;

import java.util.Random;

import com.phoenixkahlo.test.MethodMocker;
import com.phoenixkahlo.test.Mockery;
import com.phoenixkahlo.test.Test;
import com.phoenixkahlo.test.Testing;

public class DependencyInjectionTest {

	public static interface Foo {

		int bar();

	}

	private Foo foo;

	public DependencyInjectionTest(Foo foo) {
		this.foo = foo;
	}

	public void baz() {
		for (int i = 0; i < 10; i++) {
			System.out.println(foo.bar());
		}
	}

	@Test
	public static void dependencyInjectionTest() {
		Foo fooMockery = Testing.mock(Foo.class);
		MethodMocker barMocker = ((Mockery) fooMockery).method("bar");
		Random random = new Random();
		barMocker.setResponse(a -> random.nextInt());
		DependencyInjectionTest test = new DependencyInjectionTest(fooMockery);
		test.baz();
	}

}
