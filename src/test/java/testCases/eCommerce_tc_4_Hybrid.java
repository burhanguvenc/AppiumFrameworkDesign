package testCases;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pageObjects.android.CartPage;
import pageObjects.android.FormPage;
import pageObjects.android.ProductCatalogue;
import testUtils.AndroidBaseTest;

public class eCommerce_tc_4_Hybrid extends AndroidBaseTest {
	FormPage formPage;

	@Test(dataProvider = "getData", groups= {"Smoke"})
	public void validatingCartPriceSum(HashMap<String, String> input) throws InterruptedException {

		formPage = new FormPage(driver);

		formPage.setNameField(input.get("name"));
		formPage.setGender(input.get("gender"));
		formPage.setCountrySelection(input.get("country"));
		formPage.submitForm();

		ProductCatalogue productCatalogue = new ProductCatalogue(driver);

		productCatalogue.addItemToCartByIndex(0);
		productCatalogue.addItemToCartByIndex(0);
		productCatalogue.goToCartPage();

		CartPage cartPage = new CartPage(driver);
		cartPage.getProductList();
		double totalSum = cartPage.getProductSum();
		double displayFormattedSum = cartPage.getTotalAmountDisplayed();
		Assert.assertEquals(totalSum, displayFormattedSum);

		cartPage.aceeptTermsConditions();
		cartPage.submitOrder();
	}

	  @BeforeMethod (alwaysRun=true) 
	  public void preSetup()  
	  { // Screen to homepage
	  formPage.setActivity(); 
	  }
	 
	@DataProvider
	public Object[][] getData() throws IOException {
		List<HashMap<String, String>> data = getJsonData(
				"C:\\Users\\burha\\eclipse-workspace\\AppiumFrameworkDesign\\src\\test\\java\\testData\\eCommerce.json");
		// return new Object[][] { {"Rebo Slatke","male","Argentina"},{"Timo
		// Kakosik","female","Turkey"} };
		return new Object[][] { { data.get(0) }, { data.get(1) } };
	}
}
