package core.report;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

public class Report {
	private static ExtentReports extent;
	private static ExtentHtmlReporter htmlReporter;
	private static ExtentTest test;

	public Report(String reportName) {
		// initialize ExtentReports and attach the HtmlReporter
		extent = ExtentManager.createInstance(reportName);
		// initialize the HtmlReporter
		htmlReporter = new ExtentHtmlReporter(reportName);
		// attach only HtmlReporter
		extent.attachReporter(htmlReporter);
	}

	public synchronized void createTestCaseName(String message) {
		test = extent.createTest(message);
	}

	public synchronized void logInfo(String message) {
		test = extent.createTest(message);
		test.log(Status.INFO, message);
	}

	public synchronized static void saveReport() {
		extent.flush();
	}

	public static String capture(WebDriver driver, String folderName) throws IOException {
		File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		File Dest = new File(folderName + System.currentTimeMillis() + ".png");
		String errflpath = Dest.getAbsolutePath();
		FileUtils.copyFile(scrFile, Dest);
		return errflpath;
	}
}
