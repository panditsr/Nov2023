package com.automation.pages.base;

import java.time.Duration;
import java.util.List;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.automation.tests.utilities.ExtentReportsUtility;
import com.automation.tests.utilities.Log4jUtility;

public class BasePage {
	
protected	WebDriver driver=null;
protected    WebDriverWait wait=null;
protected   Log4jUtility logObject = Log4jUtility.getInstance();
protected	static  Logger MyLog;	
protected   ExtentReportsUtility report=ExtentReportsUtility.getInstance();
		

	public BasePage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	
	}
	
	public  void launchBrowser(String browserName) {
		switch(browserName) {
		case "firefox":driver=new FirefoxDriver();
						System.out.println("firefox browser launched");
						break;
		case "chrome":driver=new ChromeDriver();
						System.out.println("chrome browser launched");
						break;
		default: System.out.println("you have not entrered the correct browser");}
		
}
		
public  void goTourl(String url) {
	driver.get(url);
	System.out.println(url + "is entered");
}
public  void maximiseBroser() {
	driver.manage().window().maximize();
	System.out.println("Browser window has maximized");
}

public static String getTextFromElement(WebElement ele,String objectName) {
	String data=ele.getText();
	System.out.println("extracted the text from"+ objectName);
	return data;
	/*if(actual.equals(expected))
		System.out.println("testcase passed");
	else {
		System.out.println("testcase failed");
	}
	driver.close();*/
	
}
public  String getPageTitle() {
	return driver.getTitle();
}
public void refreshpage() {
	driver.navigate().refresh();
}

public  void closeBrowser() {
	driver.close();
}
public void quitBrowser() {
	driver.quit();
}
public static void enterText(WebElement ele,String data,String objectName) {

if(ele.isDisplayed()) {
	 ele.clear();
	 ele.sendKeys(data);
	 System.out.println("Username is entered in the username field" + objectName);}
else {
	 System.out.println("username element is not displayed");
}
}
public static void clickElement(WebElement ele,String objectName) {
 if(ele.isEnabled()){
	 ele.click();
	 MyLog.info("Button is clicked");
	 
 }
 else {
	 System.out.println("Button element  is not enabled");
	              
 }	
			
		
		
}
	public  void waitForVisibility(WebElement ele,int time,int pollingtime,String objectName) {
		FluentWait<WebDriver> wait=new FluentWait<WebDriver>(driver);
		wait.withTimeout(Duration.ofSeconds(time))
		.pollingEvery(Duration.ofSeconds(pollingtime))
		.ignoring(ElementNotInteractableException.class);
			
		wait.until(ExpectedConditions.visibilityOf(ele));
		System.out.println(objectName+" is waited for visibility using fluent wait");}
	
	public  void waitForVisibility(WebElement ele,int time,String objectName) {
		wait=new WebDriverWait(driver,Duration.ofSeconds(time));
		wait.until(ExpectedConditions.visibilityOf(ele));
	}
	
	public  void waitForAlertPresent(int time) {
		WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(time));
		wait.until(ExpectedConditions.alertIsPresent()); }
	
	public  void waitUntilElementtobeClickable(By locator,String objectName) {
		wait=new WebDriverWait(driver,Duration.ofSeconds(30));
		wait.until(ExpectedConditions.elementToBeClickable(locator));
	}

	public static void SelectfromDropDown(WebElement ele,String text) {
		if(ele.isDisplayed()) {
			System.out.println("select drop down is displayed");
		Select select=new Select(ele);
		select.selectByVisibleText(text);
		List<WebElement> list=select.getOptions();
		for(WebElement ele1:list)	{
			System.out.println (ele1.getText());
		}}
	}
		public static void SelectfromDropDown(WebElement ele, int index) {
			Select select=new Select(ele);
			select.selectByIndex(index);;
		}
		public  void switchFrame(WebElement ele, String ObjectName) {
			if(ele.isDisplayed()) {
				driver.switchTo().frame(ele);
				System.out.println("we can switch to the frame:passed");}
			else
			{System.out.println("we cannot switch to the frame:passed");
			}
		}
 		public static void SwitchTodefaultFrame(WebDriver driver)	{
 			driver.switchTo().defaultContent();
 			System.out.println("we can switch back to the default frame");
 			
 		}
 		
 		public static void SwitchToFrameid(WebDriver driver,String ObjectName)	{
 			driver.switchTo().frame(ObjectName);
 			System.out.println("we can switch  to the" + ObjectName+  "frame");
 		}
			
 		public static void	mousehover(WebDriver driver,WebElement ele) {
 			if(ele.isDisplayed()) {
 				 Actions action = new Actions(driver);
 				 action.moveToElement(ele).build().perform();
 				System.out.println("Passed");
 			}else 
 			{System.out.println("failed");
 				
 				
 			}
 		}
 		public static void 	alert(WebDriver driver,String expected ) throws InterruptedException {
 			
 			
 			Alert loginerrorAlert=driver.switchTo().alert();//switch to alert
 			String actual=loginerrorAlert.getText(); // capture alert message
 			System.out.println("text is extracted from alert box");
 			loginerrorAlert.accept();
 			
 			if(actual.equals(expected)) {
 				System.out.println("testcase passed");}
 			else {
 				System.out.println("testcase failed");
 			}                                            // Print Alert Message
 			Thread.sleep(5000);
 	      
 	        
 		}
 		public Alert switchToAlert() {
			Alert alert=driver.switchTo().alert();
			System.out.println("Switch to alert");	
			return null;
			}
 		
 		public void AcceptAlert(Alert alert) {
 			alert.accept();
 			System.out.println("Alert Accepted");}
 			
 			public void dismissAlert() {
 				Alert alert= switchToAlert();
 	 			alert.dismiss();
 	 			System.out.println("Alert Dismissed");
 		}
 		
			

		public static void actualurl(WebDriver driver,String text) {
 		
 		 String actualurl=driver.getCurrentUrl();
 	    if(actualurl.equalsIgnoreCase(text))
 	    
 	    {
 	    
 	   	 System.out.println("Test passed");
 	    }
 	    else
 	     {
 	   	 System.out.println("Test failed");
 	     }
 		}
 		public static void actualTitle(WebDriver driver,String Expectedtext) {
     		
    		 String actual=driver.getTitle();
    	    if(actual.equalsIgnoreCase(Expectedtext));
    	    
    	    {
    	   	 System.out.println("Test passed");
    	    }
    	     {
    	   	 System.out.println("Test failed");}
 		
}
 		public  void select_Checkbox(String string,String locateBypath) {
 		
 			WebElement rememberme;
 			rememberme=driver.findElement(By.name(locateBypath));
 		if(!rememberme.isSelected())
 		{
 			rememberme.click();
 		}
 		//public static void downloadFiles()
 		
 		}
 		
 		
 		

}
