package testBase;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import freemarker.core.TemplateFormatUtil;

import org.apache.logging.log4j.LogManager;//log4j
import org.apache.logging.log4j.Logger;   //log4j



public class BaseClass {
	

	public static WebDriver driver;
	public Logger logger;
	public Properties p;
	
	@BeforeClass(groups= {"Sanity","Regression","Master"})
	@Parameters({"browser", "os"})
	public void setUp(String browser, String os) throws IOException 
	{
		// to load the config.peroperties file user FileReader or FileInputStream class
		
		//FileReader file=new FileReader(".//src//test//resources//config.properties");
		              //OR
		FileInputStream file=new FileInputStream(".//src//test//resources//config.properties");
		   p=new Properties();
		   p.load(file);
		
		//to load the log4j2.xml file in base class
		logger=LogManager.getLogger(this.getClass());   //log4j
		
		if(p.getProperty("execution_env").equalsIgnoreCase("remote"))
		{
			DesiredCapabilities cap=new DesiredCapabilities();
			
			//code for selecting operating system
			
			if (os.equalsIgnoreCase("windows"))
			{
				cap.setPlatform(Platform.WIN11);
			}
			else if (os.equalsIgnoreCase("mac"))
			{
				cap.setPlatform(Platform.MAC);
			}
			else if(os.equalsIgnoreCase("Linux"))
			{
				cap.setPlatform(Platform.LINUX);
			}
			else
			{
				System.out.println("not matching Operating system");
				return;
			}
			

			
			//code for selecting browser
			 
			switch(browser.toLowerCase())
			{
			case "chrome" : cap.setBrowserName("chrome");break;
			case "edge"   : cap.setBrowserName("MicrosoftEdge");break;
			case "firefox":cap.setBrowserName("firefox");break;
			default : System.out.println("No matching browser");return;
			}	
			
			driver=new RemoteWebDriver(new URL("http://192.168.1.80:4444/wd/hub"), cap);
			
		}
		
		if(p.getProperty("execution_env").equalsIgnoreCase("local"))
		{
			
			switch(browser.toLowerCase())
			{
			case "chrome":driver=new ChromeDriver();break;
			case "edge":  driver=new EdgeDriver();break;
			case "firefox":driver=new FirefoxDriver();break;
			default:System.out.println("invalid browser"); return;
			}
		}
		
        driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

		driver.get(p.getProperty("appURL1")); //reading URL from peroperties file
		driver.manage().window().maximize();
		//Thread.sleep(3000);

	}

	@AfterClass(groups= {"Sanity","Regression","Master"})
	public void tearDown()
	{
		driver.quit();
	}
	
	public String randomeString()
    {
    	String genratedString=RandomStringUtils.randomAlphabetic(5);
    	return genratedString;
    }
    
    public String randomPwdString()
    {
    	String genratedString=RandomStringUtils.randomAlphabetic(3);
    	String genratedNeumeric=RandomStringUtils.randomNumeric(3);
    	 String	pwdString=	genratedString+"@"+genratedNeumeric;
    	 return pwdString;
    }
	
    
    public String captureScreen(String tname) throws IOException {

		String timeStamp = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
				
		TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
		File sourceFile = takesScreenshot.getScreenshotAs(OutputType.FILE);
		
		String targetFilePath=System.getProperty("user.dir")+"\\screenshots\\" + tname + "_" + timeStamp + ".png";
		File targetFile=new File(targetFilePath);
		
		sourceFile.renameTo(targetFile);
			
		return targetFilePath;

	}
	

}
