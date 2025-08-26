package HpaTestCases;

import java.io.ByteArrayInputStream;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import com.microsoft.playwright.Page;

import HpaDependables.PlaywrightFactory;
import HpaDependables.TestUtils;
import HpaDependables.Values;
import io.qameta.allure.Allure;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;



/* For all the tests below need to pass data and also need check in different scenarios:
 * Normal Shifts with Normal Time ranges
 * Normal Shifts with Multiple line Item Time ranges
 * OvernightShifts on normal days
 * Overnight Shifts on weekends
 * Overnight shifts on public holidays in normal weekdays
 * Overnight shifts in weekends and normal days combo for friday & Sat, sun & monday
 * Overnight Shifts on public holidays & weekends combo fri & sat (vice versa like friday public holiday,normal saturday & holiday saturday & normal friday. Same with sunday and monday)
 * Normal Shifts on Sundays and Saturdays and also Public holidays
 * Internal shift  - Later
 * SleepOver Implementation - Later
 * */

@Test(groups = "ShiftAddTests")
@Epic("Shift Management Module")
@Feature("Create Shifts")
public class ShiftAddTests {
	private Page page;
	
	private static final Logger logger = (org.apache.logging.log4j.core.Logger) LogManager.getLogger(ShiftAddMethod.class);

	Values value = new Values();
	
	PlaywrightFactory pf =new PlaywrightFactory();
	
	@Test(dataProvider = "shiftData", dataProviderClass = TestNGDepnds.DataProviderExcel.class, priority =1)
	@Description("Verify that a Normal Simple Shift is creating properly")
	@Severity(SeverityLevel.CRITICAL)
	public void NormalShiftTest(String TC_Num1,
            int ClientID1,
            int PriceID1,
            String StartTime1,
            String EndTime1,
            int CarerID1,
            int SplitPrice1,
            String SplitStart1,
            String SplitEnd1,
            String EndDate1) throws InterruptedException {
		
		page = pf.pagePasser();
		
		ShiftAddMethod shift = new ShiftAddMethod(page);
		
		
		TestUtils.ApiListener(page);
		
		
		shift.normalShift(TC_Num1, ClientID1, PriceID1, StartTime1, EndTime1, CarerID1, SplitPrice1, SplitStart1, SplitEnd1, EndDate1); //Method calling
		
		//FrontEnd Assertion here
		try {
			Thread.sleep(2000);		
			Assert.assertTrue(page.locator("//h2[@id = 'fc-dom-1']").isVisible(), "Normal Shift Created Message Should come");
		}
		catch(Exception e) {
			TestUtils.takeScreenshot(page, "NormalShiftTest_Failed");
		}
	}
	
	
	
	@Test(dataProvider = "shiftData", dataProviderClass = TestNGDepnds.DataProviderExcel.class, priority = 2)
	@Description("Verify that a Normal Transport Shift is creating properly")
	@Severity(SeverityLevel.CRITICAL)
	public void NormalTrasnportTest(String TC_Num1,
            int ClientID1,
            int PriceID1,
            String StartTime1,
            String EndTime1,
            int CarerID1,
            int SplitPrice1,
            String SplitStart1,
            String SplitEnd1,
            String EndDate1) throws InterruptedException {
		
		page = pf.pagePasser();
		ShiftAddMethod shift = new ShiftAddMethod(page);
		
		TestUtils.ApiListener(page); //Backend API assertion here
		
		shift.normaltranshift(TC_Num1, ClientID1, PriceID1, StartTime1, EndTime1, CarerID1, SplitPrice1, SplitStart1, SplitEnd1, EndDate1); // Actual Test method here
		
		try {
			Thread.sleep(3000);
			Assert.assertTrue(page.locator("//h2[@id = 'fc-dom-1']").isVisible(), "Normal Transport Shift Created Message should come");
		}
		catch(Exception e) {
			TestUtils.takeScreenshot(page, "NormalTrasnportShiftTest_failed");
		}
		
}
	@Test(dataProvider = "shiftData", dataProviderClass = TestNGDepnds.DataProviderExcel.class, priority =3)
	@Description("Verify that a Normal Split Shift is creating properly")
	@Severity(SeverityLevel.CRITICAL)
	public void SplitShiftTest(String TC_Num1,
            int ClientID1,
            int PriceID1,
            String StartTime1,
            String EndTime1,
            int CarerID1,
            int SplitPrice1,
            String SplitStart1,
            String SplitEnd1,
            String EndDate1) throws InterruptedException {
		
		page = pf.pagePasser();
		ShiftAddMethod shift = new ShiftAddMethod(page);
		
		TestUtils.ApiListener(page);
		
		shift.NormalSplitShift(TC_Num1, ClientID1, PriceID1, StartTime1, EndTime1, CarerID1, SplitPrice1, SplitStart1, SplitEnd1, EndDate1);
		
		try {
			Thread.sleep(3000);
				Assert.assertTrue(page.locator("//h2[@id = 'fc-dom-1']").isVisible(), "Normal Split Shift is being created");
			}
		catch(Exception e) {
			TestUtils.takeScreenshot(page, "NormalSplitShiftFailed");
		}
	
	}
	
