package filterOnTestName;

import java.net.MalformedURLException;
import java.rmi.RemoteException;

import javax.xml.rpc.ServiceException;

import org.mantis.ta.MantisManager;
import org.mantis.ta.RequiredItemException;
import org.mantis.ta.beans.IssueData;
import org.mantis.ta.beans.ObjectRef;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

/*
 * 
 * Install Testlink (latest stable version)
 * Install Mantis (latest stable version)
 * 
 * Configure an issue tracker per testproject via "Issue Tracker Management"
 * 
 * 
 */

@Listeners(value = TestNameInterceptor.class)
public class BaseTests {
	public static final String DEV_KEY = "9740f3efdd12a41e2c2a21bebabdc3b3";
	// Substitute your Server URL Here
	public static final String SERVER_URL = "http://localhost/testlink/lib/api/xmlrpc.php";

	@Test(testName = "Login succesfully")
	public void testcase1() throws MalformedURLException, RemoteException,
			ServiceException, RequiredItemException {
		System.out.println("Testcase1");
		MantisManager mgr = new MantisManager(
				"http://localhost/mantisbt/api/soap/mantisconnect.php",
				"administrator", "root");
		// mgr.getProjectById(new BigInteger("1")).;

		ObjectRef prjRef = new ObjectRef(null, "automation project");

		IssueData issueToCreate = new IssueData();
		issueToCreate.setSummary("issue summary");
		issueToCreate.setDescription("some description text");
		issueToCreate.setProject(prjRef);

		mgr.createIssue(issueToCreate);
	}

	@Test(testName = "Login empty fields")
	public void testcase2() {
		System.out.println("Testcase2");
	}

	@AfterMethod
	public void afterTest(ITestResult result) throws MalformedURLException,
			RemoteException, ServiceException, RequiredItemException {
		System.out.println(result.isSuccess());
		System.out.println(result.getTestName());

		MantisManager mgr = new MantisManager(
				"http://localhost/mantisbt/api/soap/mantisconnect.php",
				"administrator", "root");
		// mgr.getProjectById(new BigInteger("1")).;

		ObjectRef prjRef = new ObjectRef(null, "automation project");

		IssueData issueToCreate = new IssueData();
		issueToCreate.setSummary("issue summary");
		issueToCreate.setDescription("some description text");
		issueToCreate.setProject(prjRef);

		mgr.createIssue(issueToCreate);

	}
}
