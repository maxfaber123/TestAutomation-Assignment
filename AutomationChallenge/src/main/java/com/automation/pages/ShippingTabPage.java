package com.automation.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.automation.base.TestBase;

public class ShippingTabPage extends TestBase {

	@FindBy(xpath= "//input[@id='cgv']")
	WebElement termAndConditionsCheckBox;


	@FindBy(xpath= "//button[@name='processCarrier']")
	WebElement proceedToCheckoutBtn;

	@FindBy(xpath = "//span[contains(text(),'Shipping')]")
	WebElement shippingTabPageText;

	public ShippingTabPage() {

		PageFactory.initElements(driver, this);
	}

	public String validateShippingTabPage() {

		return shippingTabPageText.getText();


	}

	public PaymentConfirmOrderPage proceedtoShippingAddressTab() throws Exception {

		try {

			termAndConditionsCheckBox.click();	

			proceedToCheckoutBtn.click();

		} catch (Exception e) {
			logger.error("Method proceedtoShippingAddressTab Failed: " , e);
			throw(e);
		}

		return new PaymentConfirmOrderPage();



	}







}
