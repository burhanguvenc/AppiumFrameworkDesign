package org.rebotraining;

import org.rebotraining.pageObjects.android.CartPage;
import org.rebotraining.pageObjects.android.FormPage;
import org.rebotraining.pageObjects.android.ProductCatalogue;
import org.testng.Assert;
import org.testng.annotations.Test;


public class eCommerce_tc_4_Hybrid extends BaseTest {

	@Test
	public void validatingCartPriceSum() throws InterruptedException {

		FormPage formPage = new FormPage(driver);
		
		formPage.setNameField("Rebo Slatke");
		formPage.setGender("female");
		formPage.setCountrySelection("Argentina");
		formPage.submitForm();
		
		ProductCatalogue productCatalogue = new ProductCatalogue(driver);
		
		productCatalogue.addItemToCartByIndex(0);
		productCatalogue.addItemToCartByIndex(0);
		productCatalogue.goToCartPage();
		
		//Explicit Wait for Cart Page
		/*
		 * WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(5));
		 * wait.until(ExpectedConditions.attributeContains(driver.findElement(By.id(
		 * "com.androidsample.generalstore:id/toolbar_title")), "text", "Cart"));
		 */
		
		CartPage cartPage = new CartPage(driver);
		cartPage.getProductList();
		double totalSum = cartPage.getProductSum();
		double displayFormattedSum = cartPage.getTotalAmountDisplayed();		
		Assert.assertEquals(totalSum, displayFormattedSum);
		
		cartPage.aceeptTermsConditions();
		cartPage.submitOrder();


		
	}
}
