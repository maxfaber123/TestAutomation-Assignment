package com.automation.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.automation.base.TestBase;

public class GuestCheckoutPage extends TestBase {

	@FindBy(xpath = "//input[@name='email_create']" )
	WebElement email;

	@FindBy(xpath = "//button[@name='SubmitCreate']" )
	WebElement submitBtn;

	@FindBy(xpath="//li[contains(text(),'already been registered')]")
	WebElement emailAlreadyRegisteredError;


	public  GuestCheckoutPage() {

		PageFactory.initElements(driver, this);
	}

	public String verifyGuestPageTitle() {

		return driver.getTitle();
	}


	public CreateAccountPage enterEmailandClickCreatAccount(String emailid) throws Exception {

		try {

			email.sendKeys(emailid);
			submitBtn.click();

		} catch (Exception e) {

			logger.error("Method enterEmailandClickCreatAccount Failed ", e);
			throw(e);
		}


		return  new CreateAccountPage();
	}

	public String verifyAlreadyRegisteredEmail(String emailId) throws Exception {

		try {

			email.sendKeys(emailId);
			submitBtn.click();

		} catch (Exception e) {

			logger.error("Method verifyAlreadyRegisteredEmail Failed: ", e);
			throw(e);
		}

		return emailAlreadyRegisteredError.getText();



	}

}
