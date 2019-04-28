package com.automation.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;


import com.automation.util.TestUtil;
import com.automation.util.WebEventListener;

import okio.Timeout;

public class TestBase {
	
	public static WebDriver driver;
	public static Properties prop;
	
	public static WebDriverWait wait;
	
	public static EventFiringWebDriver eventfiringDriver;
	public static WebEventListener webeventlistner;
	public static Logger logger =  LogManager.getLogger(TestBase.class);
	
	public TestBase()  {
		
		
		try {
		
		prop = new Properties();
		FileInputStream ip = new FileInputStream(
				System.getProperty("user.dir") +"//src//main//java//com//automation//config//config.properties" 
				);
		prop.load(ip);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		
	
	}
	
	public static void initialization() {
		

		logger.info("Initializing the Webdriver and the Browser ");
	String browserName = 	prop.getProperty("browser");
	if (browserName.equals("chrome")) {
		
		System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") +"//driver//chromedriver.exe");
		driver = new ChromeDriver();
		
	} else if  (browserName.equals("FF")) {
		
		System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir") +"//driver//geckodriver.exe");
		driver = new FirefoxDriver();
		
		
	}
	logger.info("Initializing the Eventfiringwebdriver to log all the WebDriver Events");
	
	eventfiringDriver = new EventFiringWebDriver(driver);
	webeventlistner = new WebEventListener();
	eventfiringDriver.register(webeventlistner);
	driver = eventfiringDriver;
	
	
	
	
	driver.manage().window().maximize();
	driver.manage().deleteAllCookies();
	driver.manage().timeouts().pageLoadTimeout(TestUtil.PAGE_LOAD_TIMEOUT,TimeUnit.SECONDS);
	driver.manage().timeouts().implicitlyWait(TestUtil.IMPLICIT_WAIT, TimeUnit.SECONDS);
	
	driver.get(prop.getProperty("url"));
	
	}

}
