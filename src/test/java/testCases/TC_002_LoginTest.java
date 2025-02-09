package testCases;

import static org.testng.Assert.fail;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;

public class TC_002_LoginTest extends BaseClass{
	
	@Test(groups={"Sanity", "Master" })
	public void verify_LoginTest()
	{
		logger.info("************Starting the TC_002_LoginTest*************** ");
		
		try {
			
			//home page
		HomePage hp=new HomePage(driver);
		hp.clickMyAccount();
		logger.info("clicked on myaccount link on the home page..");
		hp.clickLogin();
		logger.info("Clicked on login link under my account");
		
		// Login page
		LoginPage lp=new LoginPage(driver);
		logger.info("Entering valid email and password..");
		lp.setEmail(p.getProperty("email"));
		lp.setPassword(p.getProperty("password"));
		Thread.sleep(5000);
		lp.clickLogin();  //login button
		logger.info("Click on login button");
		
		//MyAccount page
		MyAccountPage macc=new MyAccountPage(driver);
		boolean targetPage=macc.isMyAccountPageExists();
		
		Assert.assertEquals(targetPage,true); //Assert.assertTrue(true)
		//macc.clickLogout();
		}
		catch(Exception e)
		{
			Assert.fail();
		}
			
		logger.info("************Finishing the TC_002_LoginTest*************** ");
	}
	
}