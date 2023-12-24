package org.rebotraining;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import com.google.common.collect.ImmutableMap;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;

public class BaseTest {

	public AndroidDriver driver;
	public AppiumDriverLocalService service;

	@BeforeClass
	public void ConfigureAppium() throws MalformedURLException {

		// Code to start server service
		
		  service = new AppiumServiceBuilder() .withAppiumJS(new File(
		  "C:\\Users\\burha\\AppData\\Roaming\\npm\\node_modules\\appium\\build\\lib\\main.js"
		  )) .withIPAddress("127.0.0.1").usingPort(4723).build(); service.start();
		 

		// Download App Under Test
		UiAutomator2Options options = new UiAutomator2Options();
		//options.setDeviceName("Rebophone");	
		options.setDeviceName("Xiaomi M2006C3MG API 29"); //real device
		options.setChromedriverExecutable("C:\\Users\\burha\\eclipse-workspace\\AppiumDemo\\chromedriver_win32\\chromedriver.exe");
		//options.setApp("C:\\Users\\burha\\eclipse-workspace\\AppiumDemo\\ApiDemos-debug.apk");
		options.setApp("C:\\Users\\burha\\eclipse-workspace\\AppiumDemo\\General-Store.apk");

		driver = new AndroidDriver(new URL("http://127.0.0.1:4723"), options);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
	}
	
	public void longPressAction(WebElement ele) {
		((JavascriptExecutor)driver).executeScript("mobile: longClickGesture", 
				ImmutableMap.of("elementId", ((RemoteWebElement)ele).getId(), "duration",2000));
	}

	
	public void scrollToEndAction() {
		boolean canScrollMore;
		do {
		 canScrollMore = (Boolean) ((JavascriptExecutor) driver).executeScript("mobile: scrollGesture", ImmutableMap.of(
			    "left", 100, "top", 100, "width", 200, "height", 200,
			    "direction", "down",
			    "percent", 3.0
			));
		}
		while(canScrollMore);
	}
	
	public void swipeAction(WebElement ele, String direction) {
		((JavascriptExecutor) driver).executeScript("mobile: swipeGesture", ImmutableMap.of(
			    "elementId", ((RemoteWebElement)ele).getId(),
			    "direction", direction,
			    "percent", 0.75
			));
	}
	
	public void dragAndDrop(WebElement ele,int x, int y) {
		((JavascriptExecutor) driver).executeScript("mobile: dragGesture", ImmutableMap.of(
			    "elementId", ((RemoteWebElement) ele).getId(),
			    "endX", x,
			    "endY", y
			));
	}
		
	@AfterClass
	public void tearDown() {
		driver.quit();
		service.stop();
	}

}
