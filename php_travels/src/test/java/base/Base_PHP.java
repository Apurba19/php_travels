package base;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;

import io.github.bonigarcia.wdm.WebDriverManager;
import utilities.ExcelReader;
import utilities.ExtentManager;

public class Base_PHP {
	public static String projectpath = System.getProperty("user.dir");
	public static ExcelReader excel = new ExcelReader(projectpath + "\\src\\test\\resources\\data\\php.xlsx");

	// In order to keep log in Application log
	public static Logger log = Logger.getLogger("devpinoyLogger");

	// public static FileInputStream or_location;
	public static FileInputStream fis;

	public static Properties or = new Properties();
	public static Properties config = new Properties();

	public static WebDriver driver;

	public static ExtentTest etest;
	public static ExtentReports erep = ExtentManager.getInstance();

	public static WebDriverWait wait;

	@BeforeSuite
	public static void setup() throws IOException, InterruptedException {

		System.setProperty("org.uncommons.reportng.escape-output", "false");

		// Properties file setup
		if (driver == null) {
			fis = new FileInputStream(projectpath + "\\src\\test\\resources\\properties\\Config.properties");
			config.load(fis);

			fis = new FileInputStream(projectpath + "\\src\\test\\resources\\properties\\OR.properties");
			or.load(fis);
		}

		// Web Browser setup
		if (config.getProperty("browser").equals("chrome")) {
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
		} else if (config.getProperty("browser").equals("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
		}

		// Navigating to baseUrl
		driver.get(config.getProperty("baseurl"));
		Thread.sleep(2000);
		// Documenting step
		log.debug("Navigating to " + config.getProperty("baseurl"));

	}// setup

	// Click method property
	public static void click(String locator) {
		driver.findElement(By.xpath(or.getProperty(locator))).click();
	}

	// sendKeys method property
	public static void sendKeys(String locator, String value) {
		driver.findElement(By.xpath(or.getProperty(locator))).sendKeys(value);
	}

	// isDisplayed method property
	public static boolean isDisplayed(String locator) {
		return driver.findElement(By.xpath(or.getProperty(locator))).isDisplayed();
	}
	
	public static void isClickable(String locator) throws InterruptedException {
		driver.findElement(By.xpath(or.getProperty(locator))).click();
		Thread.sleep(2000);
		driver.navigate().forward();
	}

	@AfterSuite
	public void tearDown() {
		if (driver != null) {
			driver.quit();
			log.debug("Test completed");
		}
	}


}// class
