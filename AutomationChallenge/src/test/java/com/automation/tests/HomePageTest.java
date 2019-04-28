package com.automation.tests;

import static org.testng.Assert.assertEquals;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.automation.base.TestBase;
import com.automation.pages.CategoryPage;
import com.automation.pages.HomePage;

public class HomePageTest extends TestBase {

	HomePage homepage;

	CategoryPage categorypage;


	public HomePageTest() {
		super();
	}

	@BeforeMethod
	public void setup() {

		initialization();
		homepage = new HomePage();
		categorypage = new CategoryPage();


	}


	@Test
	public void navigateToCategoryTest() throws Exception {



		String title = homepage.validateHomePageTitle();
		assertEquals(title, "My Store");

		categorypage = homepage.selectSummerDress();


	}



	@AfterMethod
	public void tearDown() {

		driver.quit();
	}

}
