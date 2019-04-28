package com.automation.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.automation.base.TestBase;

public class OrderPage extends TestBase {


	@FindBy(xpath = "//*[contains(@class,'checkout')]" )
	WebElement proceedtocheckoutButton;


	public OrderPage() {

		PageFactory.initElements(driver, this);
	}


	public String verifyOrderPageTitle() {

		return driver.getTitle();


	}

	public GuestCheckoutPage clickonProceedToCheckoutButton() throws Exception {

		try {

			proceedtocheckoutButton.click();

		} catch (Exception e) {

			logger.error("Method clickonProceedToCheckoutButton Failed: " , e);
			throw (e);
		}

		return new GuestCheckoutPage();

	}

}
