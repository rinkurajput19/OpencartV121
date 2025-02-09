package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.AccountRegistrationPage;
import pageObjects.HomePage;
import testBase.BaseClass;

public class TC_001_AccountRegistrationTest extends BaseClass{


	@Test(groups={"Regression", "Master"})
	public void verify_account_registration() throws InterruptedException
	{
 
		logger.info("*******Starting TC_001_AccountRegistrationTest**********");
        logger.debug("This is a debug log message");
		
        try {
		HomePage hp=new HomePage(driver);
		hp.clickMyAccount();
		logger.info("Clicked on my account link....");
		hp.clickRegister();
		logger.info("Clicked on my Register link....");
		AccountRegistrationPage arp=new AccountRegistrationPage(driver);
		
		logger.info("Providing customer details.....");
		arp.setFirstName(randomeString().toUpperCase());
		arp.setLastName(randomeString().toUpperCase());
		arp.setEmail(randomeString()+"@gmail.com");
		arp.setPassword(randomPwdString());		
		arp.clickPrivacyPolicy();
		//Thread.sleep(3000);
		arp.clickContinue();
		
		logger.info("Validating expected message....");
		Assert.assertEquals(arp.getConfirmationMsg(),"Your Account Has Been Created!");
		
		logger.info("Test passed");
        }
        catch(Exception e)
        {
        	logger.error("Test failed: " +e.getMessage());
        	Assert.fail("Test failed: " + e.getMessage());
        }
		
	}

    



}
