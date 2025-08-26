package HpaTestCases;

import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.SelectOption;

import HpaDependables.TestUtils;
import io.qameta.allure.Step;

public class ShiftAddMethod {
	private Page page;

	TestUtils utils;

	public ShiftAddMethod(Page page) {
		this.page = page;
	}

	// Elements Starts here

	private String AddButton = "//button[@title = 'Add']"; // Shift add button element

	private String InternalShiftCheck = "//input[@id= 'is_internal_shift']"; // Internal Shift Type Checkbox

	// Shift Client Details Section starts

	private String ClientField = "//select[@id= 'client_id']";

	private String PriceId = "//select[@id= 'price_id']";

	private String PriceRation = "//select[@id= 'price_ratio']";

	// Shift Shift Section starts here

	private String transportCheck = "//input[@id= 'is_driver_check']";

	private String transportKm = "//input[@id= 'drive_km']";

	private String transportAmount = "//input[@id= 'driver_amount']";

	private String trasportreferenceId = "//select[@id= 'reference_id']";

	private String ShiftType = "//select[@id= 'shift_id']";

	private String ShiftAllowance = "//select[@id= 'allowance_ids']";

	// Time & Location Elements Starts Here

	private String StartTime = "//input[@id= 'start_time']";

	private String EndTime = "//input[@id= 'end_time']";

	private String RepeatCheckbox = "//input[@id= 'is_repeat']"; // Repeat Elements starts here

	private String recurrenceSelect = "//select[@id= 'recurrence']";

	private String RepeatEvery = "//select[@id= 'repeat_every']";

	private String EndDate = "//input[@id= 'end_date']";

	private String SplitCheckbox = "//input[@id= 'is_shift']"; // Split Shift Elements starts here

	private String SplitPriceBook = "//select[@id= 'new_price_id']";

	private String SplitStartTime = "//input[@id= 'durat_start_time']";

	private String SplitEndTime = "//input[@id= 'durat_end_time']";

	// Carer elements Starts here

	private String CarerId = "//select[@id= 'carer_id']";

	private String CarerPayGroup = "//select[@id= 'paygroup_id']";

	private String Instructions = "//textarea[@id= 'instruction']";

	private String SubmitButton = "//button[@id= 'btn-save-event']";

	private String DashboardDate = "//h2[@id= 'fc-dom-1']";
	
	SoftAssert softAssert = new SoftAssert();
	
	  // use the string selector
	

	// Methods Will be starting here

	// 1. Normal Shift method.
	@Step("Perform Normal Shift for Test Case ID: {TC_Num1}")
	public void normalShift(String TC_Num1, int ClientID1, int PriceID1, String StartTime1, String EndTime1,
			int CarerID1, int SplitPrice1, String SplitStart1, String SplitEnd1, String EndDate1) throws InterruptedException {
		Locator addButtonLocator = page.locator(AddButton);

		page.locator(AddButton).click();
		
		page.locator(ClientField).selectOption(new SelectOption().setIndex(ClientID1));

		page.locator(PriceId).selectOption(new SelectOption().setIndex(PriceID1));

		page.locator(PriceRation).selectOption(new SelectOption().setIndex(1)); // DD not needed (data driven)

		page.locator(ShiftType).selectOption(new SelectOption().setIndex(2)); // DD not needed (data driven)
		page.locator(ShiftAllowance).selectOption(new SelectOption().setIndex(1)); // DD not needed (data driven)

		page.locator(StartTime).evaluate("(el, value) => el.value = value", StartTime1.toUpperCase());

		page.locator(EndTime).evaluate("(el, value) => el.value = value", (EndTime1.toUpperCase()));

		page.locator(CarerId).selectOption(new SelectOption().setIndex(CarerID1));
		page.locator(CarerPayGroup).selectOption(new SelectOption().setIndex(1)); // DD not needed (data driven)

		page.locator(Instructions).fill("Just a simple Instructions for now");

		page.locator(SubmitButton).click();
		
		try {
			Thread.sleep(2000);
		    Assert.assertTrue(addButtonLocator.isVisible(), "Add Button Should be visible");
			System.out.println(" 1. Normal Shift PASSED for the test case ID: " + TC_Num1);
			
		} catch (AssertionError e) {
		
			System.out.println(" 1. Normal Shift FAILED for the test case ID: " + TC_Num1);
			page.goBack();

		}

	}

