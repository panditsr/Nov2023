package com.automation.tests.scripts;
import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.List;
import java.util.Properties;
import java.util.Set;
import java.util.ArrayList;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.BeforeTest;

import com.automation.tests.utilities.Log4jUtility;
import com.automation.tests.utilities.PropertiesUtility;
import com.google.common.io.Files;

//import com.automation.tests.utilities.ExtentReportsUtility;
//import com.automation.tests.utilities.Log4Jutility;

public class BaseTest {
		
	static WebDriver driver=null;
	static WebDriverWait wait=null;	
	protected Log4jUtility logObject = Log4jUtility.getInstance();
	static protected Logger MyLog;
	
	@BeforeTest
	public void setupBeforeTest() {
		MyLog=logObject.getLogger();
	}
		
	@BeforeMethod
	@Parameters("browsername")
	public static void setUpBeforeTestMethod(@Optional("firefox") String browser_name) {
		PropertiesUtility pro=new PropertiesUtility();
		Properties applicationProFile = pro.loadFile("applicationDataProperties");
		String url = applicationProFile.getProperty("url");
		launchBrowser("browser_name");
		maximiseBroser();
		goTourl("url");
	}
	@AfterMethod
	public void tearDownAfterTestMethod() {
		closeBrowser();
		MyLog.info("******login_to_salesforce automation script ended***********");
		
		
		}

	@Parameters("browsername")
	
	
	
	
	
	
	public static void launchBrowser(String browserName) {
		switch(browserName) {
		case "firefox":driver=new FirefoxDriver();
						System.out.println("firefox browser launched");
						break;
		case "chrome":driver=new ChromeDriver();
						System.out.println("chrome browser launched");
						break;
		default: MyLog.error("you have not entrered the correct browser");}
		
}
		
public static void goTourl(String url) {
	driver.get(url);
	MyLog.info(url + "is entered");
}
public static void maximiseBroser() {
	driver.manage().window().maximize();
	MyLog.info("Browser window has maximized");
}

public static void refreshPage() {
	driver.navigate().refresh();}

public static String getTextFromElement(WebElement ele,String objectName) {
	String data=ele.getText();
	MyLog.info("extracted the text from"+ objectName);
	return data;
	/*if(actual.equals(expected))
		System.out.println("testcase passed");
	else {
		System.out.println("testcase failed");
	}
	driver.close();*/
	
}
public static void closeBrowser() {
	driver.close();
}
public void quitBrowser() {
	driver.quit();
}


public static void enterText(WebElement ele,String data,String objectName) {

if(ele.isDisplayed()) {
	 ele.clear();
	 ele.sendKeys(data);
	 MyLog.info("Username is entered in the username field" + objectName);}
else {
	MyLog.info("username element is not displayed");
}
}
public static void clickElement(WebElement ele,String data,String objectName) {
 if(ele.isEnabled()){
	 ele.click();
	 MyLog.info("Button is clicked");
	 
 }
 else {
	 MyLog.info("Button element  is not enabled");
	              
 }
	
		
		
		
		
		
}
	public static void waitForVisibility(WebElement ele,int time,int pollingtime,String objectName) {
		FluentWait<WebDriver> wait=new FluentWait<WebDriver>(driver);
		wait.withTimeout(Duration.ofSeconds(time))
		.pollingEvery(Duration.ofSeconds(pollingtime))
		.ignoring(ElementNotInteractableException.class);
			
		wait.until(ExpectedConditions.visibilityOf(ele));
		MyLog.info(objectName+" is waited for visibility using fluent wait");}
	
	public static void waitForVisibility(WebElement ele,int time,String objectName) {
		wait=new WebDriverWait(driver,Duration.ofSeconds(time));
		wait.until(ExpectedConditions.visibilityOf(ele));
	}

	public static void SelectfromDropDown(WebElement ele,String text) {
		if(ele.isDisplayed()) {
			System.out.println("select drop down is displayed");
		Select select=new Select(ele);
		select.selectByVisibleText(text);
		List<WebElement> list=select.getOptions();
		for(WebElement ele1:list)	{
			MyLog.info (ele1.getText());
		}}
	}
		public static void SelectfromDropDown(WebElement ele, int index) {
			Select select=new Select(ele);
			select.selectByIndex(index);;
		}
		public static void selectelementbyTextdata(WebElement ele,String text) {
			
			Select select=new Select(ele);
			select.selectByVisibleText(text);
			MyLog.info("selected" + text);
			
			
		}
        public static void selectelementbyIndexdata(WebElement ele,int index) {
			
			Select select=new Select(ele);
			select.selectByIndex(index);;
			MyLog.info("selected with index" + index);
        }
 public static void selectelementbyValuedata(WebElement ele,String text) {
			
			Select select=new Select(ele);
			select.selectByValue(text);
			MyLog.info("selected with value" + text);
        }
		public static void switchFrame(WebElement ele, String ObjectName) {
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
			
 		public static void	mouseover(WebDriver driver,WebElement ele) {
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
 		public static void select_Checkbox(String string,String locateBypath) {
 		
 			WebElement rememberme;
 			rememberme=driver.findElement(By.name(locateBypath));
 		if(!rememberme.isSelected())
 		{
 			rememberme.click();
 		}}
 		public static void	takescreenshot(WebDriver driver,String filepath	)throws IOException	 {
 			TakesScreenshot screencapture=( TakesScreenshot)driver;
 		     File src= screencapture.getScreenshotAs(OutputType.FILE);
 		    // String imagepath=System.getProperty("user+dir")+"/results/screencapture/image1.png";
 		     File distination=new File(filepath);
 		     Files.copy(src,distination);}
 		
 		public static void	takescreenshotofelement(WebElement element,String filepath	)throws IOException	 {
 		     
 		     File src1= element.getScreenshotAs(OutputType.FILE);
 		    // String imagepath1=System.getProperty("user+dir")+"/results/screencapture/image1.png";
 		     File distination1=new File(filepath);
 		     Files.copy(src1, distination1);
 		     
 		}
 		
 		
 		
 		

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
