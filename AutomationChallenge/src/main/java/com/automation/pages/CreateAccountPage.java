package com.automation.pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.automation.base.TestBase;
import com.automation.util.TestUtil;

public class CreateAccountPage extends TestBase {

	WebDriverWait wait = new WebDriverWait(driver, 10);


	@FindBy(xpath = "//span[contains(text(),'Authentication')]")
	WebElement authenticationTxt;

	@FindBy(xpath="//input[@id='id_gender1']")
	WebElement maleTitleradioButton;

	@FindBy(xpath="//input[@id='id_gender2']")
	WebElement femaleTitleradioButton;

	@FindBy(xpath="//input[@id='customer_firstname']")
	WebElement firstName;

	@FindBy(xpath="//input[@id='customer_lastname']")
	WebElement lastName;

	@FindBy(xpath="//input[@id='email']")
	WebElement email;

	@FindBy(xpath="//input[@id='passwd']")
	WebElement passwd;

	@FindBy(xpath="//select[@id='days']")
	WebElement dayDropdown;

	@FindBy(xpath="//select[@id='months']")
	WebElement monthDropdown;

	@FindBy(xpath="//select[@id='years']")
	WebElement yearDropdown;

	@FindBy(xpath = "//label[contains(text(),'newsletter')]")
	WebElement newletterCheckBox;

	@FindBy(xpath = "//label[contains(text(),'special offers')]")
	WebElement specialofferCheckBox;

	@FindBy(xpath="//input[@id='firstname']")
	WebElement firstNameAddress;

	@FindBy(xpath="//input[@id='lastname']")
	WebElement lastNameAddress;

	@FindBy(xpath="//input[@id='company']")
	WebElement companyAddress;

	@FindBy(xpath="//input[@id='address1']")
	WebElement Address1;

	@FindBy(xpath="//input[@id='address2']")
	WebElement Address2;

	@FindBy(xpath="//input[@id='city']")
	WebElement city;

	@FindBy(xpath="//select[@id='id_state']")
	WebElement state;

	@FindBy(xpath="//input[@id='postcode']")
	WebElement postcode;

	@FindBy(xpath="//select[@id='id_country']")
	WebElement country;

	@FindBy(xpath = "//textarea[@id='other']")
	WebElement additionalInformation;

	@FindBy(xpath="//input[@id='phone']")
	WebElement homePhone;

	@FindBy(xpath="//input[@id='phone_mobile']")
	WebElement mobilePhoneNumber;

	@FindBy(xpath="//input[@id='alias']")
	WebElement aliasAddress;

	@FindBy(xpath="//button[@id='submitAccount']")
	WebElement registerBtn;



	@FindBy(xpath= "//b[contains(text(),'lastname')]")
	WebElement lastnameError;

	@FindBy(xpath= "//b[contains(text(),'firstname')]")
	WebElement firstnameError;

	@FindBy(xpath= "//li[contains(text(),'register at least')]")
	WebElement phoneNumberError;

	@FindBy(xpath = "//b[contains(text(),'passwd')]")
	WebElement passwordError;

	@FindBy(xpath = "//b[contains(text(),'address1')]")
	WebElement addressEror;

	@FindBy(xpath = "//b[contains(text(),'city')]")
	WebElement cityError;

	@FindBy(xpath = "//li[contains(text(),'State')]	")
	WebElement stateError;




	public CreateAccountPage() {

		PageFactory.initElements(driver, this);
	}


	public String getTitle() {

		return authenticationTxt.getText();
	}


	public String validateEmail() throws Exception {

		String value;
		try {
			email.click();
			System.out.println("Clicked on the email field");
			Thread.sleep(20000);
			value = email.getAttribute("value");

			System.out.println("Email Id in the " + value);

		} catch (Exception e) {

			logger.error("Error occured while retrieving email value " , e);

			throw (e);
		}

		return value;

	}

