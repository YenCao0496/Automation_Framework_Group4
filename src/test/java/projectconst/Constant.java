package projectconst;

import java.io.File;

import org.openqa.selenium.WebDriver;

public class Constant {
	public static String URI = "https://demoqa.com/";
	public static WebDriver WEBDRIVER;
	public static String URL = "https://demoqa.com/books";
	public static String USERNAME = "mienbui";
	public static String PASSWORD = "Mien@12345";
	public static String BOOK_GitPocketGuide = "Git Pocket Guide";
	public static String reportPath = System.getProperty("user.dir") + File.separator +"Result"+File.separator + "TestExecutionReport.html";
	public static String captureImageFolder = System.getProperty("user.dir") + File.separator +"Result";

}
