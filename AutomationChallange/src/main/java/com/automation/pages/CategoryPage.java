package com.automation.pages;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

import com.automation.base.TestBase;
import com.automation.util.TestUtil;

public class CategoryPage extends TestBase {



	@FindBy(xpath = "//img[contains(@src,'http://automationpractice.com/img/p/2/0/20-home_default.jpg')]")
	WebElement chiffonLink;

	@FindBy(xpath ="//span[text()='Quick view']")
	WebElement quickview;

	@FindBy(xpath = "//select[@name='group_1']")
	WebElement sizeDropDown;

	@FindBy(xpath="//button[@type= 'submit']")
	WebElement addToCartButton;

	@FindBy(xpath = "//*[contains(@class,'continue')]" )
	WebElement continueshoppingButton;

	@FindBy(xpath = "//b[contains(text(),'Cart')]")
	WebElement cartLink;

	@FindBy(xpath = "//a[@id='button_order_cart']")
	WebElement checkoutLink;



	//	Initialize the Page Object
	public CategoryPage() {

		PageFactory.initElements(driver, this);
	}

	public String validateCategoryPageTitle() {

		return driver.getTitle();
	}

	public OrderPage selectItemAndAddToCart() throws Exception {
		try {
			chiffonLink.click();

			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

			System.out.println("Total windows are " + driver.getWindowHandles().size());
			TestUtil.switchToFrame();

			Select selectElement = new Select(sizeDropDown);
			selectElement.selectByIndex(1);; 
			addToCartButton.click();

			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			((JavascriptExecutor)driver).executeScript("window.scrollTo(0,"+continueshoppingButton.getLocation().x+")");
			continueshoppingButton.click();

			TestUtil.Hover(driver, cartLink);
			checkoutLink.click();

		} catch (Exception e) {

			logger.error("selectItemAndAddToCart Method Failed : ", e );

			throw (e);
		}

		return new OrderPage();

	}

}