	public AddressTabPage createAccount(String gender, String fn , String ln, String pswd, String day, 

			String month, String year, String addr_fn , String add_ln , String add_cmny,String add1, String add2,String cty, 
			String st, String zip, String cntry,String additionalInfo,String homeph, String mobile,String alias) throws Exception {

		System.out.println("Inside the page object of the account creation  " + gender);
		System.out.println("Day " + day);

		try {

			if (gender.equals("M")) {

				boolean genderbtn_male = maleTitleradioButton.isSelected();

				if (!genderbtn_male) {

					maleTitleradioButton.click();
				}			

			} else {

				boolean genderbtn_fm = femaleTitleradioButton.isSelected();

				if (!genderbtn_fm) {

					femaleTitleradioButton.click();
				}

			}

			firstName.sendKeys(fn);
			lastName.sendKeys(ln);
			passwd.sendKeys(pswd);
			Select select = new Select(dayDropdown);
			select.selectByIndex(Integer.parseInt(day));
			Select monthdropdown = new Select(monthDropdown);
			monthdropdown.selectByIndex(Integer.parseInt(month));
			Select yeardropdn = new Select(yearDropdown);
			yeardropdn.selectByIndex(Integer.parseInt(year));

			boolean checkbox_newsletter = newletterCheckBox.isSelected();

			if (!checkbox_newsletter) {

				newletterCheckBox.click();
			}

			boolean checkbox_spOffer = specialofferCheckBox.isSelected();

			if (!checkbox_spOffer) {

				specialofferCheckBox.click();
			}

			//			Address details form

			firstNameAddress.sendKeys(fn);
			lastNameAddress.sendKeys(ln);
			companyAddress.sendKeys(add_cmny);
			Address1.sendKeys(add1);
			Address2.sendKeys(add2);
			city.sendKeys(cty);
			state.sendKeys(st);
			postcode.sendKeys(zip);
			country.sendKeys(cntry);
			additionalInformation.sendKeys(additionalInfo);
			homePhone.sendKeys(homeph);
			mobilePhoneNumber.sendKeys(mobile);
			aliasAddress.sendKeys(alias);


			registerBtn.click();	

		} catch (Exception e) {

			logger.error("createAccount Method Failed :", e);
			throw(e);
		}

		return new AddressTabPage();

	}



	public String[] VerifyErrorWhenoValuePassed(String gender, String fn , String ln, String pswd, String day, 

			String month, String year, String addr_fn , String add_ln , String add_cmny,String add1, String add2,String cty, 
			String st, String zip, String cntry,String additionalInfo,String homeph, String mobile,String alias) throws Exception {

		System.out.println("Inside the page object of the account creation  " + gender);

		String phoneError ;
		String pswdError ;
		String steError ;

		try {

			if (gender.equals("M")) {

				boolean genderbtn_male = maleTitleradioButton.isSelected();

				if (!genderbtn_male) {

					maleTitleradioButton.click();
				}			

			} else {

				boolean genderbtn_fm = femaleTitleradioButton.isSelected();

				if (!genderbtn_fm) {

					femaleTitleradioButton.click();
				}

			}

			firstName.sendKeys(fn);
			lastName.sendKeys(ln);
			passwd.sendKeys(pswd);
			Select select = new Select(dayDropdown);
			select.selectByIndex(4);;
			Select monthdropdown = new Select(monthDropdown);
			monthdropdown.selectByIndex(3);
			Select yeardropdn = new Select(yearDropdown);
			yeardropdn.selectByIndex(4);

			boolean checkbox_newsletter = newletterCheckBox.isSelected();

			if (!checkbox_newsletter) {

				newletterCheckBox.click();
			}

			boolean checkbox_spOffer = specialofferCheckBox.isSelected();

			if (!checkbox_spOffer) {

				specialofferCheckBox.click();
			}

			//			Address details form

			firstNameAddress.sendKeys(fn);
			lastNameAddress.sendKeys(ln);
			companyAddress.sendKeys(add_cmny);
			Address1.sendKeys(add1);
			Address2.sendKeys(add2);
			city.sendKeys(cty);
			state.sendKeys(st);
			postcode.sendKeys(zip);
			country.sendKeys(cntry);
			additionalInformation.sendKeys(additionalInfo);
			homePhone.sendKeys("");
			mobilePhoneNumber.sendKeys("");
			aliasAddress.sendKeys(alias);


			registerBtn.click();	

			Thread.sleep(20000);
			System.out.println("Phone Number error is " + phoneNumberError.getText());




			phoneError = phoneNumberError.getText();
			pswdError = passwordError.getText(); 
			steError = stateError.getText();

		} catch (Exception e) {

			logger.error("Method VerifyErrorWhenoValuePassed Failed: ", e);

			throw(e);
		}
		return new String[] {phoneError,pswdError,steError};

	}

