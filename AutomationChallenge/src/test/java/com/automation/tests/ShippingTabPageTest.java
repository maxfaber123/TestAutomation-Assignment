package com.automation.tests;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.automation.base.TestBase;
import com.automation.pages.AddressTabPage;
import com.automation.pages.CategoryPage;
import com.automation.pages.CreateAccountPage;
import com.automation.pages.GuestCheckoutPage;
import com.automation.pages.HomePage;
import com.automation.pages.OrderPage;
import com.automation.pages.PaymentConfirmOrderPage;
import com.automation.pages.ShippingTabPage;
import com.automation.util.TestUtil;

public class ShippingTabPageTest extends TestBase {

	HomePage homepage;
	CategoryPage categorypage;
	OrderPage orderpage;
	GuestCheckoutPage guestcheckoutPage;
	CreateAccountPage createaccountpage;
	AddressTabPage addresstabpage;
	ShippingTabPage shippingtabpage;
	PaymentConfirmOrderPage paymentconfirmationorderpage;

	public ShippingTabPageTest() {

		super();
	}

	@BeforeMethod
	public void setUp() throws Exception {


		initialization();
		homepage = new HomePage();
		categorypage= homepage.selectSummerDress();
		orderpage = categorypage.selectItemAndAddToCart();
		guestcheckoutPage =  orderpage.clickonProceedToCheckoutButton();

		createaccountpage  = guestcheckoutPage.enterEmailandClickCreatAccount(TestUtil.emailRandom());

		addresstabpage=  createaccountpage.createAccount("F", "FN", "LN", "mary123", "2", "8", "29", "addrfn", "addln", "add_cmny", "add1", "add2",
				"cty", "Florida", "01890", "United States", "additionalInfo", "2015087765", "2014508776", "AliasAdd");

		shippingtabpage = addresstabpage.proceedtoCheckoutAddressTab();
	}
	
	@Test
	public void verifyShippingPageTitleAndPageText() throws Exception {

		logger.info("Calling Method verifyShippingPageTitleAndPageText......");



		String pageText = shippingtabpage.validateShippingTabPage();
		softAssert.assertEquals(pageText, "SHIPPING");
		softAssert.assertEquals(driver.getTitle(), "Order - My Store");
		softAssert.assertAll();


	}

	@Test
	public void verifyProceedToCheckoutShippingTab() throws Exception {


		logger.info("Calling Method verifyProceedToCheckoutShippingTab......");

		paymentconfirmationorderpage = shippingtabpage.proceedtoShippingAddressTab();




	}

	@AfterMethod
	public void tearDown() {

		driver.quit();
	}

}
