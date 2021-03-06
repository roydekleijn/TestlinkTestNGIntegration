package org.roydekleijn.integration.testlink;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import br.eti.kinoshita.testlinkjavaapi.TestLinkAPI;
import br.eti.kinoshita.testlinkjavaapi.constants.ExecutionStatus;
import br.eti.kinoshita.testlinkjavaapi.constants.ExecutionType;
import br.eti.kinoshita.testlinkjavaapi.constants.TestCaseDetails;
import br.eti.kinoshita.testlinkjavaapi.model.TestCase;
import br.eti.kinoshita.testlinkjavaapi.util.TestLinkAPIException;

public class TestlinkIntegration {
  private final static String url = "http://localhost:8080/testlink/lib/api/xmlrpc/v1/xmlrpc.php";
  private final static String devKey = "14ebeb279cb712655c53daadef839321";

  public TestlinkIntegration() {

  }

  public ArrayList<Integer> getTestcaseByTestplanId(
      int testPlanID) throws TestLinkAPIException,
      MalformedURLException {
    TestLinkAPI testlinkAPIClient = new TestLinkAPI(
        new URL(url), devKey);
    TestCase[] testcases = testlinkAPIClient
        .getTestCasesForTestPlan(testPlanID, null, null, null,
            null, null, null, null, ExecutionType.AUTOMATED,
            true, TestCaseDetails.FULL);
    ArrayList<Integer> testcaseNames = new ArrayList<Integer>();
    for (TestCase testCase : testcases) {
      TestCase finalTestcase = testlinkAPIClient.getTestCase(
          testCase.getId(), null, null);
      testcaseNames.add(finalTestcase.getId());
    }
    return testcaseNames;
  }

  public Integer getTestcaseIDByName(String testcaseName,
      String projectName) throws TestLinkAPIException,
      MalformedURLException {
    TestLinkAPI testlinkAPIClient = new TestLinkAPI(
        new URL(url), devKey);
    return testlinkAPIClient.getTestCaseIDByName(testcaseName,
        null, projectName, null);
  }

  public void setResult(String testcaseId, ExecutionStatus status)
      throws TestLinkAPIException, MalformedURLException {
    TestLinkAPI testlinkAPIClient = new TestLinkAPI(
        new URL(url), devKey);
    testlinkAPIClient.setTestCaseExecutionResult(Integer
        .parseInt(testcaseId), null, 3, status, null, null,
        null, true, null, null, null, null, false);
  }

}
