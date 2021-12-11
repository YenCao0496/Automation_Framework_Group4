package core.driver;

import java.util.ArrayList;
import java.util.Set;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriver.Options;

import core.log.Log;

public class DriverWrapper {
	 //protected static Logger logger = Logger.getLogger(DriverWrapper.class);
	static Log logger ;
	public static WebDriver getWebDriver( ) {
		WebDriverFactory driver = new WebDriverFactory();
		return driver.getDriver();
	}
	
	public static void acceptAlert() {
		getWebDriver().switchTo().alert().accept();
	}
	
	public static String getCurrentUrl() {
		logger.info("Get current url");
		//logger.info("Get current url ");
		String url = null;
		try {
			url = getWebDriver().getCurrentUrl();
		} catch (Exception e) {
			logger.info("Error: Cannot get current URL due to '" + e.getMessage() + "'");
		}
		return url;
	}
	
	public static String getTitle() {
		logger.info("Get title ");
		String title = null;
		try {
			title = getWebDriver().getTitle();
		} catch (Exception e) {
			logger.info("Error: Cannot get title due to '" + e.getMessage() + "'");
		}
		return title;
	}
	
	public static String getPageSource() {
		logger.info("Get title ");
		String pageSource = null;
		try {
			pageSource = getWebDriver().getPageSource();
		} catch (Exception e) {
			logger.info("Error: Cannot get page source due to '" + e.getMessage() + "'");
		}
		return pageSource;
	}
	
	public static void close() {
		logger.info("Close current page");
		try {
			getWebDriver().close();
		} catch (Exception e) {
			logger.info("Error: Cannot close current page due to '" + e.getMessage() + "'");
		}
	}
	
	public static void quit() {
		logger.info("Quit browser");
		try {
			getWebDriver().quit();
		} catch (Exception e) {
			logger.info("Error: Cannot quit due to '" + e.getMessage() + "'");
		}
	}
	
	public static String getWindowHandle() {
		try {
			return getWebDriver().getWindowHandle();
		} catch (Exception e) {
			logger.info("Error: Cannot get Window handle due to '" + e.getMessage() + "'");
			return null;
		}
	}
	
	public static ArrayList<String> getWindowHandles() {
		try {
			return new  ArrayList<String>(getWebDriver().getWindowHandles());
		} catch (Exception e) {
			logger.info("Error: Cannot get Window handle due to '" + e.getMessage() + "'");
			return null;
		}
	}
	
	public static void switchTo(String windowHandle) {
		getWebDriver().switchTo().window(windowHandle);
	}
	
	public static void switchWindow(String title) {
		String currentWindow = getWebDriver().getWindowHandle();
		Set<String> availableWindows = getWebDriver().getWindowHandles();
		
		if(!availableWindows.isEmpty()) {
			for (String windowId : availableWindows) {
				if (getWebDriver().switchTo().window(windowId).getTitle().equals(title)) {
					
				} else {
					getWebDriver().switchTo().window(currentWindow);
				}
			}
		}
	}
	
	public static void switchFrame(int index) {
		getWebDriver().switchTo().frame(index);
	}
	
	public static void switchFrame(String iframeName) {
		if (iframeName != "default") {
			getWebDriver().switchTo().frame(iframeName);
		} else {
			getWebDriver().switchTo().defaultContent();
		}
	}
	
	public static void switchParentFrame() {
		getWebDriver().switchTo().parentFrame();
	}
	
	public static Options manage() {
		try {
			return getWebDriver().manage();
		} catch (Exception e) {
			logger.info("Error: Cannot navigate due to '" + e.getMessage() + "'");
			return null;
		}
	}
	
	public static void maximize() {
		try {
			getWebDriver().manage().window().maximize();
		} catch (Exception e) {
			logger.info(e.getMessage());
		}
	}
}
