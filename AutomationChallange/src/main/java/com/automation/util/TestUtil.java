package com.automation.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.commons.io.FileUtils;

import com.automation.base.TestBase;

public class TestUtil extends TestBase {
	
	public static long PAGE_LOAD_TIMEOUT = 20;
	public static long IMPLICIT_WAIT = 30;
	
		public static String TESTDATA_SHEET_PATH = System.getProperty("user.dir") +"//src//main//java//com//automation//testdata//testdata.xlsx";

	static Workbook book;
	static Sheet sheet;
	
	
	public static void Hover(WebDriver driver , WebElement element) {
		
		
		Actions action = new Actions(driver);
		action.moveToElement(element).pause(100)
					.perform();
	}
	
	public static void HoverAndClick(WebDriver driver , WebElement elementToHover , WebElement elementToClick ) {
		
		Actions action = new Actions(driver);
		action.moveToElement(elementToHover).click(elementToClick).build().perform();
	}
	
	
	public static void switchToFrame() {
		
		driver.switchTo().frame(0);
	}
	
	public static String emailRandom() {
		Random random = new Random();
		int number = random.nextInt(1000);
		String randoms = String.format("%04d", number);
		String email = randoms+"@gmail.com";
		return email;
		
	}
	
	public static boolean isElementPresent(WebElement state) {
		
		boolean isElementPresent;
		
		
			if (state.isDisplayed()) {
				
				isElementPresent = true;
			} else {
				
				isElementPresent = false;
			}
			
		
		
		
		return isElementPresent;
		
		
	}
	
	public static Object[][] getTestData(String sheetName) {
		FileInputStream file = null;
		try {
			file = new FileInputStream(TESTDATA_SHEET_PATH);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		try {
			book = WorkbookFactory.create(file);
		} catch (InvalidFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		sheet = book.getSheet(sheetName);
		Object[][] data = new Object[sheet.getLastRowNum()][sheet.getRow(0).getLastCellNum()];
		// System.out.println(sheet.getLastRowNum() + "--------" +
		// sheet.getRow(0).getLastCellNum());
		for (int i = 0; i < sheet.getLastRowNum(); i++) {
			for (int k = 0; k < sheet.getRow(0).getLastCellNum(); k++) {
				data[i][k] = sheet.getRow(i + 1).getCell(k).toString();
				// System.out.println(data[i][k]);
			}
		}
		return data;
	}
	
	public static void takeScreenShot()  {
		
		File srcFile= ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		try {
			FileUtils.copyFile(srcFile, new File(System.getProperty("user.dir")+"//screenshots//"+System.currentTimeMillis()+".png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


}
