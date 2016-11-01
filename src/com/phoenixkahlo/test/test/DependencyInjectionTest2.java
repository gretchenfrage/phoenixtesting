package com.phoenixkahlo.test.test;

import com.phoenixkahlo.test.MethodMocker;
import com.phoenixkahlo.test.Mockery;
import com.phoenixkahlo.test.Test;
import com.phoenixkahlo.test.Testing;

public class DependencyInjectionTest2 {

	public static interface Foo {
		
		void bar(String arg);
		
	}
	
	private Foo foo;
	
	public DependencyInjectionTest2(Foo foo) {
		this.foo = foo;
	}
	
	public void baz() {
		foo.bar("hello");
		foo.bar("world");
	}
	
	public static void main(String[] a) {
		Testing.test(DependencyInjectionTest2.class);
	}
	
	@Test(name = "dependency injection test 2")
	public static void test() {
		Foo foo = Testing.mock(Foo.class);
		MethodMocker barMocker = ((Mockery) foo).method("bar", String.class);
		barMocker.queueAssert(args -> args[0].equals("hello"));
		barMocker.queueAssert(args -> args[0].equals("world"));
		DependencyInjectionTest2 test = new DependencyInjectionTest2(foo);
		test.baz();
	}
	
}
