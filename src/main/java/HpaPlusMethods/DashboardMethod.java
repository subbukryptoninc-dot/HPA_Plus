package HpaPlusMethods;

import com.microsoft.playwright.Page;

import io.qameta.allure.Step;

public class DashboardMethod {
	
	private Page page;
	
	public DashboardMethod(Page page) {
		this.page= page;
		}
	
//	Elements Starts Here
	
	private String NextWeekButton = "//button[@title= 'Next week']";
	private String PreviousWeekButton = "//button[@title= 'Previous week']";
	
	
// Methods Starts here
	@Step("All Dashbaord actions created should be performed as required")
	public void dashboardNav() {
		page.locator(NextWeekButton).click();
		page.locator(PreviousWeekButton).click();
		
	}

}
