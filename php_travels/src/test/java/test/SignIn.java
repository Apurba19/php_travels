package test;

import java.util.Hashtable;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import base.Base_PHP;
import utilities.TestUtil;

public class SignIn extends Base_PHP {

	@Test(dataProviderClass = TestUtil.class, dataProvider = "dp")
	public void login(Hashtable<String, String> data) throws InterruptedException {

		click("MY_ACCOUNT");
		System.out.println("My account");
		Thread.sleep(1000);
		click("LOGIN");
		System.out.println("login");

		// Enter Email from excel sheet
		sendKeys("EMAIL", data.get("Email"));

		// Enter Password from excel sheet
		sendKeys("PASSWORD", data.get("Password"));

		click("LOGIN2");
		Thread.sleep(2000);
		
		boolean text = isDisplayed("WELCOME_TEXT");
		if(text==true) {
			Assert.assertEquals(text, true);
			log.debug("Login successfull");
		}
		
		Thread.sleep(2000);
	}
	
	
	@Test(description="Thumbnail verification")
	public void thumbnail() {
		boolean img = isDisplayed("THUMBNAIL");
		if(img==true) {
			Assert.assertEquals(img, true);
			log.debug("Thumbnail available");
		}
	}
	
	@Test
	public void menuBar() {
		List<WebElement> menuBar = driver.findElements(By.xpath(or.getProperty("MENU")));
		int menu = menuBar.size();
		log.debug("Menu has " + menu + " options");
		
		for(WebElement e: menuBar) {
			String S = e.getText();
			System.out.println(S);
		}
		Assert.assertEquals(4,menu);
	}
}
