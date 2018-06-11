package com.weborder;

import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;

public class Order {

	public static void main(String[] args) throws InterruptedException {
		Random randomNumber = new Random();

		System.setProperty("webdriver.chrome.driver",
				"C:/Users/obeyd/Documents/selenium dependencies/drivers/ChromeDriver.exe");
		// It tells compiler where to find the chrome driver

		WebDriver driver = new ChromeDriver(); // Opens chrome web-browser

		driver.get("http://secure.smartbearsoftware.com/samples/TestComplete12/WebOrders/Login.aspx");
		// it goes to the mentioned URL
		Thread.sleep(1000);
		driver.findElement(By.name("ctl00$MainContent$username")).sendKeys("Tester");
		Thread.sleep(1000);
		driver.findElement(By.id("ctl00_MainContent_password")).sendKeys("test");
		Thread.sleep(1000);
		driver.findElement(By.name("ctl00$MainContent$login_button")).click();
		Thread.sleep(1000);
		driver.findElement(By.cssSelector("a[href='Process.aspx']")).click(); // "tagName[attributeName='valueOfAttribute']
		Thread.sleep(1000);
		driver.findElement(By.name("ctl00$MainContent$fmwOrder$txtQuantity")).click();
		Actions action = new Actions(driver);
		action.doubleClick().perform();
		Thread.sleep(1000);

		String quantity = randomNumber.nextInt(100) + 1 + "";
		driver.findElement(By.name("ctl00$MainContent$fmwOrder$txtQuantity")).sendKeys(quantity);
		Thread.sleep(1000);
		String name = new String();
		name += "John ";

		for (int i = 0; i < 5; i++) {
			char temp = (char) (randomNumber.nextInt(26) + 97);
			name += temp;
		}
		name += " Smith";

		driver.findElement(By.name("ctl00$MainContent$fmwOrder$txtName")).sendKeys(name);
		Thread.sleep(1000);
		driver.findElement(By.name("ctl00$MainContent$fmwOrder$TextBox2")).sendKeys("123 Any st");
		Thread.sleep(1000);
		driver.findElement(By.name("ctl00$MainContent$fmwOrder$TextBox3")).sendKeys("Anytown");
		Thread.sleep(1000);
		driver.findElement(By.name("ctl00$MainContent$fmwOrder$TextBox4")).sendKeys("Virginia");

		String zipCode = randomNumber.nextInt(90000) + 10000 + "";
		driver.findElement(By.name("ctl00$MainContent$fmwOrder$TextBox5")).sendKeys(zipCode);
		Thread.sleep(1000);
		int Luck=randomNumber.nextInt(3);
		String CardNumber=new String();
		
		switch(Luck) {
		case 0:
			driver.findElement(By.id("ctl00_MainContent_fmwOrder_cardList_0")).click(); // Visa
			CardNumber= "4"+(long)(Math.random()*1000000000000000L)+"";
			driver.findElement(By.name("ctl00$MainContent$fmwOrder$TextBox6")).sendKeys(CardNumber);
			break;
		case 1:
			driver.findElement(By.id("ctl00_MainContent_fmwOrder_cardList_1")).click();  //Master 
			CardNumber= "5"+(long)( Math.random()*1000000000000000L)+"";
			driver.findElement(By.name("ctl00$MainContent$fmwOrder$TextBox6")).sendKeys(CardNumber);
			break;
		case 2:
			driver.findElement(By.id("ctl00_MainContent_fmwOrder_cardList_2")).click(); //American
			CardNumber= "3"+(long)(Math.random()*100000000000000L)+"";
			driver.findElement(By.name("ctl00$MainContent$fmwOrder$TextBox6")).sendKeys(CardNumber);
			break;
		}
		
		int temp=randomNumber.nextInt(2);
		String randomMonth= temp==1 ? temp+""+randomNumber.nextInt(3): temp+""+randomNumber.nextInt(10);

	    driver.findElement(By.name("ctl00$MainContent$fmwOrder$TextBox1")).sendKeys(randomMonth+"/"+randomNumber.nextInt(82)+18);
        Thread.sleep(2000);
        driver.findElement(By.id("ctl00_MainContent_fmwOrder_InsertButton")).click();
        
        
        String expected = "New order has been successfully added";
        
        
        if (driver.findElement(By.xpath("//*[@id=\"ctl00_MainContent_fmwOrder\"]/tbody/tr/td/div/strong")).getText().contains(expected)) {
            System.out.println("pass");
            
        }else {
            System.out.println("fail");
            System.out.println("Expected:\t" + expected);
            System.out.println("Actual:\t" + driver.findElement(By.xpath("[@id=\"ctl00_MainContent_fmwOrder\"]/tbody/tr/td/div/strong")).getText());
            
        }
		
        driver.close();
	}
}
