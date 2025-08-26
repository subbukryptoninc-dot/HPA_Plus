package HpaPlusMethods;

import com.microsoft.playwright.Page;

import HpaDependables.PlaywrightFactory;
import HpaDependables.Values;
import io.qameta.allure.Step;

public class AppOpen {
	
	PlaywrightFactory pf = new PlaywrightFactory();
	Values value = new Values();
	private Page page;
	
	@Step("Perform application opening without any interruptions")
	public void setUp() {
	page = pf.initBrowser(value.browser);
	page.navigate(value.AppUrl);
	//pf.closeBrowser();
	}

}
