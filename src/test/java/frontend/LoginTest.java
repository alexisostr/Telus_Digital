package frontend;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginTest extends BaseClass {

	@Test
	public void test1_login_with_valid_credensials() {
		// Login to Inventory page
		String url = "https://www.saucedemo.com/";
		driver.get(url);
		WebElement email = driver.findElement(By.id("user-name"));
		email.clear();
		email.sendKeys("standard_user");
		WebElement password = driver.findElement(By.id("password"));
		password.clear();
		password.sendKeys("secret_sauce");
		driver.findElement(By.id("login-button")).click();
		// Verify that Inventory page is displayed
		Assert.assertEquals("https://www.saucedemo.com/inventory.html", driver.getCurrentUrl());

	}
	
	
}
