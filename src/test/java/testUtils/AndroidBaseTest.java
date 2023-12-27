package testUtils;


import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import utils.AppiumUtils;

public class AndroidBaseTest extends AppiumUtils{

	public AndroidDriver driver;
	public AppiumDriverLocalService service;

	@BeforeClass(alwaysRun=true)
	public void ConfigureAppium() throws IOException {

		// Code to start server service
		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream("C:\\Users\\burha\\eclipse-workspace\\AppiumFrameworkDesign\\src\\main\\java\\resources\\data.properties");
		
		prop.load(fis);
		String ipAddress = prop.getProperty("ipAddress");
		String port = prop.getProperty("port");
		String deviceName = prop.getProperty("DeviceName");
		
		service = startAppiumServer(ipAddress, Integer.parseInt(port));

		// Download App Under Test
		UiAutomator2Options options = new UiAutomator2Options();
		options.setDeviceName(deviceName); 
		options.setChromedriverExecutable("C:\\Users\\burha\\eclipse-workspace\\AppiumFrameworkDesign\\src\\test\\java\\appresources\\chromedriver.exe");
		options.setApp("C:\\Users\\burha\\eclipse-workspace\\AppiumFrameworkDesign\\src\\test\\java\\appresources\\General-Store.apk");
		driver = new AndroidDriver(service.getUrl(), options);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
	}

	@AfterClass(alwaysRun=true)
	public void tearDown() {
		driver.quit();
		service.stop();
	}

}
