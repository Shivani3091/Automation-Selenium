package PracDemoApp;

import java.io.File;
import java.io.IOException;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.ui.Select;
import org.testng.Reporter;

public class EndToEndPracDemo {

	public static void main(String[] args) throws InterruptedException, IOException 
	{
		System.setProperty("webdriver.chrome.driver","C:\\Program Files\\Java\\chromedriver.exe");
		ChromeOptions co=new ChromeOptions();
		co.addArguments("--remote-allow-origins=*");
		WebDriver driver=new ChromeDriver(co);
		driver.get("https://katalon-demo-cura.herokuapp.com/#appointment");
		driver.manage().window().maximize();
		Thread.sleep(3000);
		
		//clicking on make appointment button
		driver.findElement(By.id("btn-make-appointment")).click();
		Thread.sleep(500);
		
		//entering username and password in field
		driver.findElement(By.id("txt-username")).sendKeys("John Doe");
		driver.findElement(By.xpath("//input[@type='password']")).sendKeys("ThisIsNotAPassword");
		driver.findElement(By.xpath("//button[@type='submit']")).click();
		Thread.sleep(1000);
		
		//Alert alt=driver.switchTo().alert();
		//alt.accept();
		//Thread.sleep(500);
		
	    // selecting from facility drop down
		WebElement center = driver.findElement(By.id("combo_facility"));
		Select s=new Select(center);
		s.selectByVisibleText("Seoul CURA Healthcare Center");
		Thread.sleep(500);
		
		//checking if radio button selected
		 WebElement radio = driver.findElement(By.xpath("(//input[@type='radio'])[3]"));
		 radio.click();
		 if(radio.isSelected())
			{
				System.out.println("radio button is selected");
			}
		 else
		 {
			 System.out.println("radio button is not selected");
		 }
		 Thread.sleep(500);
		 
		 
		 // scrolling to title and printing title
		 WebElement title = driver.findElement(By.xpath("//h1[text()='CURA Healthcare Service']"));
		 Actions act=new Actions(driver);
				 act.moveToElement(title);
				 act.perform();
				 Thread.sleep(500);
				 
				 String Username = title.getText();
				 
				 if(Username.equals("CURA Healthcare Service"))
				 {
					 System.out.println("Title matches");
				 }
				 else
				 {
					 System.out.println("Title does not match");
					File scr = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
					File dest = new File("C:\\Users\\91942\\Documents\\Automation document\\screenshot\\.png");
					FileHandler.copy(scr, dest);
					
				 }
				 
				 //selecting date
				 WebElement datePicker = driver.findElement(By.xpath("//input[@type='text']"));
				 act.moveToElement(datePicker);
				 datePicker.sendKeys("02/07/2023");
				 Thread.sleep(500);
				 
			// clicking on book appointment	 
			WebElement appointmentButton = driver.findElement(By.id("btn-book-appointment"));
			act.moveToElement(appointmentButton);
			Thread.sleep(500);
			appointmentButton.click();
			Thread.sleep(500);
			
			//clicking on menu and clicking on logout
			driver.findElement(By.id("menu-toggle")).click();
			Thread.sleep(500);
			
			driver.findElement(By.xpath("//a[text()='Logout']")).click();
		
	}

}
