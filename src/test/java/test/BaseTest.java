package test;

import java.util.concurrent.TimeUnit;

import org.apache.log4j.xml.DOMConfigurator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import com.aventstack.extentreports.ExtentTest;

import core.report.ExtentManager;
import core.report.Report;
import io.github.bonigarcia.wdm.WebDriverManager;
import projectconst.Constant;

public class BaseTest {
	WebDriver WEBDRIVER;
	Report report;
	@BeforeTest
	public void init() {
		WebDriverManager.chromedriver().browserVersion(null).setup();
		WEBDRIVER = new ChromeDriver();
		WEBDRIVER.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
		WEBDRIVER.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
		WEBDRIVER.manage().window().maximize();
		// report = new Report (Constant.reportPath);
		report = new Report(Constant.reportPath);
		report.logInfo("Start browser!");
		WEBDRIVER.get(Constant.URL);
		
		// config log
		DOMConfigurator.configure("log4j.xml");

	}

	@AfterTest
	public void afterMethod() {
		WEBDRIVER.quit();
		Report.saveReport();
	}

}
