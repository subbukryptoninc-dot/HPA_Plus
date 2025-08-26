package HpaTestCases;

import org.testng.annotations.Test;

import com.microsoft.playwright.Page;

import HpaDependables.PlaywrightFactory;
import HpaPlusMethods.DashboardMethod;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
@Epic("Dashboard Navigation Module")
@Feature("Dashboard Navigation")
public class DashboardTest {
	private Page page;
	
	PlaywrightFactory pf = new PlaywrightFactory();
	
	@Test(groups = "ShiftAddTests")
	@Description("All the actions for navigation should be executed as Designed")
	@Severity(SeverityLevel.NORMAL)
	public void DashTest() {
		page = pf.pagePasser();
		DashboardMethod db = new DashboardMethod(page);
		db.dashboardNav();
	}

}
