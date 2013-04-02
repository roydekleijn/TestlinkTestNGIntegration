package org.testng.listeners;

import java.net.MalformedURLException;

import org.roydekleijn.integration.testlink.TestlinkIntegration;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.annotations.Test;

import br.eti.kinoshita.testlinkjavaapi.constants.ExecutionStatus;
import br.eti.kinoshita.testlinkjavaapi.util.TestLinkAPIException;

public class TestMethodListener implements ITestListener {

	public void onTestStart(ITestResult result) {

	}

	public void onTestSuccess(ITestResult result) {
		TestlinkIntegration tl = new TestlinkIntegration();
		try {
			tl.setResult(result.getMethod().getConstructorOrMethod()
					.getMethod().getAnnotation(Test.class).testName(),
					ExecutionStatus.PASSED);
		} catch (TestLinkAPIException e) {
			e.printStackTrace();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}

	}

	public void onTestFailure(ITestResult result) {
		TestlinkIntegration tl = new TestlinkIntegration();
		try {
			tl.setResult(result.getMethod().getConstructorOrMethod()
					.getMethod().getAnnotation(Test.class).testName(),
					ExecutionStatus.FAILED);
		} catch (TestLinkAPIException e) {
			e.printStackTrace();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
	}

	public void onTestSkipped(ITestResult result) {
		TestlinkIntegration tl = new TestlinkIntegration();
		try {
			tl.setResult(result.getMethod().getConstructorOrMethod()
					.getMethod().getAnnotation(Test.class).testName(),
					ExecutionStatus.NOT_RUN);
		} catch (TestLinkAPIException e) {
			e.printStackTrace();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}

	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {

	}

	public void onStart(ITestContext context) {

	}

	public void onFinish(ITestContext context) {

	}

}