	@Test(dataProvider = "shiftData", dataProviderClass = TestNGDepnds.DataProviderExcel.class, priority = 4)
	@Description("Verify that a Normal Repeat Shift is creating properly")
	@Severity(SeverityLevel.CRITICAL)
	public void RepeatTest(String TC_Num1,
            int ClientID1,
            int PriceID1,
            String StartTime1,
            String EndTime1,
            int CarerID1,
            int SplitPrice1,
            String SplitStart1,
            String SplitEnd1,
            String EndDate1) throws InterruptedException {
		
		page = pf.pagePasser();
		ShiftAddMethod shift = new ShiftAddMethod(page);
		
		TestUtils.ApiListener(page);
		
		shift.NormalRepeatShift(TC_Num1, ClientID1, PriceID1, StartTime1, EndTime1, CarerID1, SplitPrice1, SplitStart1, SplitEnd1, EndDate1);
		
		try {
			Thread.sleep(3000);
				Assert.assertTrue(page.locator("//h2[@id = 'fc-dom-1']").isVisible(), "Normal Split Shift is being created");
			}
		catch(Exception e) {
			TestUtils.takeScreenshot(page, "NormalrepeatShiftFailed");
		}
	}
	
	@Test(dataProvider = "shiftData", dataProviderClass = TestNGDepnds.DataProviderExcel.class, priority = 5)
	@Description("Verify that a transport with repeat is creating properly")
	@Severity(SeverityLevel.CRITICAL)
	public void RepeattrasnportTest(String TC_Num1,
            int ClientID1,
            int PriceID1,
            String StartTime1,
            String EndTime1,
            int CarerID1,
            int SplitPrice1,
            String SplitStart1,
            String SplitEnd1,
            String EndDate1) throws InterruptedException {
		
		page = pf.pagePasser();
		ShiftAddMethod shift = new ShiftAddMethod(page);
		
		TestUtils.ApiListener(page);
		
		shift.NormalRepeatTransShift(TC_Num1, ClientID1, PriceID1, StartTime1, EndTime1, CarerID1, SplitPrice1, SplitStart1, SplitEnd1, EndDate1);
		
		try {
			Thread.sleep(3000);
				Assert.assertTrue(page.locator("//h2[@id = 'fc-dom-1']").isVisible(), "Normal Split Shift is being created");
			}
		catch(Exception e) {
			TestUtils.takeScreenshot(page, "NormalrepeattransportShiftFailed");
		}
		
		
	}
	
	

	
	 @AfterMethod
	    public void takeScreenshotOnFailure(ITestResult result) {
	        if (!result.isSuccess() && page != null) {
	            try {
	                byte[] screenshot = page.screenshot();
	                Allure.addAttachment(result.getName() + "_Failure", 
	                                     new ByteArrayInputStream(screenshot));
	            } catch (Exception e) {
	                e.printStackTrace();
	            }
	        }
	    }
	}

