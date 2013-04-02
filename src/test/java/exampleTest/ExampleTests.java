package exampleTest;

import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.rmi.RemoteException;

import javax.xml.rpc.ServiceException;

import org.junit.Assert;
import org.mantis.ta.RequiredItemException;
import org.roydekleijn.integration.testlink.TestlinkIntegration;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.testng.listeners.TestMethodListener;
import org.testng.listeners.TestNameInterceptor;

import br.eti.kinoshita.testlinkjavaapi.constants.ExecutionStatus;

@Listeners(value = { TestMethodListener.class, TestNameInterceptor.class })
public class ExampleTests {

	@Test(testName = "7")
	public void testcase1() throws MalformedURLException, RemoteException,
			ServiceException, RequiredItemException {
		System.out.println("Testcase1");
		Assert.assertFalse(true);
	}

	@Test(testName = "5")
	public void testcase2() {
		System.out.println("Testcase2");
		Assert.assertFalse(false);
	}

	@Test(testName = "9")
	public void testcase3() {
		System.out.println("Testcase3");
		Assert.assertFalse(false);
	}

	@AfterMethod
	public void afterTest(Method test, ITestResult result)
			throws MalformedURLException {
		TestlinkIntegration tl = new TestlinkIntegration();
		if (result.isSuccess()) {

			tl.setResult(test.getAnnotation(Test.class).testName(),
					ExecutionStatus.PASSED);
		} else {
			tl.setResult(test.getAnnotation(Test.class).testName(),
					ExecutionStatus.FAILED);
		}

	}
}
