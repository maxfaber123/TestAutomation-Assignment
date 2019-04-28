package com.automation.tests;

import static org.testng.Assert.assertEquals;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.automation.base.TestBase;
import com.automation.pages.CategoryPage;
import com.automation.pages.HomePage;
import com.automation.pages.OrderPage;

public class CategoryPageTest extends TestBase {

	HomePage homepage;
	CategoryPage categorypage;
	OrderPage orderpage;


	public CategoryPageTest() {
		super();
	}


	@BeforeMethod
	public void setup() throws Exception {

		initialization();
		homepage = new HomePage();
		categorypage= homepage.selectSummerDress();




	}

	@Test
	public void verifyselectItemAndCheckOut() throws Exception {

		logger.info("Calling the verifyselectItemAndCheckOut test of the CategoryPageTest" );

		String catTitle = categorypage.validateCategoryPageTitle();
		softAssert.assertEquals(catTitle, "Summer Dresses - My Store");

		orderpage = categorypage.selectItemAndAddToCart();
		softAssert.assertAll();

	}

	@AfterMethod
	public void tearDown() {

		driver.quit();
	}


}
