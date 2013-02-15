package filterOnTestName;

import java.net.MalformedURLException;
import java.net.URL;
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
			List<String> testnamesFromTL = getTestnamesFromTestlink(8);
			for (IMethodInstance m : methods) {
				Test test = m.getMethod().getConstructorOrMethod().getMethod()
						.getAnnotation(Test.class);
				if (testnamesFromTL.contains(test.testName())) {
					result.add(m);
				}
			}

		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	private List<String> getTestnamesFromTestlink(int testPlanId)
			throws MalformedURLException {
		TestlinkIntegration tl = new TestlinkIntegration(new URL(
				"http://localhost/testlink/lib/api/xmlrpc.php"),
				"9740f3efdd12a41e2c2a21bebabdc3b3");
		return tl.getTestcaseByTestplanId(testPlanId);
	}
}