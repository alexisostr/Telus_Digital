package frontend;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

public class BuyTwoItemsTest extends BaseClass {
	
	@Test
	public void test2_buy_two_items() throws InterruptedException {
		//Login to inventory page
		String url = "https://www.saucedemo.com/";
		driver.get(url);
		WebElement email = driver.findElement(By.id("user-name"));
		email.clear();
		email.sendKeys("standard_user");
		WebElement password = driver.findElement(By.id("password"));
		password.clear();
		password.sendKeys("secret_sauce");
		driver.findElement(By.id("login-button")).click();
		// Select Three Items
		Thread.sleep(2000);
		driver.findElement(By.cssSelector("button[id='add-to-cart-sauce-labs-backpack']")).click();		
		driver.findElement(By.id("add-to-cart-sauce-labs-bolt-t-shirt")).click();
		driver.findElement(By.id("add-to-cart-sauce-labs-bike-light")).click();
		// Go to Cart Containers
		driver.findElement(By.id("shopping_cart_container")).click();	
		// Remove one item
		driver.findElement(By.id("remove-sauce-labs-backpack")).click();			
		//Buy two items
		driver.findElement(By.id("checkout")).click();
		driver.findElement(By.id("first-name")).sendKeys("Telus");
		driver.findElement(By.id("last-name")).sendKeys("Digital");
		driver.findElement(By.id("postal-code")).sendKeys("L4J 9N0");
		driver.findElement(By.id("continue")).click();
		driver.findElement(By.id("finish")).click();
		//Verify that order completed successfully
		Assert.assertTrue(driver.findElement(By.id("checkout_complete_container")).isDisplayed() );
	}
	
	
	

}
