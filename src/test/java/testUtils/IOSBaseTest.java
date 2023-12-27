package testUtils;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.options.XCUITestOptions;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import utils.AppiumUtils;

public class IOSBaseTest extends AppiumUtils{

	public IOSDriver driver;
	public AppiumDriverLocalService service;

	@BeforeClass
	public void ConfigureAppium() throws IOException {

		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream(System.getProperty("index.dir")+"\\src\\main\\java\\resources\\data.properties");
		
		prop.load(fis);
		String ipAddress = prop.getProperty("ipAddress");
		String port = prop.getProperty("port");
		String deviceName = prop.getProperty("DeviceName");
		
		service = startAppiumServer(ipAddress, Integer.parseInt(port));

		XCUITestOptions options = new XCUITestOptions();
		options.setDeviceName(deviceName);

		options.setApp(System.getProperty("index.dir")+"\\src\\test\\java\\appresources\\UIKitCatalog.app");
		// options.setApp(System.getProperty("index.dir")+"\\src\\test\\java\\appresources\\TestApp 3.app");

		options.setPlatformVersion("17.0");

		// Appium- Webdriver Agent -> IOS Apps.
		options.setWdaLaunchTimeout(Duration.ofSeconds(20));

		driver = new IOSDriver(service.getUrl(), options);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	}

	@AfterClass
	public void tearDown() {
		driver.quit();
		service.stop();
	}

}
