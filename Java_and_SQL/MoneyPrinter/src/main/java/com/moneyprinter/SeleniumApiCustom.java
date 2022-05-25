package com.moneyprinter;

import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import io.github.bonigarcia.wdm.WebDriverManager;

public class SeleniumApiCustom {

	String username;
	String password;
	boolean error = false;
	
	SeleniumApiCustom(String username, String password)
	{
		this.username = username;
		this.password = password;
	}
	
	void transfer(int amount)
	{
		System.out.println("Transfering $" + amount + ".");
		if(amount < 1) return;
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		
		//open on top monitor:
		driver.manage().window().setPosition(new Point(0, -1000));
		driver.manage().window().maximize();
		driver.get("https://app.alpaca.markets");
		driver.findElement(By.id("username")).sendKeys(this.username);
		driver.findElement(By.id("password")).sendKeys(this.password+"\n");
		
		wait.until(ExpectedConditions.urlToBe("https://app.alpaca.markets/brokerage/dashboard/overview"));
		driver.get("https://app.alpaca.markets/brokerage/banking");
		
		//I can make sure the parent element is a button as well.
		By transfer = By.xpath("//span[text() = 'Transfer Money']/..");
		wait.until(ExpectedConditions.elementToBeClickable(transfer));
		driver.findElement(transfer).click();
		
		By dollarEntry = By.name("dollars");
		wait.until(ExpectedConditions.visibilityOfElementLocated(dollarEntry));
		driver.findElement(dollarEntry).sendKeys(String.valueOf(amount));
		
		By submit = By.xpath("//span[text() = 'Submit']/..");
		wait.until(ExpectedConditions.elementToBeClickable(submit));
		driver.findElement(submit).click();
		
		By confirm = By.xpath("//span[text() = 'Confirm']/..");
		wait.until(ExpectedConditions.elementToBeClickable(confirm));
		driver.findElement(confirm).click();
		
		By confirmLoad = By.xpath("//span[text() = 'Sounds Good']/..");
		wait.until(ExpectedConditions.presenceOfElementLocated(confirmLoad));
		driver.quit();
	}
	
}
