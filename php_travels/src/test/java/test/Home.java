package test;


import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import base.Base_PHP;

public class Home extends Base_PHP {
	
	@Test(priority=0,description="Headline verification")
	public void headline() {
		boolean headline = isDisplayed("HEADLINE");
		
		if(headline==true) {
			Assert.assertEquals(headline, true);
			log.debug("Headline is available");
		}
		else {
			System.out.println("No headline");
			log.debug("Could not find headline");
		}
	}
	
	@Test(priority=1,description="Logo verification")
	public void logo() {
		boolean logo = isDisplayed("LOGO");
		
		if(logo==true) {
			Assert.assertEquals(logo, true);
			log.debug("Logo is visible");
		}
		else {
			log.debug("Logo not visible");
		}
	}
	@Test(priority=2,description="Currency button and list verification")
	public void currencyButton() throws InterruptedException {
		click("USD");
		Thread.sleep(2000);
		List <WebElement> currencyList = driver.findElements(By.xpath(or.getProperty("CURRENCY")));
		log.debug("Available currency :" + currencyList.size());
		
		for(WebElement e: currencyList) {
			String List = e.getText();
			System.out.println(List);
		}
	}
	
	@Test(priority=3,description="Language button verification")
	public void language() {
		click("ENGLISH");
		List <WebElement> languageList = driver.findElements(By.xpath(or.getProperty("LANGUAGE")));
		log.debug("Total language " + languageList.size());
		
		for(WebElement e: languageList) {
			String List = e.getText();
			System.out.println(List);
		}
	}
	
	@Test(priority=4,description="My Account verification")
	public void myAccount() throws InterruptedException {
		click("MY_ACCOUNT");
		Thread.sleep(1000);
		click("MY_ACCOUNT");
	}
	
	@Test(priority=5)
	public void callNow() {
		boolean call= isDisplayed("CALL");
		if(call==true) {
			Assert.assertEquals(call, true);
		}
	}

}
