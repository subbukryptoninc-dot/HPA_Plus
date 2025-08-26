package HpaDependables;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;

public class PlaywrightFactory {
	
	Values value = new Values();
	
	private Playwright playwright;
	private Browser browser;
	private BrowserContext context;
	private static Page page;
	
	public Page initBrowser(String BrowserName) {
		
		playwright = Playwright.create();
		
		switch (BrowserName.toLowerCase()) 
		{
			case "firefox":
				BrowserType.LaunchOptions options = new BrowserType.LaunchOptions();
				browser = playwright.firefox().launch();
				options.setHeadless(false);
				options.setSlowMo(1000);
			
				break;
			case "webkit":
				browser = playwright.webkit().launch(new BrowserType.LaunchOptions().setHeadless(false));
				break;
				
			default:
				BrowserType.LaunchOptions options1 = new BrowserType.LaunchOptions();
				options1.setHeadless(true);
				options1.setArgs(java.util.Arrays.asList("--start-maximized"));
				options1.setSlowMo(1000);
				browser = playwright.chromium().launch(options1);
				
				
		}
		context = browser.newContext(
		        new Browser.NewContextOptions()
		                .setViewportSize(null)
		                );
		page = context.newPage();
		return page;
		
	}
	
	public Page pagePasser() {
		return page;
	}
	
	public void closeBrowser() {
		browser.close();
	//	playwright.close();
		
		
	}
	

}
