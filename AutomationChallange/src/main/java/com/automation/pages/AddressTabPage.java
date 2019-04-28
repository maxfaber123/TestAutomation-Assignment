package com.automation.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.automation.base.TestBase;

public class AddressTabPage extends TestBase {


	@FindBy(xpath= "//button[@name='processAddress']")
	WebElement proceedToCheckoutBtn;

	@FindBy(xpath = "//span[contains(text(),'Addresses')]")
	WebElement addresspagext;



	public AddressTabPage() {

		PageFactory.initElements(driver, this);
	}



	public String validateAddressPage() {

		return addresspagext.getText();


	}

	public ShippingTabPage proceedtoCheckoutAddressTab() throws Exception {

		try {

			proceedToCheckoutBtn.click();
		} catch (Exception e) {

			logger.error("proceedtoCheckoutAddressTab Method failed :" , e);
			throw(e);
		}

		return new ShippingTabPage();



	}
}
