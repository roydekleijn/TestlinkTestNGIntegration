package org.roydekleijn.integration.testlink;

import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;

import org.testng.IMethodInstance;
import org.testng.IMethodInterceptor;
import org.testng.ITestContext;
import org.testng.annotations.Test;

public class TestNameInterceptor implements IMethodInterceptor {

	public List<IMethodInstance> intercept(List<IMethodInstance> methods,
			ITestContext context) {

		List<IMethodInstance> result = new ArrayList<IMethodInstance>();
		try {
			List<Integer> testnamesFromTL = getTestnamesFromTestlink(8);
			for (IMethodInstance m : methods) {
				Test test = m.getMethod().getConstructorOrMethod().getMethod()
						.getAnnotation(Test.class);
				if (testnamesFromTL.contains(Integer.parseInt(test.testName()))) {
					result.add(m);
				}
			}

		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		return result;
	}

	private List<Integer> getTestnamesFromTestlink(int testPlanId)
			throws MalformedURLException {
		TestlinkIntegration tl = new TestlinkIntegration();
		return tl.getTestcaseByTestplanId(testPlanId);
	}
}