package pageObjects;

import java.time.Duration;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AccountRegistrationPage extends BasePage {
	
	
	// created subclass constuctor to call parent class (BasePage class) constructor
	
	public AccountRegistrationPage(WebDriver driver)
	{
		super(driver);
	}
	
	//WebElement
	
	@FindBy(xpath="//input[@id='input-firstname']")
	WebElement txtFirstName;
	
	@FindBy(xpath="//input[@id='input-lastname']")
	WebElement txtLastName;
	
	@FindBy(xpath="//input[@id='input-email']")
	WebElement txtEmail;
	
	@FindBy(xpath="//input[@id='input-password']")
	WebElement txtPassword;
			
	@FindBy(xpath="//input[@name='agree']")
	WebElement tglPolicy;

	@FindBy(xpath="//button[normalize-space()='Continue']")
	WebElement btnContinue;
	
	@FindBy (xpath="//h1[normalize-space()='Your Account Has Been Created!']")
	WebElement msgConfirmation;
	
	
	public void setFirstName(String fname)
	{
		txtFirstName.sendKeys(fname);
	}
	
	public void setLastName(String lname)
	{
		txtLastName.sendKeys(lname);
	}
	
	public void setEmail(String email)
	{
		txtEmail.sendKeys(email);
	}
	
	public void setPassword(String pwd)
	{
		txtPassword.sendKeys(pwd);
	}
	
	public void clickPrivacyPolicy() throws InterruptedException
	{
		
		JavascriptExecutor js = (JavascriptExecutor) driver;
		
		js.executeScript("arguments[0].scrollIntoView();", tglPolicy);	
		// System.out.println("pixel value:"+ js.executeScript("return window.pageYOffset;"));
	
		try{WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.elementToBeClickable(tglPolicy)).click();
		}
		
		
		catch(Exception e)
		{
			System.out.println("need exceptionmessage :" +e.getMessage());
		}
		
	}
	
	public void clickContinue()
	{
		//btnContinue.click();
		
		//Sol2
		//btnContinue.submit();
	/*	
		//Sol3
		
		Actions act=new Actions(driver);
		   act.moveToElement(btnContinue).click().perform();
		
		//Sol4
		 JavascriptExecutor js=(JavascriptExecutor) driver;
		 js.executeScript("arguments[0].clcik();", btnContinue);
			
		//Sol 5
			btnContinue.sendKeys(Keys.RETURN);
		*/	
		//Sol6
			WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(10));
		  wait.until(ExpectedConditions.elementToBeClickable(btnContinue)).click();
		
		
	}

	public String getConfirmationMsg()
	{
		try {
		String msg=msgConfirmation.getText();
		return msg;
		}
		catch(Exception e)
		{
			return (e.getMessage());
		}
	}

	//input[@id='input-firstname']
	//input[@id='input-lastname']
	//input[@id='input-email']
	//input[@id='input-password']
	//button[normalize-space()='Continue']

}
