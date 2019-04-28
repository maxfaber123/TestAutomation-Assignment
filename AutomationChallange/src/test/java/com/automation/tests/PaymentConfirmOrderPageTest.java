package com.automation.tests;

import static org.testng.Assert.assertEquals;

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

public class PaymentConfirmOrderPageTest extends TestBase{



	HomePage homepage;
	CategoryPage categorypage;
	OrderPage orderpage;
	GuestCheckoutPage guestcheckoutPage;
	CreateAccountPage createaccountpage;
	AddressTabPage addresstabpage;
	ShippingTabPage shippingtabpage;
	PaymentConfirmOrderPage paymentconfirmationorderpage;


	public PaymentConfirmOrderPageTest() {

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

		addresstabpage=  createaccountpage.createAccount("F", "FN", "LN", "mary123", "2", "5", "36", "addrfn", "addln", "add_cmny", "add1", "add2",
				"cty", "Florida", "01890", "United States", "additionalInfo", "2015087765", "2014508776", "AliasAdd");

		shippingtabpage = addresstabpage.proceedtoCheckoutAddressTab();

		paymentconfirmationorderpage =  shippingtabpage.proceedtoShippingAddressTab();

	}

	@Test
	public void verifyOrder() throws Exception {

		logger.info("Calling Method verifyorder to confirm the order ......");



		String dressInfo = paymentconfirmationorderpage.getDressInfo();
		String dressSize= paymentconfirmationorderpage.getDressSize();
		String price= paymentconfirmationorderpage.getPriceInfo();

		assertEquals(dressInfo, "Printed Chiffon Dress");
		assertEquals(dressSize, "M");
		assertEquals(price, "$16.40");


	}

	@AfterMethod
	public void tearDown() {

		driver.quit();
	}

}


