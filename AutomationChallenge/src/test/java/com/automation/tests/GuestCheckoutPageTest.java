package com.automation.tests;

import static org.testng.Assert.assertEquals;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.automation.base.TestBase;
import com.automation.pages.CategoryPage;
import com.automation.pages.CreateAccountPage;
import com.automation.pages.GuestCheckoutPage;
import com.automation.pages.HomePage;
import com.automation.pages.OrderPage;
import com.automation.util.TestUtil;

public class GuestCheckoutPageTest extends TestBase {

	HomePage homepage;
	CategoryPage categorypage;
	OrderPage orderpage;
	GuestCheckoutPage guestcheckoutPage;
	CreateAccountPage createaccountpage;


	public GuestCheckoutPageTest() {
		super();
	}

	@BeforeMethod
	public void setup() throws Exception {

		initialization();
		homepage = new HomePage();
		categorypage= homepage.selectSummerDress();
		orderpage = categorypage.selectItemAndAddToCart();
		guestcheckoutPage =  orderpage.clickonProceedToCheckoutButton();


	}



	@Test
	public void verifyEmailAndCreateAccontButton() throws Exception {

		logger.info("Calling Methods for the GuestCheckoutPageTest");

		String guestpageTitle = guestcheckoutPage.verifyGuestPageTitle();
		softAssert.assertEquals(guestpageTitle, "Login - My Store");

		createaccountpage = guestcheckoutPage.enterEmailandClickCreatAccount(TestUtil.emailRandom());
		softAssert.assertAll();


	}



	@Test
	public void validateAlreadyRegisterdEmails() throws Exception {

		logger.info("Calling Methods for the GuestCheckoutPageTest");

		String emailError =  guestcheckoutPage.verifyAlreadyRegisteredEmail(prop.getProperty("email"));

		softAssert.assertEquals(emailError, prop.getProperty("emailError"));
		
		softAssert.assertAll();

	}


	@AfterMethod
	public void tearDown() {

		driver.quit();
	}


}
