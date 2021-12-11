package test;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.xml.DOMConfigurator;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import core.driver.WebDriverFactory;

import core.report.Report;

import projectconst.Constant;

public class BaseTest {
	WebDriver WEBDRIVER;
	WebDriverFactory webDriverFactory;
	Report report;

	@BeforeTest
	public void init() throws IOException {
		webDriverFactory = new WebDriverFactory();
		webDriverFactory.getDriver().manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
		webDriverFactory.getDriver().manage().timeouts().pageLoadTimeout(120, TimeUnit.SECONDS);
		webDriverFactory.getDriver().manage().timeouts().setScriptTimeout(60, TimeUnit.SECONDS);
		webDriverFactory.getDriver().manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
		webDriverFactory.getDriver().manage().window().maximize();
		// String browser = webDriverFactory.getDriver().
		report = new Report(Constant.reportPath + File.separator + "TestResult_" + webDriverFactory.getBrowser() + ".html");
		webDriverFactory.getDriver().get(Constant.URL);
		report.logInfo(webDriverFactory.getDriver(), Constant.captureImageFolder, "Start browser!");
		// config log
		DOMConfigurator.configure("log4j.xml");

	}

	@AfterTest
	public void afterMethod() {
		webDriverFactory.removeDriver();
		Report.saveReport();
	}

}
