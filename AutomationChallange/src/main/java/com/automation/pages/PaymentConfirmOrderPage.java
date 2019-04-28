package com.automation.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.automation.base.TestBase;

public class PaymentConfirmOrderPage extends TestBase{

	@FindBy(xpath = "//a[contains(text(),'Printed Chiffon Dres')]")
	WebElement printedShiffonText;

	@FindBy(xpath = "//a[contains(text(),'Size')]")
	WebElement dressSizeText;

	@FindBy(xpath = "//span[@class='price special-price']")
	WebElement price;


	public PaymentConfirmOrderPage() {

		PageFactory.initElements(driver, this);
	}


	public String getDressInfo() {

		return  printedShiffonText.getText();
	}

	public String getDressSize() throws Exception {
		String size;
		try {
			String text = dressSizeText.getText();
			String[] words = text.split(":");
			size= words[2].trim();
			System.out.println(words[2]);
		} catch (Exception e) {

			logger.error("Method getDressSize Failed ", e );
			throw(e);
		}

		return size  ;
	}

	public String getPriceInfo() {

		return  price.getText();
	}

}
