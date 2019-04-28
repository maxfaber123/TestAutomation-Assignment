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
import com.automation.pages.ShippingTabPage;
import com.automation.util.TestUtil;

public class AddressTabPageTest extends TestBase {

	HomePage homepage;
	CategoryPage categorypage;
	OrderPage orderpage;
	GuestCheckoutPage guestcheckoutPage;
	CreateAccountPage createaccountpage;
	AddressTabPage addresstabpage;
	ShippingTabPage shippingtabpage;


	public AddressTabPageTest() {
		super();
	}

	@BeforeMethod
	public void setup() throws Exception {

		initialization();
		homepage = new HomePage();
		categorypage= homepage.selectSummerDress();
		orderpage = categorypage.selectItemAndAddToCart();
		guestcheckoutPage =  orderpage.clickonProceedToCheckoutButton();

		createaccountpage  = guestcheckoutPage.enterEmailandClickCreatAccount(TestUtil.emailRandom());

		addresstabpage=  createaccountpage.createAccount("M", "FN", "LN", "mary123", "2", "5", "23", "addrfn", "addln", "add_cmny", "add1", "add2",
				"cty", "Florida", "01890", "United States", "additionalInfo", "2015087765", "2014508776", "AliasAdd");


	}

	@Test
	public void verifyPageTitleAndPageText() throws Exception {

		logger.info("Calling Method verifyPageTitleAndPageText......");



		String pageText = addresstabpage.validateAddressPage();
		softAssert.assertEquals(pageText, "Addresses");
		softAssert.assertEquals(driver.getTitle(), "Order - My Store");
		softAssert.assertAll();


	}



	@Test
	public void verifyProceedToCheckoutAddressTab() throws Exception {

		logger.info("Calling Method verifyProceedToCheckoutAddressTab......");



		shippingtabpage = addresstabpage.proceedtoCheckoutAddressTab();


	}

	@AfterMethod
	public void tearDown() {

		driver.quit();
	}

}
