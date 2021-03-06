package proxy.demo;

import proxy.MethodInvocation;

public class PerformancePrinterAdvice extends MethodInvocation {

	@Override
	public Object invoke(Class<?> owner, String method, Object... args) {
		long start = System.currentTimeMillis();
		Object obj = callOrigin(args);
		System.out.println(method + " performance : " + (System.currentTimeMillis() - start));
		return obj;
	}

}
