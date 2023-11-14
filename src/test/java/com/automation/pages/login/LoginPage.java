package com.automation.pages.login;

import com.automation.pages.base.BasePage;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


public class LoginPage extends BasePage {
	
	@FindBy(name="username")WebElement userNameElement;
	@FindBy(id="password")WebElement passwordElement;
	@FindBy(id="Login")WebElement loginbuttonElement;
	@FindBy(id="error")WebElement alertElement;
	
	public LoginPage(WebDriver driver) {
		super(driver);
	}
	
	public void enterUserName(String data) {
		enterText(userNameElement, data,"Username textbox");
	}
	public void enterPassword(String data) {
		enterText(passwordElement,data,"password textbox");
	}
	public void clickButton() {
		clickElement(loginbuttonElement,"Login button");
	}
	public String getTitleOfThePage()  {
		return getPageTitle();
	}
	public void alertmessage() {
		waitForAlertPresent( 5) ;
	switchToAlert();
	}
	public WebDriver getErrorTextMessage(){
		  getTextFromElement(alertElement,"alert");
		return driver;
	}
	
	
	

}
