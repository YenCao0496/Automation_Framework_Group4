package core.element;

import java.util.List;
import java.util.Set;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Element {

	public static Element getElementObject() {
		return new Element();
	}

	protected WebElement _webElement;
	protected List<WebElement> webElements;
	protected By by;
	protected WebDriver driver;

	public Element() {
		webElements = null;
	}

	public Element(By by) {
		this.by = by;
	}

	public Element(WebElement webElement) {
		this._webElement = webElement;
	}

	public Element(String xpathLocator) {
		by = By.xpath(xpathLocator);
	}

	public void openPageUrl(String pageUrl) {
		driver.get(pageUrl);
	}

	public String getPageTitle() {
		return driver.getTitle();
	}

	public String getPageUrl() {
		return driver.getCurrentUrl();
	}

	public String getPageSourceCode() {
		return driver.getPageSource();
	}

	public void backToPage() {
		driver.navigate().back();
	}

	public void forwardToPage() {
		driver.navigate().forward();
	}

	public void refreshToPage() {
		driver.navigate().refresh();
	}

	public Alert waitForAlertPresence(int longTimeout) {
		WebDriverWait explicitwait = new WebDriverWait(driver, longTimeout);
		return explicitwait.until(ExpectedConditions.alertIsPresent());
	}

	public void acceptAlert(int longTimeout) {
		waitForAlertPresence(longTimeout);
		driver.switchTo().alert().accept();
	}

	public void cancelAlert(int longTimeout) {
		waitForAlertPresence(longTimeout).dismiss();
	}

	public String getTextAlert(int longTimeout) {
		return waitForAlertPresence(longTimeout).getText();
	}

	public void sendkeyToAlert(int longTimeout, String textValue) {
		waitForAlertPresence(longTimeout).sendKeys(textValue);
	}

	public void switchToWindowByID(String windowID) {
		Set<String> allItemIDs = driver.getWindowHandles();
		for (String id : allItemIDs) {
			if (!id.equals(windowID)) {
				driver.switchTo().window(id);
				break;
			}
		}
	}

	public void switchToWindowByTitle(String tabTitle) {
		Set<String> allItemIDs = driver.getWindowHandles();
		for (String id : allItemIDs) {
			if (!id.equals(id)) {
				driver.switchTo().window(id);
				String actualTitle = driver.getTitle();
				if (actualTitle.equals(tabTitle)) {
					break;
				}
			}
		}
	}

	public void closeAllTabWithoutParent(String parentID) {
		Set<String> allItemIDs = driver.getWindowHandles();
		for (String id : allItemIDs) {
			if (!id.equals(parentID)) {
				driver.switchTo().window(id);
				driver.close();
			}
			driver.switchTo().window(parentID);
		}

	}

	public void clickToElement() {
		_webElement.click();
	}

	public List<WebElement> getListWebElement() {
		return driver.findElements(by);
	}

	public void sendkeyToElement(String textValue) {
		_webElement.clear();
		_webElement.sendKeys(textValue);
	}

	public String getElementText() {
		return _webElement.getText();
	}

	public void selectItemInDefaultDropdown(String textItem) {
		Select select = new Select(_webElement);
		select.selectByValue(textItem);
	}

	public String getSelectedItemDefaultDropdown() {
		Select select = new Select(_webElement);
		return select.getFirstSelectedOption().getText();
	}

	public boolean isDropdownMultiple() {
		Select select = new Select(_webElement);
		return select.isMultiple();
	}

	public void selectItemInCustomDropdown(String expectedTextItem,int longTimeout) {
		_webElement.click();
		WebDriverWait explicit = new WebDriverWait(driver, longTimeout);
		List<WebElement> allItems = explicit.until(ExpectedConditions.presenceOfAllElementsLocatedBy(by));
		for (WebElement item : allItems) {
			if (item.getText().trim().equals(expectedTextItem)) {
				JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
				jsExecutor.executeScript("arguments[0].scrollIntoView(true);", item);
				item.click();
				break;
			}

		}
	}

	public void sleepInSecond(long time) {
		try {
			Thread.sleep(time * 1000);

		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public String getElementAttribute(String attributeName) {
		return _webElement.getAttribute(attributeName);
	}

	public String getElementCssValue(String propertyName) {
		return _webElement.getCssValue(propertyName);

	}

	public String getHexaColorFromRGBA(String rgbaValue) {
		return Color.fromString(rgbaValue).asHex();
	}

	public int GetElementSize() {
		return getListWebElement().size();
	}

	public void checkToDefaultCheckboxRadio() {
		
		if (!_webElement.isSelected()) {
			_webElement.click();
		}
	}

	public void uncheckToDefaultCheckboxRadio() {
		if (_webElement.isSelected()) {
			_webElement.click();
		}
	}

	public boolean isElementDisplayed() {
		return _webElement.isDisplayed();
	}

	public boolean isElementEnabled() {
		return _webElement.isEnabled();
	}

	public boolean isElementSelected() {
		return _webElement.isSelected();
	}

	public void switchToDefaultContent(WebDriver driver) {
		driver.switchTo().defaultContent();
	}

	public void switchToFrameIframe() {
		driver.switchTo().frame(_webElement);
	}

	public void hoverMouseToElement() {
		Actions action = new Actions(driver);
		action.moveToElement(_webElement).perform();

	}

	public void scrollToBottomPage() {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("window.scrollBy(0,document.body.scrollHeight)");
	}

	public void highlightElement(WebDriver driver, String locator) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		WebElement element = _webElement;
		String originalStyle = element.getAttribute("style");
		jsExecutor.executeScript("arguments[0].setAttribute(arguments[1], arguments[2])", element, "style",
				"border: 2px solid red; border-style: dashed;");
		sleepInSecond(1);
		jsExecutor.executeScript("arguments[0].setAttribute(arguments[1], arguments[2])", element, "style",
				originalStyle);
	}

	public void clickToElementByJS(WebDriver driver, String locator) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("arguments[0].click();", _webElement);
	}

	public void scrollToElement(WebDriver driver, String locator) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("arguments[0].scrollIntoView(true);", _webElement);
	}

	public void removeAttributeInDOM(WebDriver driver, String locator, String attributeRemove) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("arguments[0].removeAttribute('" + attributeRemove + "');",
				_webElement);
	}

	public String getElementValidationMessage(WebDriver driver, String locator) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		return (String) jsExecutor.executeScript("return arguments[0].validationMessage;",
				_webElement);
	}

	public void waitForElementVisible(int longTimeout) {
		WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(by));
	}

	public void waitForAllElementVisible(int longTimeout) {
		WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
		explicitWait.until(ExpectedConditions.visibilityOfAllElements((webElements)));
	}

	public void waitForElementInvisible(int longTimeout) {
		WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(by));
	}

	public void waitForAllElementInvisible(int longTimeout) {
		WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
		explicitWait.until(ExpectedConditions.invisibilityOfAllElements(webElements));
	}

	public void waitForElementClickable(int longTimeout) {
		WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
		explicitWait.until(ExpectedConditions.elementToBeClickable(by));
	}



}
