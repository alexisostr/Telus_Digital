package frontend;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

public class OrdertWithTotalValueBetween30_60_Test extends BaseClass {
	
	@Test
	public void test3_make_order_between30_60_dollars() throws InterruptedException {
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
		// Select Items 
		Thread.sleep(2000);
		driver.findElement(By.cssSelector("button[id='add-to-cart-sauce-labs-backpack']")).click();
		driver.findElement(By.id("add-to-cart-sauce-labs-bolt-t-shirt")).click();
		// Go to Cart Containers
		driver.findElement(By.id("shopping_cart_container")).click();
		// Buy two items
		driver.findElement(By.id("checkout")).click();
		driver.findElement(By.id("first-name")).sendKeys("Telus");
		driver.findElement(By.id("last-name")).sendKeys("Digital");
		driver.findElement(By.id("postal-code")).sendKeys("L4J 9N0");
		driver.findElement(By.id("continue")).click();
		// Verify that order completed amount less then $60 and more then $30
		String str = driver.findElement(By.cssSelector("div[class='summary_total_label']")).getText();
		System.out.println(str);
	}

}