	public String verifyNoPhoneNumberPassed(String gender, String fn , String ln, String pswd, String day, 

			String month, String year, String addr_fn , String add_ln , String add_cmny,String add1, String add2,String cty, 
			String st, String zip, String cntry,String additionalInfo,String homeph, String mobile,String alias) throws Exception {
		try {

			if (gender.equals("M")) {

				boolean genderbtn_male = maleTitleradioButton.isSelected();

				if (!genderbtn_male) {

					maleTitleradioButton.click();
				}			

			} else {

				boolean genderbtn_fm = femaleTitleradioButton.isSelected();

				if (!genderbtn_fm) {

					femaleTitleradioButton.click();
				}

			}

			firstName.sendKeys(fn);
			lastName.sendKeys(ln);
			passwd.sendKeys(pswd);
			Select select = new Select(dayDropdown);
			select.selectByIndex(4);;
			Select monthdropdown = new Select(monthDropdown);
			monthdropdown.selectByIndex(3);
			Select yeardropdn = new Select(yearDropdown);
			yeardropdn.selectByIndex(4);

			boolean checkbox_newsletter = newletterCheckBox.isSelected();

			if (!checkbox_newsletter) {

				newletterCheckBox.click();
			}

			boolean checkbox_spOffer = specialofferCheckBox.isSelected();

			if (!checkbox_spOffer) {

				specialofferCheckBox.click();
			}

			//				Address details form

			firstNameAddress.sendKeys(fn);
			lastNameAddress.sendKeys(ln);
			companyAddress.sendKeys(add_cmny);
			Address1.sendKeys(add1);
			Address2.sendKeys(add2);
			city.sendKeys(cty);
			state.sendKeys(st);
			postcode.sendKeys(zip);
			country.sendKeys(cntry);
			additionalInformation.sendKeys(additionalInfo);
			homePhone.sendKeys("");
			mobilePhoneNumber.sendKeys("");
			aliasAddress.sendKeys(alias);


			registerBtn.click();	

		} catch (Exception e) {

			logger.error("Method verifyNoPhoneNumberPassed faile " , e);

			throw(e);
		}


		return  phoneNumberError.getText();


	}



	public boolean verifyNonExistanceofElement(String gender, String fn , String ln, String pswd, String day, 

			String month, String year, String addr_fn , String add_ln , String add_cmny,String add1, String add2,String cty, 
			String st, String zip, String cntry,String additionalInfo,String homeph, String mobile,String alias) throws Exception {
		try {	

			if (gender.equals("M")) {

				boolean genderbtn_male = maleTitleradioButton.isSelected();

				if (!genderbtn_male) {

					maleTitleradioButton.click();
				}			

			} else {

				boolean genderbtn_fm = femaleTitleradioButton.isSelected();

				if (!genderbtn_fm) {

					femaleTitleradioButton.click();
				}

			}

			firstName.sendKeys(fn);
			lastName.sendKeys(ln);
			passwd.sendKeys(pswd);
			Select select = new Select(dayDropdown);
			select.selectByIndex(4);;
			Select monthdropdown = new Select(monthDropdown);
			monthdropdown.selectByIndex(3);
			Select yeardropdn = new Select(yearDropdown);
			yeardropdn.selectByIndex(4);

			boolean checkbox_newsletter = newletterCheckBox.isSelected();

			if (!checkbox_newsletter) {

				newletterCheckBox.click();
			}

			boolean checkbox_spOffer = specialofferCheckBox.isSelected();

			if (!checkbox_spOffer) {

				specialofferCheckBox.click();
			}

			//				Address details form

			firstNameAddress.sendKeys(fn);
			lastNameAddress.sendKeys(ln);
			companyAddress.sendKeys(add_cmny);
			Address1.sendKeys(add1);
			Address2.sendKeys(add2);
			city.sendKeys(cty);
			state.sendKeys(st);
			postcode.sendKeys(zip);
			country.sendKeys("-");
			additionalInformation.sendKeys(additionalInfo);
			homePhone.sendKeys("");
			mobilePhoneNumber.sendKeys("");
			aliasAddress.sendKeys(alias);


			registerBtn.click();	

			System.out.println(TestUtil.isElementPresent(state));

		} catch (Exception e) {

			logger.error("Method verifyNonExistanceofElement Failed: ", e);

			throw(e);
		}
		return  TestUtil.isElementPresent(state);


	}


}




