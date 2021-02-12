package listeners;

import java.io.IOException;

import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import org.testng.Reporter;

import com.relevantcodes.extentreports.LogStatus;

import utilities.MonitoringMail;
import utilities.TestConfig;
import utilities.TestUtil;
import base.Base_PHP;

public class customListeners extends Base_PHP implements ITestListener, ISuiteListener {

	public String messageBody;

	public void onFinish(ITestContext arg0) {

	}

	public void onStart(ITestContext arg0) {

	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult arg0) {

	}

	// onTestFailure method property executes when @Test is failed in test cases
	public void onTestFailure(ITestResult arg0) {

		// In order to escape special characters (<, >, &, \\ etc)
		System.setProperty("org.uncommons.reportng.escape-output", "false");

		try {
			TestUtil.captureScreenshot();
		} catch (IOException e) {
			e.printStackTrace();
		}

		// etest.log is keeping the log for Extent Report log
		etest.log(LogStatus.FAIL, arg0.getName().toUpperCase() + " Failed with exception : " + arg0.getThrowable());
		etest.log(LogStatus.FAIL, etest.addScreenCapture(TestUtil.screenshotName));

		// log.debug is keeping the log for Application log
		log.debug("TestUtil.screenshotName=" + TestUtil.screenshotName);

		// Reporter.log is keeping the log for ReportNG Log
		Reporter.log("Click to see ");
		Reporter.log("<a target=\"_blank\" href=" + TestUtil.screenshotName + ">Failed Test</a>");
		Reporter.log("<br>");
		Reporter.log("<br>");

		// Configuring height and width of the screenshot image
		Reporter.log("<a target=\"_blank\" href=" + TestUtil.screenshotName + "><img src=" + TestUtil.screenshotName
				+ " height=200 width=200></img></a>");

		// Ending the test
		erep.endTest(etest);

		// Generating the report
		erep.flush();
	}

	public void onTestSkipped(ITestResult arg0) {

		etest.log(LogStatus.SKIP, arg0.getName().toUpperCase() + " Skipped the test as the Run mode is NO");
		erep.endTest(etest);
		erep.flush();

	}

	public void onTestStart(ITestResult arg0) {
		etest = erep.startTest(arg0.getName().toUpperCase());
		log.debug("Test Started");
	}

	// onTestSuccess method property executes when @Test is passed in test cases
	public void onTestSuccess(ITestResult arg0) {

		// In order to escape special characters (<, >, &, \\ etc)
		System.setProperty("org.uncommons.reportng.escape-output", "false");

		try {
			TestUtil.captureScreenshot();
		} catch (IOException e) {
			e.printStackTrace();
		}

		// etest.log is keeping the log for Extent Report log
		etest.log(LogStatus.PASS, arg0.getName().toUpperCase() + " HAS PASSED");

		// Reporter.log is keeping the log for ReportNG Log
		Reporter.log("Click to see ");
		Reporter.log("<a target=\"_blank\" href=" + TestUtil.screenshotName + ">Pass Test</a>");
		Reporter.log("<br>");
		Reporter.log("<br>");
		Reporter.log("<a target=\"_blank\" href=" + TestUtil.screenshotName + "><img src=" + TestUtil.screenshotName
				+ " height=200 width=200></img></a>");

		// Ending the test
		erep.endTest(etest);

		// Generating the report
		erep.flush();

	}

	public void onFinish(ISuite arg0) {
		/*
		 * MonitoringMail mail = new MonitoringMail();
		 * 
		 * try { messageBody = "http://" + InetAddress.getLocalHost().getHostAddress() +
		 * ":8080/job/DataDrivenLiveProject/Extent_Reports/"; } catch
		 * (UnknownHostException e) {
		 * 
		 * e.printStackTrace(); }
		 * 
		 * try { mail.sendMail(TestConfig.server, TestConfig.from, TestConfig.to,
		 * TestConfig.subject, messageBody); } catch (AddressException e) {
		 * e.printStackTrace(); } catch (MessagingException e) {
		 * 
		 * e.printStackTrace(); }
		 */
	}

	public void onStart(ISuite arg0) {

	}

}
