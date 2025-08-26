package HpaTestCases;

import org.testng.annotations.Test;

import com.microsoft.playwright.Page;

import HpaDependables.PlaywrightFactory;
import HpaDependables.Values;
import HpaPlusMethods.LoginMethod;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
@Epic("Login Module")
@Feature("Login to the application")
public class LoginTest {
	
	private Page page;
	Values value = new Values();
	PlaywrightFactory pf = new PlaywrightFactory();
	
	@Test(groups = "ShiftAddTests")
	@Description("Users should be able to Log into the application with Valid Login details")
	@Severity(SeverityLevel.CRITICAL)
	public void loginmethodTest() {
		page = pf.pagePasser();
		LoginMethod lm = new LoginMethod(page);
		lm.LoginMethodHpa();
	}
	

}
