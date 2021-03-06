package exampleTest;

import java.lang.reflect.Method;
import java.math.BigInteger;
import java.net.MalformedURLException;
import java.rmi.RemoteException;

import javax.xml.rpc.ServiceException;

import org.junit.Assert;
import org.roydekleijn.integration.testlink.TestlinkIntegration;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.testng.listeners.TestMethodListener;
import org.testng.listeners.TestNameInterceptor;

import biz.futureware.mantis.rpc.soap.client.IssueData;
import biz.futureware.mantis.rpc.soap.client.MantisConnectLocator;
import biz.futureware.mantis.rpc.soap.client.MantisConnectPortType;
import biz.futureware.mantis.rpc.soap.client.ObjectRef;
import br.eti.kinoshita.testlinkjavaapi.constants.ExecutionStatus;

@Listeners(value = { TestMethodListener.class,
    TestNameInterceptor.class })
public class ExampleTests {

  @Test(testName = "4")
  public void testcase1() {
    System.out.println("Testcase1");
    Assert.assertFalse(true);
  }

  @Test(testName = "6")
  public void testcase2() {
    System.out.println("Testcase2");
    Assert.assertFalse(true);
  }

  @Test(testName = "8")
  public void testcase3() {
    System.out.println("Testcase3");
    Assert.assertFalse(false);
  }

  @AfterMethod
  public void afterTest(Method test, ITestResult result)
      throws MalformedURLException, ServiceException,
      RemoteException {
    TestlinkIntegration tl = new TestlinkIntegration();
    if (result.isSuccess()) {

      tl.setResult(test.getAnnotation(Test.class).testName(),
          ExecutionStatus.PASSED);
    } else {
      tl.setResult(test.getAnnotation(Test.class).testName(),
          ExecutionStatus.FAILED);
/*
      // Add issue to mantis or other bugtracker
      MantisConnectLocator mcl = new MantisConnectLocator();

      mcl.setMantisConnectPortEndpointAddress("http://localhost/mantis/api/soap/mantisconnect.php");

      MantisConnectPortType mcpt = mcl.getMantisConnectPort();

      System.out.println(mcpt.mc_version());
      IssueData issue = new IssueData();
      issue.setSummary(test.getName());
      issue.setDescription("New Description");
      issue.setCategory("Automated Test");

      ObjectRef ref = new ObjectRef();
      ref.setId(BigInteger.valueOf(1));
      ref.setName("");

      issue.setProject(ref);

      mcpt.mc_issue_add("administrator", "root", issue);
*/
    }

  }
}
