package com.phoenixkahlo.test;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

/**
 * Static utility class for testing.
 */
public class Testing {

	private Testing() {
	}

	/**
	 * Execute all {@link com.phoenixkahlo.test.Test tests} in the given class.
	 */
	public static void test(Class<?> clazz) {
		Arrays.stream(clazz.getMethods()).filter(method -> method.isAnnotationPresent(Test.class)).forEach(method -> {
			System.out.println("running " + method.getName());
			try {
				method.invoke(null);
				System.out.println("test returned");
			} catch (Throwable e) {
				System.out.println("test failed with exception: " + e);
			}
		});
	}

	/**
	 * Create a {@link com.phoenixkahlo.test.Mockery Mockery} of the given
	 * interface.
	 */
	@SuppressWarnings("unchecked")
	public static <E> E mock(Class<E> intrface) {
		Map<Method, MethodMocker> mockers = new HashMap<>();
		for (Method method : intrface.getMethods()) {
			mockers.put(method, new MethodMocker());
		}
		InvocationHandler handler = (proxy, method, args) -> {
			if (method.equals(Mockery.class.getMethod("method", String.class, Class[].class))) {
				for (Map.Entry<Method, MethodMocker> entry : mockers.entrySet()) {
					if (entry.getKey().getName().equals(args[0])
							&& Arrays.equals(entry.getKey().getParameterTypes(), (Object[]) args[1])) {
						return entry.getValue();
					}
				}
				throw new NoSuchElementException();
			} else {
				return mockers.get(method).handle(args);
			}
		};
		return (E) Proxy.newProxyInstance(Testing.class.getClassLoader(), new Class<?>[] { intrface, Mockery.class },
				handler);
	}

}