	// 2. Normal Shift with transport option
	@Step("Perform Normal Shift with transport for Test Case ID: {TC_Num1}")
	public void normaltranshift(String TC_Num1, int ClientID1, int PriceID1, String StartTime1, String EndTime1,
			int CarerID1, int SplitPrice1, String SplitStart1, String SplitEnd1, String EndDate1) throws InterruptedException {
		Locator addButtonLocator = page.locator(AddButton);
		page.locator(AddButton).click();
		page.locator(ClientField).selectOption(new SelectOption().setIndex(ClientID1));
		page.locator(PriceId).selectOption(new SelectOption().setIndex(PriceID1));
		page.locator(PriceRation).selectOption(new SelectOption().setIndex(1)); // DD not needed (data driven)

		page.locator(transportCheck).click();
		page.locator(ShiftType).selectOption(new SelectOption().setIndex(2)); // DD not needed (data driven)
		page.locator(ShiftAllowance).selectOption(new SelectOption().setIndex(1)); // DD not needed (data driven)
		page.locator(transportKm).fill("5");
		page.locator(transportAmount).fill("5");
		page.locator(trasportreferenceId).selectOption(new SelectOption().setIndex(1)); // DD not needed (data driven)

		page.locator(StartTime).evaluate("(el, value) => el.value = value", StartTime1);

		page.locator(EndTime).evaluate("(el, value) => el.value = value", EndTime1);

		page.locator(CarerId).selectOption(new SelectOption().setIndex(CarerID1));
		page.locator(CarerPayGroup).selectOption(new SelectOption().setIndex(1)); // DD not needed (data driven)

		page.locator(Instructions).fill("Just a simple Instructions for now");

		page.locator(SubmitButton).click();
		try {
			
			Thread.sleep(2000);
		    Assert.assertTrue(addButtonLocator.isVisible(), "Add Button Should be visible");
			System.out.println(" 2. Normal Shift with transport PASSED for the test case ID: " + TC_Num1);
		} catch (AssertionError e) {
			System.out.println(" 2. Normal Shift with transport FAILED for the test case ID: " + TC_Num1);
			page.goBack();
			

		}

	}

	// 3. Normal Split Shift Implementation
	@Step("Perform Normal Split Shift for Test Case ID: {TC_Num1}")
	public void NormalSplitShift(String TC_Num1, int ClientID1, int PriceID1, String StartTime1, String EndTime1,
			int CarerID1, int SplitPrice1, String SplitStart1, String SplitEnd1, String EndDate1) throws InterruptedException {
		Locator addButtonLocator = page.locator(AddButton);
		page.locator(AddButton).click();
		page.locator(ClientField).selectOption(new SelectOption().setIndex(ClientID1));
		page.locator(PriceId).selectOption(new SelectOption().setIndex(PriceID1));
		page.locator(PriceRation).selectOption(new SelectOption().setIndex(1));// DD not needed (data driven)

		page.locator(ShiftType).selectOption(new SelectOption().setIndex(2));// DD not needed (data driven)
		page.locator(ShiftAllowance).selectOption(new SelectOption().setIndex(1));// DD not needed (data driven)

		page.locator(StartTime).evaluate("(el, value) => el.value = value", StartTime1);

		page.locator(EndTime).evaluate("(el, value) => el.value = value", EndTime1);

		page.locator(SplitCheckbox).click();
		page.locator(SplitPriceBook).selectOption(new SelectOption().setIndex(SplitPrice1));
		page.locator(SplitStartTime).evaluate("(el, value) => el.value = value", SplitStart1);
		page.locator(SplitEndTime).evaluate("(el, value) => el.value = value", SplitEnd1);

		page.locator(CarerId).selectOption(new SelectOption().setIndex(CarerID1));
		page.locator(CarerPayGroup).selectOption(new SelectOption().setIndex(1));// DD not needed (data driven)

		page.locator(Instructions).fill("Just a simple Instructions for now");

		page.locator(SubmitButton).click();
		try {
			Thread.sleep(2000);
		    Assert.assertTrue(addButtonLocator.isVisible(), "Add Button Should be visible");
			System.out.println(" 3. Normal Shift with Split Shift PASSED for the test case ID: " + TC_Num1);
		} catch (AssertionError e) {
			
			System.out.println(" 3. Normal Shift with Split Shift FAILED for the test case ID: " + TC_Num1);
			page.goBack();
		}
	}

