package com.automation.tests;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.automation.base.TestBase;
import com.automation.pages.AddressTabPage;
import com.automation.pages.CategoryPage;
import com.automation.pages.CreateAccountPage;
import com.automation.pages.GuestCheckoutPage;
import com.automation.pages.HomePage;
import com.automation.pages.OrderPage;
import com.automation.util.TestUtil;

public class CreateAccountPageTest extends TestBase {


	HomePage homepage;
	CategoryPage categorypage;
	OrderPage orderpage;
	GuestCheckoutPage guestcheckoutPage;
	CreateAccountPage createaccountpage;
	AddressTabPage addresstabpage;

	String sheetName = "NewAcct";



	public CreateAccountPageTest() {
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


	}

	@Test
	public void verifyCreateAccontPage() {

		logger.info("Calling Methods for the Create Account Page ");

		String pagetxt = createaccountpage.getTitle();

		softAssert.assertEquals(pagetxt, "Authentication" ,"Create Account Page does not match the text");
		
		softAssert.assertAll();


	}



	@DataProvider
	public Object[][] getAcctCreationData() {
		Object data[][] = TestUtil.getTestData(sheetName);


		return data;
	}


	@Test(dataProvider="getAcctCreationData")
	public void verifyNewAccountCreation(String gndr, String fstnm , String lstnm, String paswd, String dy, 
			String mnth, String yr, String addr_fstnam , String add_lstnm , String add_cpy,String addr1, String addr2,String ctys, 
			String strt, String zips, String ctry,String addntnlInfo,String homephn, String mobileph,String alis) throws Exception {


		addresstabpage=  createaccountpage.createAccount(gndr, fstnm, lstnm,paswd,dy,mnth,yr,addr_fstnam,add_lstnm,add_cpy,addr1,addr2,ctys,
				strt,	zips,ctry,addntnlInfo,homephn,mobileph,alis	);

	}

	@Test
	public void verifyNoPhoneNumberPassed() throws Exception {

		logger.info("Calling verifyNoPhoneNumberPassed Method ... ");

		String phoneErrorTxt=  createaccountpage.verifyNoPhoneNumberPassed("M", "FN", "LN", "mary123", "2", "4", "7", "addrfn", "addln", "add_cmny", "add1", "add2",
				"cty", "Florida", "01890", "United States", "additionalInfo", " ", " ", "AliasAdd");

		softAssert.assertEquals(phoneErrorTxt, prop.getProperty("phoneErrorMsg"));


		softAssert.assertAll();


	}

	@Test
	public void errorVerificationWhennoValuePassedInForm() throws Exception {

		logger.info("Calling errorVerificationWhennoValuePassedInForm Method ......");


		String[] errorVals =  createaccountpage.VerifyErrorWhenoValuePassed("M", " ",	 " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ",
				" ", " ", " ", " ", " ", " ", " ");


		softAssert.assertEquals(errorVals[0], "You must register at least one phone number.");

		softAssert.assertEquals(errorVals[1], "passwd");

		softAssert.assertEquals(errorVals[2], "This country requires you to choose a State.");
		softAssert.assertAll();


	}


	@Test
	public void verifyElementDoesNotExistForCountrySelection() throws Exception {


		logger.info("Calling Method verifyElementDoesNotExistForCountrySelection .....");



		boolean status =  createaccountpage.verifyNonExistanceofElement("M", "FN", "LN", "mary123", "2", "4", "7", "addrfn", "addln", "add_cmny", "add1", "add2",
				"cty", "Florida", "01890", "-", "additionalInfo", " ", " ", "AliasAdd");


		softAssert.assertFalse(status, "Value is still shown");
		softAssert.assertAll();

	}
	
	@AfterMethod
	public void tearDown() {

		driver.quit();
	}

}
