package pageObjects.ios;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import utils.IOSActions;

public class AlertViews extends IOSActions {

	IOSDriver driver;
	//Grandparent (AppiumUtils) -> IOSActions -> AlertViews

	public AlertViews(IOSDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}

	@iOSXCUITFindBy(accessibility = "Alert Views")
	private WebElement alertViews;

	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeStaticText[`label =='Text Entry'`]")
	private WebElement textEntryMenu;

	@iOSXCUITFindBy(iOSNsPredicate = "type == 'XCUIElementTypeStaticText' AND value BEGINSWITH[c] 'Confirm'")
	private WebElement confirmMenuItem;

	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeCell")
	private WebElement textBox;

	@iOSXCUITFindBy(accessibility = "OK")
	private WebElement acceptPopUp;

	@iOSXCUITFindBy(iOSNsPredicate = "name BEGINSWITH[c] 'A message'")
	private WebElement confirmMessage;

	@iOSXCUITFindBy(iOSNsPredicate = "label == 'Confirm'")
	private WebElement submit;

	public void selectAlertViews() {
		alertViews.click();
	}

	public void fillTextLabel(String name) {
		textEntryMenu.click();
		textBox.sendKeys(name);
		acceptPopUp.click();
	}

	public String getConfirmMessage() {
		confirmMenuItem.click();
		return confirmMessage.getText();
	}

}