	// 4. Normal Shift with repeat option
	@Step("Perform Normal Repeat Shift for Test Case ID: {TC_Num1}")
	public void NormalRepeatShift(String TC_Num1, int ClientID1, int PriceID1, String StartTime1, String EndTime1,
			int CarerID1, int SplitPrice1, String SplitStart1, String SplitEnd1, String EndDate1) throws InterruptedException {
		Locator addButtonLocator = page.locator(AddButton);
		page.locator(AddButton).click();
		page.locator(ClientField).selectOption(new SelectOption().setIndex(ClientID1));
		page.locator(PriceId).selectOption(new SelectOption().setIndex(PriceID1));
		page.locator(PriceRation).selectOption(new SelectOption().setIndex(1));// DD not needed (data driven)

		page.locator(ShiftType).selectOption(new SelectOption().setIndex(2));// DD not needed (data driven)
		page.locator(ShiftAllowance).selectOption(new SelectOption().setIndex(1));// DD not needed (data driven)

		page.locator(StartTime).evaluate("(el, value) => el.value = value", StartTime1);

		page.locator(EndTime).evaluate("(el, value) => el.value = value", EndTime1);

		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		page.locator(RepeatCheckbox).click();
		page.locator(recurrenceSelect).selectOption(new SelectOption().setIndex(1)); // DD not needed (data driven)
		page.locator(RepeatEvery).selectOption(new SelectOption().setIndex(1)); // DD not needed (data driven)
		page.locator(EndDate).evaluate("(el, value) => el.value = value", EndDate1);

		page.locator(CarerId).selectOption(new SelectOption().setIndex(CarerID1));
		page.locator(CarerPayGroup).selectOption(new SelectOption().setIndex(1)); // DD not needed (data driven)

		page.locator(Instructions).fill("Just a simple Instructions for now");

		page.locator(SubmitButton).click();
		try {
			Thread.sleep(2000);
		    Assert.assertTrue(addButtonLocator.isVisible(), "Add Button Should be visible");
			System.out.println(" 4. Normal Shift with repeat PASSED for the test case ID: " + TC_Num1);

		} catch (AssertionError e) {
			System.out.println(" 4. Normal Shift with repeat FAILED for the test case ID: " + TC_Num1);
			page.goBack();
		}

	}

	// 5. Normal Shift with repeat and transport included
	@Step("Perform Normal repeat with transpot Shift for Test Case ID: {TC_Num1}")
	public void NormalRepeatTransShift(String TC_Num1, int ClientID1, int PriceID1, String StartTime1, String EndTime1,
			int CarerID1, int SplitPrice1, String SplitStart1, String SplitEnd1, String EndDate1) throws InterruptedException {
		Locator addButtonLocator = page.locator(AddButton);
		page.locator(AddButton).click();
		page.locator(ClientField).selectOption(new SelectOption().setIndex(ClientID1));
		page.locator(PriceId).selectOption(new SelectOption().setIndex(PriceID1));
		page.locator(PriceRation).selectOption(new SelectOption().setIndex(1)); // DD not needed (data driven)

		page.locator(transportCheck).click();
		page.locator(ShiftType).selectOption(new SelectOption().setIndex(2)); // DD not needed (data driven)
		page.locator(ShiftAllowance).selectOption(new SelectOption().setIndex(1)); // DD not needed (data driven)
		page.locator(transportKm).fill("5");
		page.locator(transportAmount).fill("5");
		page.locator(trasportreferenceId).selectOption(new SelectOption().setIndex(1)); // DD not needed (data driven)

		page.locator(StartTime).evaluate("(el, value) => el.value = value", StartTime1);

		page.locator(EndTime).evaluate("(el, value) => el.value = value", EndTime1);

		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		page.locator(RepeatCheckbox).click();
		page.locator(recurrenceSelect).selectOption(new SelectOption().setIndex(1)); // DD not needed (data driven)
		page.locator(RepeatEvery).selectOption(new SelectOption().setIndex(1)); // DD not needed (data driven)
		page.locator(EndDate).evaluate("(el, value) => el.value = value", EndDate1);

		page.locator(CarerId).selectOption(new SelectOption().setIndex(CarerID1));
		page.locator(CarerPayGroup).selectOption(new SelectOption().setIndex(1)); // DD not needed (data driven)

		page.locator(Instructions).fill("Just a simple Instructions for now");

		page.locator(SubmitButton).click();
		try {
			
			Thread.sleep(2000);
		    Assert.assertTrue(addButtonLocator.isVisible(), "Add Button Should be visible");
			System.out.println(
					" 5. Normal Shift with repeat and transport included PASSED for the test case ID: " + TC_Num1);
		} catch (AssertionError e) {
			
			System.out.println(
					" 5. Normal Shift with repeat and transport included FAILED for the test case ID: " + TC_Num1);
			page.goBack();
		}
	}
}
