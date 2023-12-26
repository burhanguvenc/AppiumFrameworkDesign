package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.ios.AlertViews;
import pageObjects.ios.HomePage;
import testUtils.IOSBaseTest;

public class IOSBasics extends IOSBaseTest {

	@Test
	public void IOSBasicsTest() {
		HomePage homePage = new HomePage(driver);
		homePage.selectAlertViews();

		AlertViews alertViews = new AlertViews(driver);
		alertViews.fillTextLabel("hello");
		String actualMessage = alertViews.getConfirmMessage();
		Assert.assertEquals(actualMessage, "A message should be a short, complete sentence.");


	}
}
