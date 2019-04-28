package com.automation.tests;

import static org.testng.Assert.assertEquals;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.automation.base.TestBase;
import com.automation.pages.CategoryPage;
import com.automation.pages.GuestCheckoutPage;
import com.automation.pages.HomePage;
import com.automation.pages.OrderPage;

public class OrderPageTest extends TestBase {

	HomePage homepage;
	CategoryPage categorypage;
	OrderPage orderpage;
	GuestCheckoutPage guestcheckoutPage;


	public OrderPageTest() {
		super();
	}


	@BeforeMethod
	public void setup() throws Exception {

		initialization();
		homepage = new HomePage();
		categorypage= homepage.selectSummerDress();
		orderpage = categorypage.selectItemAndAddToCart();


	}

	@Test
	public void verifyselectItemAndCheckOut() throws Exception {

		logger.info("Calling the verifyselectItemAndCheckOut test of the OrderPageTest" );

		String orderpageTitle = orderpage.verifyOrderPageTitle();
		softAssert.assertEquals(orderpageTitle, "Order - My Store");

		guestcheckoutPage = orderpage.clickonProceedToCheckoutButton();
		softAssert.assertAll();

	}

	@AfterMethod
	public void tearDown() {

		driver.quit();
	}

}
