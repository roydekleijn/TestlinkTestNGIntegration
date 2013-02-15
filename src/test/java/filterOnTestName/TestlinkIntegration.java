package filterOnTestName;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import br.eti.kinoshita.testlinkjavaapi.TestLinkAPI;
import br.eti.kinoshita.testlinkjavaapi.constants.ExecutionType;
import br.eti.kinoshita.testlinkjavaapi.constants.TestCaseDetails;
import br.eti.kinoshita.testlinkjavaapi.model.TestCase;

public class TestlinkIntegration {
	private URL url;
	private String devKey;

	public TestlinkIntegration(URL url, String devKey) {
		this.url = url;
		this.devKey = devKey;
	}

	public List<String> getTestcaseByTestplanId(int testPlanID) {
		TestLinkAPI testlinkAPIClient = new TestLinkAPI(this.url, this.devKey);
		TestCase[] testcases = testlinkAPIClient.getTestCasesForTestPlan(
				testPlanID, null, null, null, null, null, null, null,
				ExecutionType.AUTOMATED, true, TestCaseDetails.FULL);
		List<String> testcaseNames = new ArrayList<String>();
		for (TestCase testCase : testcases) {
			TestCase finalTestcase = testlinkAPIClient.getTestCase(
					testCase.getId(), null, null);
			testcaseNames.add(finalTestcase.getName());
		}
		return testcaseNames;
	}

	public Integer getTestcaseIDByName(String testcaseName, String projectName) {
		TestLinkAPI testlinkAPIClient = new TestLinkAPI(this.url, this.devKey);
		return testlinkAPIClient.getTestCaseIDByName(testcaseName, null,
				projectName, null);
	}
}
