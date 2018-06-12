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
		driver.findElement(By.name("ctl00$MainContent$username")).sendKeys("Tester");  // Enters UserName
		Thread.sleep(1000);
		driver.findElement(By.id("ctl00_MainContent_password")).sendKeys("test");  // Enters PassWord
		Thread.sleep(1000);
		driver.findElement(By.name("ctl00$MainContent$login_button")).click();  //  Clicks Login
		Thread.sleep(1000);
		//driver.findElement(By.cssSelector("a[href='Process.aspx']")).click(); // "tagName[attributeName='valueOfAttribute']  // we can use By.linkText(String)
		driver.findElement(By.linkText("Order")).click();  // Does the same thing as line 31
		Thread.sleep(1000);
		
		
		
	//	driver.findElement(By.name("ctl00$MainContent$fmwOrder$txtQuantity")).click(); 
//		Actions action = new Actions(driver);
//		action.doubleClick().perform();   // WE can also use .clear()
		Thread.sleep(1000);

		char backspace=8; // ascii code of Backspace
		String quantity = randomNumber.nextInt(100) + 1 + "";
		
		/*  Try this code to understand how backspace is working, ascii code for backspace is 8
		 * 	String a=new String();
		 *	a=a+ (char) 8; 
		 *	System.out.println(a);
		 */
		
		driver.findElement(By.name("ctl00$MainContent$fmwOrder$txtQuantity")).sendKeys(backspace+quantity);
		
		Thread.sleep(1000);
		String name = new String();
		name += "John ";

		for (int i = 0; i < 5; i++) {
			char temp = (char) (randomNumber.nextInt(26) + 97);  //return a random character from a to z
			name = name + temp; // saves it to string
		}
		name += " Smith";
		
		

		driver.findElement(By.name("ctl00$MainContent$fmwOrder$txtName")).sendKeys(name);
		Thread.sleep(1000);
		driver.findElement(By.name("ctl00$MainContent$fmwOrder$TextBox2")).sendKeys("123 Any st");
		Thread.sleep(1000);
		driver.findElement(By.name("ctl00$MainContent$fmwOrder$TextBox3")).sendKeys("Anytown");
		Thread.sleep(1000);
		driver.findElement(By.name("ctl00$MainContent$fmwOrder$TextBox4")).sendKeys("Virginia");

		String zipCode = randomNumber.nextInt(90000) + 10000 + "";      // generating a random 5 digit no 10000-99999
		driver.findElement(By.name("ctl00$MainContent$fmwOrder$TextBox5")).sendKeys(zipCode);
		Thread.sleep(1000);
		
		int Luck=randomNumber.nextInt(3);  //generates random number 0-2
		String CardNumber=new String();
		
		switch(Luck) {
		case 0:
			driver.findElement(By.id("ctl00_MainContent_fmwOrder_cardList_0")).click(); // Visa
			CardNumber= "4"+(long)(Math.random()*1000000000000000L)+"";        // "4" + (long)(double*long)---> "16 digit no starting with 4"
//			// Visa
//			String sixteenDigitNumber = "4"
//			for(int i=0; i<15; i++){
//			sixteenDigitNumber += randomNumber.nextInt(10);}
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
		
		int temp=randomNumber.nextInt(2); // Generates the left digit of month i.e. 0 or 1  
		String randomMonth= temp==1 ? temp+""+randomNumber.nextInt(3): temp+""+(randomNumber.nextInt(9)+1);
		/* if temp==1 is true, left side of ':' is run Otherwise right side of ':' is run 
		 * if left digit is 1 we can add 0,1, or 2 to right digit i.e.  10,11,12
		 * if left digit is 0 we can add any number 1-9 i.e.  01,02,03,04,05,06,07,08,or 09  
		 */

	    driver.findElement(By.name("ctl00$MainContent$fmwOrder$TextBox1")).sendKeys(randomMonth+"/"+randomNumber.nextInt(82)+18);   // 18 + random no from 0-81
        Thread.sleep(2000);
        driver.findElement(By.id("ctl00_MainContent_fmwOrder_InsertButton")).click();
        
        
        String expected = "New order has been successfully added.";
        
        
        if(driver.findElement(By.tagName("strong")).getText().contains(expected)){
            System.out.println("pass");
            
        }else {
            System.out.println("fail");
            System.out.println("Expected: " + expected);
           System.out.println("Actual: " +driver.findElement(By.tagName("strong")).getText());
        }    
            
// Does the same thing as above if else            
        
//       if (driver.findElement(By.xpath("//*[@id=\"ctl00_MainContent_fmwOrder\"]/tbody/tr/td/div/strong")).getText().contains(expected)) {
//        System.out.println("pass");
//        
//    }else {
//        System.out.println("fail");
//        System.out.println("Expected: " + expected);
//        System.out.println("Actual:\t" + driver.findElement(By.xpath("//*[@id=\"ctl00_MainContent_fmwOrder\"]/tbody/tr/td/div/strong")).getText());
//    }
        driver.close();
	}
}
