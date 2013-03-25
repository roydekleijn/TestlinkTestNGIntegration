package exampleTest;

import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.rmi.RemoteException;

import javax.xml.rpc.ServiceException;

import org.junit.Assert;
import org.mantis.ta.RequiredItemException;
import org.roydekleijn.integration.testlink.TestNameInterceptor;
import org.roydekleijn.integration.testlink.TestlinkIntegration;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import br.eti.kinoshita.testlinkjavaapi.constants.ExecutionStatus;

/*
 * 
 * Install Testlink (latest stable version)
 * 
 * Configure an issue tracker per testproject via "Issue Tracker Management"
 * 
 * 
 */

@Listeners(value = TestNameInterceptor.class)
public class ExampleTests {

	@Test(testName = "3")
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

	@AfterMethod
	public void afterTest(Method test, ITestResult result)
			throws MalformedURLException {
		// System.out.println(result.isSuccess());
		System.out.println("TestNAME"
				+ test.getAnnotation(Test.class).testName());
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
